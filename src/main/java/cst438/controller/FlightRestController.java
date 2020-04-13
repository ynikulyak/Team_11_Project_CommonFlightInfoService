package cst438.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438.domain.Flight;
import cst438.domain.FlightInfo;
import cst438.service.FlightService;

/*
 * Rest controller to return flight objects in the form of JSON if found or
 * throws exception if not found (or empty list in case of all flights).
 */

@RestController
public class FlightRestController {

   @Autowired
   private FlightService flightService;

   @GetMapping("/api/flight/v1/{code}")
   public FlightInfo getFlight(@PathVariable("code") String code) {

      Optional<FlightInfo> flight = flightService.getFlightByCode(code);
      // return airportInfo object in form of JSON if object present
      if (flight.isPresent()) {
         return flight.get();
      }

      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found: " + code);
   }

   @GetMapping("/api/flights/v1/{from}/{to}")
   public List<FlightInfo> getAllFlights(@PathVariable("from") String from, @PathVariable("to") String to,
         @RequestParam("departure") String departureDate, @RequestParam("return") String returnDate) {
      try {
         List<FlightInfo> allFlights = new ArrayList<>(flightService.getAllFlights(from, to, departureDate));
         allFlights.addAll(flightService.getAllFlights(to, from, returnDate));
         return allFlights;
      } catch (IllegalArgumentException iae) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
      }
   }
}
