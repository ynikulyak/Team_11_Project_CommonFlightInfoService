package cst438.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438.domain.Flight;
import cst438.domain.Reservation;
import cst438.domain.passenger;

@Service
public class ReservationService {
	
	private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

	@Autowired
	ReservationRepository reservationRepository;
	
	public ReservationService() {
		
	}
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public Reservation createReservation(Reservation reserve) {
		// idReservation
		log.info("Request Reservation: "+reserve.toString());

		reserve.setIdReservation(idReservation);
		reserve.setRentalCar(rentalCar);
		reserve.setShuttle(shuttle);
		reserve.setHotel(hotel);
		reserve.setSeatPref(seatPref);
		reserve.setPassenger_idPassenger(passenger_idPassenger);

		// even though flight is in other service, we need to reserve it here, right?
		String Flight_idFlight = reserve.getFlight_idFlight();
		Flight flight = null;
		if (Flight_idFlight != null) flight = flightRepository.findByFlight_idFlight(Flight_idFlight);
		if (flight == null) {
			// Flight_idFlight is missing or invalid
			reserve.setMessage("Flight ID is not valid. "+Flight_idFlight);
			log.info("Request Not Processed: "+reserve.toString());
			return reserve;
		}

		// saves the reservation
		Reservation newReservation = reservationRepository.save(reserve);
		
		// returns updated Reservation
		return newReservation;
	}

	public boolean deleteReservation(int iidReservation) {
		Reservation r = reservationRepository.findById(idReservation);
		if (r !=null) {
			reservationRepository.delete(r);
			log.info("Reservation deleted: "+idReservation);
			return true;
		} else {
			log.info("Reservation delete failed. Not found. "+idReservation);
			return false;
		}
	}

	public Reservation getReservation(int idReservation) {
		log.info("Reservation retrieve: "+idReservation);
		Reservation r = reservationRepository.findById(idReservation);
		if (r != null) {
			log.info("Reservation retrieved: "+r.toString());
			return r;
		} else {
			log.info("Reservation retrieve failed. Not found.: "+idReservation);
			return null;
		}
	}
	
	public List<Reservation> getReservationsForUserName(String userName){
		List<Reservation> reservations =  reservationRepository.findByUserName(userName);
		log.info("Reservations for userName: "+userName+" noRervations returned="+reservations.size());
		return reservations;
	}

}
