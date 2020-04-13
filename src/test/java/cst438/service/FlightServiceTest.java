package cst438.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438.domain.Airport;
import cst438.domain.AirportRepository;
import cst438.domain.Flight;
import cst438.domain.FlightInfo;
import cst438.domain.FlightRepository;

@SpringBootTest
public class FlightServiceTest {

   @MockBean
   private FlightRepository flightRepository;

   @MockBean
   private AirportRepository airportRepository;

   @Autowired
   private FlightService flightService;

   @Test
   public void testFlightByCodeFound() throws Exception {
      // all needed mocks
      Flight flight = new Flight(1, "AS1922", "SFO", "LAX", "2020-05-01 08:30:00", "2020-05-01 10:30:00");

      given(flightRepository.findByCode("AS1922")).willReturn(Optional.of(flight));

      Airport airportFrom = new Airport("SFO", "San Francisco International Airport",
            "San Francisco, California, United States");
      given(airportRepository.findById("SFO")).willReturn(Optional.of(airportFrom));
      Airport airportTo = new Airport("LAX", "Los Angeles International Airport",
            "Los Angeles, California, United States");
      given(airportRepository.findById("LAX")).willReturn(Optional.of(airportTo));

      // real data
      Optional<FlightInfo> flightInfo = flightService.getFlightByCode("AS1922");

      assertThat(flightInfo.isPresent());

      FlightInfo info = flightInfo.get();
      assertThat(info.getFlightCode()).isEqualTo("AS1922");
      assertThat(info.getFromAirportCode()).isEqualTo("SFO");
      assertThat(info.getToAirportCode()).isEqualTo("LAX");
      assertThat(info.getAirportLocationFrom()).isEqualTo("San Francisco, California, United States");
      assertThat(info.getAirportLocationTo()).isEqualTo("Los Angeles, California, United States");
      assertThat(info.getAirportTitleFrom()).isEqualTo("San Francisco International Airport");
      assertThat(info.getAirportTitleTo()).isEqualTo("Los Angeles International Airport");
      assertThat(info.getDeparture()).isEqualTo("2020-05-01 08:30:00");
      assertThat(info.getArrival()).isEqualTo("2020-05-01 10:30:00");
   }

   @Test
   public void testAllFlights() throws Exception {

      //create all mocks
      List<Flight> flights = Arrays.asList(
            new Flight(1, "AS1922", "SFO", "LAX", "2020-05-01 08:30:00", "2020-05-01 10:30:00"),
            new Flight(2, "AS1921", "SFO", "LAX", "2020-05-01 10:30:00", "2020-05-01 12:30:00"),
            new Flight(3, "AS1920", "SFO", "LAX", "2020-05-01 05:30:00", "2020-05-01 07:30:00"),
            new Flight(4, "AS1923", "SFO", "LAX", "2020-05-01 18:30:00", "2020-05-01 20:30:00"));
      
      given(flightRepository.findFlights("SFO", "LAX", "2020-05-01")).willReturn(flights);
      
      Airport airportFrom = new Airport("SFO", "San Francisco International Airport",
            "San Francisco, California, United States");
      given(airportRepository.findById("SFO")).willReturn(Optional.of(airportFrom));
      
      Airport airportTo = new Airport("LAX", "Los Angeles International Airport",
            "Los Angeles, California, United States");
      
      given(airportRepository.findById("LAX")).willReturn(Optional.of(airportTo));
      
      List<FlightInfo> flightsInfo = Arrays.asList(
            new FlightInfo(1, "AS1922","SFO","LAX","2020-05-01 08:30:00","2020-05-01 10:30:00", "San Francisco International Airport", "San Francisco, California, United States", "Los Angeles International Airport",
                   "Los Angeles, California, United States"),
      new FlightInfo(2, "AS1921","SFO","LAX","2020-05-01 10:30:00","2020-05-01 12:30:00", "San Francisco International Airport","San Francisco, California, United States", "Los Angeles International Airport",
             "Los Angeles, California, United States"),
      new FlightInfo(3, "AS1920","SFO","LAX","2020-05-01 05:30:00","2020-05-01 07:30:00", "San Francisco International Airport", "San Francisco, California, United States", "Los Angeles International Airport",
             "Los Angeles, California, United States"),
      new FlightInfo(4, "AS1923","SFO","LAX","2020-05-01 18:30:00","2020-05-01 20:30:00", "San Francisco International Airport","San Francisco, California, United States", "Los Angeles International Airport",
             "Los Angeles, California, United States"));
      
      //real data
      List<FlightInfo> allFlights = flightService.getAllFlights("SFO", "LAX", "2020-05-01");
      
      assertThat(allFlights).containsAll(flightsInfo);
   }
   
   @Test
   public void testFlightNotFound() throws Exception {
      //mock
      given(flightRepository.findByCode("11111")).willReturn(Optional.empty());
      
      //real
      Optional<FlightInfo> flight = flightService.getFlightByCode("11111");
      
      assertThat(flight).isNotPresent();
   }
   
   @Test
   public void testAllFlightNotFound() throws Exception {
      //mock
      given(flightRepository.findFlights("000", "999", "2020-05-01")).willReturn(Collections.emptyList());
      
      List<FlightInfo> info = flightService.getAllFlights("000", "999", "2020-05-01");
      
      assertThat(info.isEmpty());
   }
}
