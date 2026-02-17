package com.educandoweb.course.entities.enums;

public enum OrderStatus {

		ABERTO(1),
		EM_PRODUCAO(2),
		ENVIADO(3),
		DEVOLVIDO(4),
		CANCELADO(5);
	
		private int code;
		
		private OrderStatus(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
		public static OrderStatus valueOf(int code) {
			for (OrderStatus value : OrderStatus.values()) {
				if(value.getCode() == code) {
					return value;
				}
			}
			throw new IllegalArgumentException("Invalid OrderStatus Code");
		}
}
