package com.marcosribeiro.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pendente"),
	APPROVED(2, "Aprovado"),
	CANCELED(3, "Cancelado");
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (PaymentStatus x : PaymentStatus.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid code: " + code);
	}
	
}
