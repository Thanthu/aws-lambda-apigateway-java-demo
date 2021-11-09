package com.thanthu.labmda.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanthu.labmda.api.dto.Order;

public class ReadOrdersLambda {

	private ObjectMapper objectMapper;

	public ReadOrdersLambda() {
		objectMapper = new ObjectMapper();
	}

	public APIGatewayProxyResponseEvent getOrders(APIGatewayProxyRequestEvent request) throws Exception {
		Order order = new Order(1L, "Mac Book Pro", 2);
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(objectMapper.writeValueAsString(order));
	}

}
