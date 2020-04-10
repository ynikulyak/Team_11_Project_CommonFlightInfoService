package cst438.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
   
   Optional<Airport> findById(String id);
   
   @Query("select a from Airport a where a.id = ?1 or a.title like ?1%")
   List<Airport> findByTitleStartingWith(String prefix);
}
 