package cst438.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
   
   Optional<Flight> findByCode(String code);
      
   @Query("select f from Flight f where f.fromAirportId = ?1 and f.toAirportId = ?2")
   List<Flight> findByFromAirportId(String airportCodeFrom, String airportCodeTo);

}
