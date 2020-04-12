package cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438.domain.*;
import cst438.service.reservationService;

@RestController
public class ReservationRestController {
	
	@Autowired
	private ReservationService reservationService;
	
    // not sure what else we need
	
}
