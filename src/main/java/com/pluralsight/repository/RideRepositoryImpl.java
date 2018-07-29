package com.pluralsight.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.Util;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {

		List<Ride> rides = jdbcTemplate.query("select * from ride", (rs, rowNum) -> Util.toRide(rs, rowNum));

		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {

		// get back id via GeneratedKeyHolder
//		var keyHolder = new GeneratedKeyHolder();
//		jdbcTemplate.update((con) -> {
//			var result = con.prepareStatement("insert into ride (name, duration) values (?,?)", new String[] { "id" });
//			result.setString(1, ride.getName());
//			result.setInt(2, ride.getDuration());
//			return result;
//		}, keyHolder);
//		
//		Number id = keyHolder.getKey();

		// get back id when inserting with SimpleJdbcInsert
		var insert = new SimpleJdbcInsert(jdbcTemplate);

		var columns = Arrays.asList("name", "duration");
		insert.setTableName("ride");
		insert.setColumnNames(columns);

		var data = new HashMap<String, Object>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());

		insert.setGeneratedKeyName("id");

		Number key = insert.executeAndReturnKey(data);
		var id = key;
		System.out.println("key=" + key);

		return getRide(id.intValue());

		// simple approach
//		jdbcTemplate.update("insert into ride (name, duration) values (?,?)", 
//				ride.getName(), ride.getDuration());

	}

	@Override
	public Ride getRide(Integer id) {

		return jdbcTemplate.queryForObject("select * from ride where id = ?", (rs, rowNum) -> Util.toRide(rs, rowNum),
				id);
	}

	@Override
	public Ride updateRide(Ride ride) {

		jdbcTemplate.update("update ride set name=?, duration=? where id=?", ride.getName(), ride.getDuration(),
				ride.getId());

		return ride;
	}

	@Override
	public void updateRideList(List<Object[]> pairs) {
		
		jdbcTemplate.batchUpdate("update ride set ride_date=? where id=?", pairs);
		
	}

	@Override
	public void delete(Integer id) {
		// jdbcTemplate implementation
		//jdbcTemplate.update("delete from ride where id = ?", id);
		
		// namedparameterJdbcTemplate implementation
		var namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate); 
		var paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		
		namedTemplate.update("delete from ride where id=:id", paramMap);
		
		
	}

}
