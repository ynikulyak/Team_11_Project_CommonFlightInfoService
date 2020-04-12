package cst438.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	Reservation findById(int idReservation);
	
	List<Reservation> findBypassenger_idPassenger(int passenger_idPassenger);

}