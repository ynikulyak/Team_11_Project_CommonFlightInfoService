package cst438.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.domain.Airport;
import cst438.service.AirportService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebMvcTest(AirportRestController.class)
public class AirportRestControllerTest {

   @MockBean
   private AirportService airportService;

   @Autowired
   private MockMvc mvc;

   // This object will be magically initialized by the initFields method below.
   private JacksonTester<Airport> json;

   private JacksonTester<List<Airport>> listJson;

   @BeforeEach
   public void setup() {
      JacksonTester.initFields(this, new ObjectMapper());
   }

   @Test
   public void getAirportInfo() throws Exception {

      // expected airport object
      Airport airport = new Airport("SJC", "San Jose International Airport", "San Jose, California, United States");

      // stub out the Airport Service class
      given(airportService.getAirportInfo("SJC")).willReturn(Optional.of(airport));

      // when
      MockHttpServletResponse response = mvc.perform(
            get("/api/airport/v1/SJC").contentType(MediaType.APPLICATION_JSON).content(json.write(airport).getJson()))
            .andReturn().getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).isEqualTo(json.write(airport).getJson());
   }

   @Test
   public void getAirportInfoNotFound() throws Exception {

      // stub out the City Service class
      given(airportService.getAirportInfo("AAA")).willReturn(Optional.empty());

      // when
      ResultActions response = mvc.perform(get("/api/airport/v1/AAA"));

      // then
      assertThat(response.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
   }

   @Test
   public void getAllAirportsInfo() throws Exception {

      // expected airport object
      List<Airport> airports = Arrays.asList(
            new Airport("SJC", "San Jose International Airport", "San Jose, California, United States"),
            new Airport("SAN", "San Diego International Airport", "San Diego, California, United States"),
            new Airport("SAT", "San Antonio International Airport", "San Antonio, Texas, United States"),
            new Airport("SFO", "San Francisco International Airport", "San Francisco, California, United States"));

      // stub out the Airport Service class
      given(airportService.getAllAirports("San")).willReturn(airports);

      // when
      MockHttpServletResponse response = mvc.perform(get("/api/airports/v1/San").contentType(MediaType.APPLICATION_JSON)
            .content(listJson.write(airports).getJson())).andReturn().getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).isEqualTo(listJson.write(airports).getJson());
   }

   @Test
   public void getAllAirportsInfoNotFoundAndReturnEmptyList() throws Exception {

      // stub out the City Service class
      given(airportService.getAllAirports("AAA")).willReturn(Collections.emptyList());

      // when
      ResultActions response =  mvc.perform(get("/api/airports/v1/AAA"));

      // then
      assertThat(response.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
   }
}
