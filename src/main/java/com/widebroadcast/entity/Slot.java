package com.widebroadcast.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Slot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer slotId;
	private String programName;

	@ManyToOne
	@JoinColumn(name="plan_type_id")
	private PlanType plantype;

	private String status;

}
