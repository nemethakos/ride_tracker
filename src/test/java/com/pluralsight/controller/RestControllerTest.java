package com.pluralsight.controller;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

public class RestControllerTest {

	@Test(timeout = 30000)
	public void testCreateRide() {
		var restTemplate = new RestTemplate();

		var ride = new Ride();
		ride.setName(new Date().toString() + "Bobsled Trail Ride");
		ride.setDuration(35);

		var result = restTemplate.postForObject("http://localhost:8080/ride_tracker/rides", ride, Ride.class);
		ride = null;
		
		System.out.format("Newly created ride: %s%n", result.toString());
	}

	@Test(timeout = 30000)
	public void testGetRides() {
		long startTime = System.nanoTime();
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange("http://localhost:8080/ride_tracker/rides",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Ride>>() {
				});
		long endTime = System.nanoTime();

		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}

		System.out.format("REST call time: %d", endTime - startTime);
	}
	
	@Test(timeout = 30000)
	public void testGetRide() {
		var restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		
		System.out.format("Result: %s%n", ride);
		
	}
	
	@Test(timeout = 30000)
	public void testUpdateRide() {
		var restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		
		ride.setDuration(ride.getDuration()+1);
		
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		
		System.out.format("Result: %s%n", ride);
		
	}
	
	@Test(timeout = 30000)
	public void testBatchUpdate() {
		var restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
		
		
	}
	
	@Test(timeout = 30000)
	public void testDelete() {
		var restTemplate = new RestTemplate();
		
		restTemplate.delete("http://localhost:8080/ride_tracker/delete/11");
		
	}
	
	
	@Test(timeout = 30000)
	public void testException() {
		var restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);
		
	}
}
