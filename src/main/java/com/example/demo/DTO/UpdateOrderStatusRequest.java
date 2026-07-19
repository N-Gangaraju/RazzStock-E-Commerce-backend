package com.example.demo.DTO;

import com.example.demo.Enum.OrderStatus;

public class UpdateOrderStatusRequest {

	private OrderStatus status;
	

	public UpdateOrderStatusRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateOrderStatusRequest(OrderStatus status) {
		super();
		this.status = status;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdateOrderStatusRequest [status=" + status + "]";
	}
	
}
