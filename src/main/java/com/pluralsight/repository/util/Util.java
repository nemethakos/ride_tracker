package com.pluralsight.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pluralsight.model.Ride;

public class Util {

	public static Ride toRide(ResultSet rs, int rowNum) throws SQLException {
		return Ride.builder().withId(rs.getInt("id"))//
				.withDuration(rs.getInt("duration"))//
				.withName(rs.getString("name"))//
				.build();
	}

	

}
