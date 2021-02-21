package com.cg.citipark.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.citipark.beans.ParkingSlots;
import com.cg.citipark.exceptions.NoSuchParkingSlotException;
import com.cg.citipark.exceptions.ParkingSlotNotAvailableException;
import com.cg.citipark.repository.ParkingRepository;


@Service
public class ParkingServiceImpl  implements ParkingService{
	@Autowired
	private ParkingRepository parkingRepository;
	
	/*
	Book Slot
	*/
	@Override
	public ParkingSlots addSlot(ParkingSlots parkingSlots) 
	{
		boolean check=checkAvailability(parkingSlots.getParkingDate(), parkingSlots.getParkingTime());
		if(check)
		{
		return parkingRepository.save(parkingSlots);
		}
		else
			throw new ParkingSlotNotAvailableException("Parking Slot not available");
	}
	/*
	Delete Slot
	*/
	@Override
	public ParkingSlots deleteSlot(Long parkingSlotId)   {
		Optional<ParkingSlots> rep = parkingRepository.findById(parkingSlotId);

		if (rep.isEmpty())
			throw new NoSuchParkingSlotException("ParkingSlot Id not found");
		else
			parkingRepository.deleteById(parkingSlotId);
			return rep.get();
		
	}
	/*
	Check Availability
	*/
	@Override
	public boolean checkAvailability(LocalDate parkingDate, String parkingTime) 
	{ 
		
		List<ParkingSlots> slots=parkingRepository.findSlotsforDateandTime(parkingDate, parkingTime);
		
		System.out.println("slots:"+slots);
		
		if(slots.isEmpty())
			return true;
		else
			throw new ParkingSlotNotAvailableException("Parking Slot not available");
			
	}
	/*
	Get Slots based on Parkinglot Id
	*/
	
	@Override
	public ParkingSlots getSlotById(long parkingSlotId) {
		Optional<ParkingSlots> optional=	parkingRepository.findById(parkingSlotId);  
		if(!optional.isPresent())		 
			throw new NoSuchParkingSlotException("Parking Slot Details not found for id "+parkingSlotId);	
		return optional.get();    
	}
	
	/*
	Find All Slots
	*/
	@Override
	public List<ParkingSlots> findAllSlots()
	{
		List<ParkingSlots> slots = parkingRepository.findAll(); 
		System.out.println("Slots are"+slots);
		return slots;
	}
	/*
	Get Slots based on Parking Floor Id
	*/
	@Override
	public List<ParkingSlots> findSlotsByFloor(int parkingFloorId) {
		
		return parkingRepository.findByFloor(parkingFloorId);
	}
	
	
	
}
