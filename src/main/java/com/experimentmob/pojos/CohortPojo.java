package com.experimentmob.pojos;

public class CohortPojo {

	private String name;
	private String description;
	private String file;

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public String getFile() {
		return file;
	}

	public CohortPojo(String name, String description,String file) {
		this.name = name;
		this.description = description;
		this.file = file;
	}

}
