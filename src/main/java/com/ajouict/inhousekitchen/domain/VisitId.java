package com.ajouict.inhousekitchen.domain;

import java.io.Serializable;

public class VisitId implements Serializable {

	private Long member;
	private Long host;

	public VisitId(){}

	public VisitId(Long member, Long host){
		this.member = member;
		this.host = host;
	}
}
