package fr.orsys.kingsley.calendrier.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.orsys.kingsley.calendrier.business.Jour;

public interface JourDao extends JpaRepository<Jour, LocalDate> {
	@Query("FROM Jour WHERE date = :date")
	Jour recupererJour(@Param("date") LocalDate idJour);
	
	@Query("SELECT count(*) FROM Jour")
	Long countJour();
}
