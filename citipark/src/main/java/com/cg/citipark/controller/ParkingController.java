package com.cg.citipark.controller;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.citipark.beans.ParkingSlots;
import com.cg.citipark.service.AdminService;
import com.cg.citipark.service.ParkingService;
@RestController																																
public class ParkingController {
	
	@Autowired
	ParkingService parkingService;
	@Autowired
	AdminService adminService;
	/*
	Booking a Slot
	*/
	@PostMapping("/api/bookslot")
	public ResponseEntity<?> bookSlot(@RequestBody ParkingSlots parkingSlot) 
	{
		parkingService.addSlot(parkingSlot);
		return new ResponseEntity<ParkingSlots>(parkingSlot, HttpStatus.OK);
	}
	/*
	Deleting a Slot
	*/
	@DeleteMapping("/api/deleteSlotById/{parkingSlotId}")
	public ResponseEntity<String> deleteReportById(@PathVariable Long parkingSlotId)  {
		parkingService.deleteSlot(parkingSlotId);
		return new ResponseEntity<String>("Slot Deleted Successfully", HttpStatus.GONE);
	}
	

	@GetMapping("/api/getSlotById/{parkingSlotId}")
	public ResponseEntity<ParkingSlots> getSlotById(@PathVariable Long parkingSlotId)  {
		return new ResponseEntity<ParkingSlots>(parkingService.getSlotById(parkingSlotId), HttpStatus.OK);
	}
	
	/*
	Check Availability of slot
	*/
	@GetMapping("/api/checkAvailability/{parkingDate}/{parkingTime}")
	public ResponseEntity<?> checkAvailability(@PathVariable  String parkingDate, @PathVariable String parkingTime) 
	{
		
		
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate d=LocalDate.parse(parkingDate,formatter);
		return new ResponseEntity<Boolean>(parkingService.checkAvailability(d, parkingTime),HttpStatus.OK);
		
		
	}
	/*
	View all Slots
	*/
	@GetMapping("/api/veiw")
	public ResponseEntity<List<ParkingSlots>> veiwAllSlots()
	{
		return new ResponseEntity<List<ParkingSlots>>(parkingService.findAllSlots(), HttpStatus.OK);
	}
	/*
	Find Slots based on parking floor Id
	*/
	@GetMapping("/api/find/floor/{parkingFloorId}")
	public ResponseEntity<List<ParkingSlots>> getParkingSlotsByFloor(@PathVariable int parkingFloorId)
	{
		
		return new ResponseEntity<List<ParkingSlots>>(parkingService.findSlotsByFloor(parkingFloorId),HttpStatus.OK);
	}
	
	
	
}

