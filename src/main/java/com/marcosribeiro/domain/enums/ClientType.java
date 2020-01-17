package com.marcosribeiro.domain.enums;

public enum ClientType {

	PERSON(1, "Pessoa Física"),
	COMPANY(2, "Pessoa Jurídica");
	
	private int code;
	private String description;
	
	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (ClientType x : ClientType.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + code);
	}
	
}
