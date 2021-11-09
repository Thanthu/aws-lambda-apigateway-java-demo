package com.thanthu.labmda.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
	private Long id;
	private String itemName;
	private Integer quantity;
}
