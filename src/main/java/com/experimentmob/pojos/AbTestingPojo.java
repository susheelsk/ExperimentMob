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
}
