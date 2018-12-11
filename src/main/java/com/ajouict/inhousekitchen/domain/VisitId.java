package com.ajouict.inhousekitchen.domain;

import java.io.Serializable;

public class VisitId implements Serializable {

	private Long user;
	private Long host;

	public VisitId(){}

	public VisitId(Long user, Long host){
		this.user = user;
		this.host = host;
	}
}
