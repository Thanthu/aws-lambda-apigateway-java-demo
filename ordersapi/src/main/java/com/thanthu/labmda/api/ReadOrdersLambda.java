package com.thanthu.labmda.api;

import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanthu.labmda.api.dto.Order;

public class ReadOrdersLambda {

	private ObjectMapper objectMapper;
	private AmazonDynamoDB amazonDynamoDB;

	public ReadOrdersLambda() {
		objectMapper = new ObjectMapper();
		amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
	}

	public APIGatewayProxyResponseEvent getOrders(APIGatewayProxyRequestEvent request) throws Exception {

		ScanResult scanResult = amazonDynamoDB.scan(new ScanRequest().withTableName(System.getenv("ORDERS_TABLE")));
		
		List<Order> orders = scanResult
				.getItems()
				.stream()
				.map(item -> new Order(Long.parseLong(item.get("id").getN()),
						item.get("itemName").getS(), 
						Integer.parseInt(item.get("quantity").getN())))
				.collect(Collectors.toList());
		
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(objectMapper.writeValueAsString(orders));
	}

}
