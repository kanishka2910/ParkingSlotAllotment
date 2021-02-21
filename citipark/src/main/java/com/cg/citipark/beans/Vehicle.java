package com.cg.citipark.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VehiclesTable")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vehiclId;
	private String vehicleNumber;
	private String vehicleCompany;
	private String vehicleModel;
	@ManyToOne(targetEntity = User.class,cascade = CascadeType.PERSIST)
	private User owner;
	public Long getVehiclId() {
		return vehiclId;
	}
	public void setVehiclId(Long vehiclId) {
		this.vehiclId = vehiclId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleCompany() {
		return vehicleCompany;
	}
	public void setVehicleCompany(String vehicleCompany) {
		this.vehicleCompany = vehicleCompany;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Vehicle(Long vehiclId, String vehicleNumber, String vehicleCompany, String vehicleModel, User owner) {
		super();
		this.vehiclId = vehiclId;
		this.vehicleNumber = vehicleNumber;
		this.vehicleCompany = vehicleCompany;
		this.vehicleModel = vehicleModel;
		this.owner = owner;
	}
	
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
