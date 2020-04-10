package cst438.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438.domain.Flight;
import cst438.domain.FlightInfo;
import cst438.service.FlightService;

/*
 * Rest controller to return flight objects in form of json if found or
 * throws exception if not found
 */

@RestController
public class FlightRestController {

   @Autowired
   private FlightService flightService;

   @GetMapping("/api/flight/v1/{code}")
   public FlightInfo getFlight(@PathVariable("code") String code) {

      Optional<FlightInfo> flight = flightService.getFlightByCode(code);
      // return airportInfo object in form of json if object present
      if (flight.isPresent()) {
         return flight.get();
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   }

   @GetMapping("/api/flights/v1/{from}/{to}")
   public List<FlightInfo> getAllFlights(@PathVariable("from") String from, @PathVariable("to") String to) {

      List<FlightInfo> flights = flightService.getAllFlights(from, to);

      return flights;
   }
}
