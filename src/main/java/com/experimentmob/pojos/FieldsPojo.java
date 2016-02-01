package com.experimentmob.pojos;

public class FieldsPojo {
	
	private String name;
	private String type;
	private String value;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public FieldsPojo(String name, String type, String value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}
}
