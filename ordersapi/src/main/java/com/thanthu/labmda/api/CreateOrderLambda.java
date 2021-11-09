package com.thanthu.labmda.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanthu.labmda.api.dto.Order;

public class CreateOrderLambda {

	private ObjectMapper objectMapper;

	public CreateOrderLambda() {
		objectMapper = new ObjectMapper();
	}

	public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request) throws Exception {
		Order order = objectMapper.readValue(request.getBody(), Order.class);
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(order.getId().toString());
	}

}
