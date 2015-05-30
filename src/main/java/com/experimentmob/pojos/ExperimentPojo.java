package com.experimentmob.pojos;

import java.util.List;

public class ExperimentPojo {
	private String id;
	private String name;
	private String desc;
	private List<FieldsPojo> fields;
	private String createDate;
	private String finishedDate;
	private String expression;

	public ExperimentPojo(String id, String name, String desc, List<FieldsPojo> fields, String createDate, String finishedDate, String expression) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.fields = fields;
		this.createDate = createDate;
		this.finishedDate = finishedDate;
		this.expression = expression;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getFinishedDate() {
		return finishedDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public List<FieldsPojo> getFields() {
		return fields;
	}

	public String getExpression() {
		return expression;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}
}
