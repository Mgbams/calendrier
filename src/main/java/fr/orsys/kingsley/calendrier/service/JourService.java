package fr.orsys.kingsley.calendrier.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.orsys.kingsley.calendrier.business.GifDistant;
import fr.orsys.kingsley.calendrier.business.Jour;

public interface JourService {
	Jour ajouterJour(LocalDate date);

	Jour enregistrerJour(Jour jour);

	List<Jour> recupererJours();

	Jour recupererJour(LocalDate idJour);

	Page<Jour> recupererJours(Pageable pageable);

	boolean supprimerJour(LocalDate date);

	Long compterJours();

	void associerGif(GifDistant gifDistant);
}
