package com.ajouict.inhousekitchen.domain;

import javax.persistence.*;

@Entity
@IdClass(VisitId.class)
public class Visit {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "host_id")
	private Host host;

	private Long count;

	public Visit(){}


}
