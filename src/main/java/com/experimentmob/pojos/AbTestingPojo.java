package com.experimentmob.pojos;

import java.util.List;

public class AbTestingPojo {
	private List<ExperimentPojo> experiments;

	public List<ExperimentPojo> getExperiments() {
		return experiments;
	}

	public void setExperiments(List<ExperimentPojo> experiments) {
		this.experiments = experiments;
	}

	public AbTestingPojo(List<ExperimentPojo> experiments) {
		super();
		this.experiments = experiments;
	}

	@SuppressWarnings("unused")
	public class ExperimentPojo {
		private String id;
		private String name;
		private String desc;
		private List<FieldsPojo> fields;
		private String createDate;
		private String finishedDate;
		private String expression;
	}

	@SuppressWarnings("unused")
	class FieldsPojo {
		private String name;
		private String type;
		private Object value;
	}
}
