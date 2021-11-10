package com.thanthu.labmda.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanthu.labmda.api.dto.Order;

public class CreateOrderLambda {

	private ObjectMapper objectMapper;
	private DynamoDB dynamoDB;
	private Table ordersTable;

	public CreateOrderLambda() {
		objectMapper = new ObjectMapper();
		dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
		ordersTable = dynamoDB.getTable(System.getenv("ORDERS_TABLE"));
	}

	public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request) throws Exception {
		Order order = objectMapper.readValue(request.getBody(), Order.class);
		
		Item item = new Item().withPrimaryKey("id", order.getId())
				.withString("itemName", order.getItemName())
				.withInt("quantity", order.getQuantity());
		ordersTable.putItem(item);
		
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(order.getId().toString());
	}

}
