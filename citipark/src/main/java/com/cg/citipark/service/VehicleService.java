package com.cg.citipark.service;

import java.util.List;
import java.util.Optional;

import com.cg.citipark.beans.Vehicle;

public interface VehicleService {
	public Vehicle addUsersVehicle(Vehicle vehicle) ;
	public Vehicle removeVehicleByVehicleId(Long vehicleId);
	public Optional<Vehicle  > findByVehicleId(Long vehicleId);
	public Optional<Vehicle> findByVehicleNumber(String vehicleNumber,Long vehicleId);
	public List<Vehicle> findAllVehicles();
	public Vehicle modifyVehicle(Vehicle vehicle);
	
}
