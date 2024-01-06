package com.infomerica.ars.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infomerica.ars.DAO.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query(value = "SELECT DISTINCT BOARDINGPOINT FROM FLIGHTS", nativeQuery = true)
	List<String> getDistinctBoardingPoint();
	
	@Query(value = "SELECT DISTINCT DESTINATION FROM FLIGHTS", nativeQuery = true)
	List<String> getDistinctDestination();
	
	@Query(value = "SELECT DISTINCT STARTDATE FROM FLIGHTS", nativeQuery = true)
	List<Date> getDistinctDates();
	
	@Query(value = "SELECT * FROM flights WHERE BOARDINGPOINT = ?1 AND DESTINATION = ?2 AND startdate = TO_DATE(?3 || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS')", nativeQuery = true)
	List<Flight> getFlights(String boardingPoint, String destination, String dates);

	
}
