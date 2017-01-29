package org.macchinetta.reqman.domain.model;

import java.util.Set;

import lombok.Data;

@Data
public class Request {

	private long id;

	private long excelId;
	
	private String title;

	private String description;
	
	private boolean isClosed;

	private Organization requestedBy;

	private Set<Tag> tags;
}
