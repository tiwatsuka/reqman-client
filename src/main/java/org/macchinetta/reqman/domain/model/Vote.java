package org.macchinetta.reqman.domain.model;

import lombok.Data;

@Data
public class Vote {

	private long id;

	private Organization organization;

	private Request request;

	private int priority;

	private int step;

	private int period;

}
