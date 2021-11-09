package com.thanthu.labmda.api;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class CreateOrderLambdaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateOrder() throws Exception {
		CreateOrderLambda createOrderLambda = new CreateOrderLambda();
		APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
		request.setBody("{\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"itemName\": \"Mac Book Pro\",\r\n" + 
				"    \"quantity\": 2\r\n" + 
				"}");
		APIGatewayProxyResponseEvent order = createOrderLambda.createOrder(request);
		Assert.assertEquals("1", order.getBody());
	}

}
