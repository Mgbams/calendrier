package fr.orsys.kingsley.calendrier.service;

import java.util.List;

import fr.orsys.kingsley.calendrier.business.GifDistant;
import fr.orsys.kingsley.calendrier.business.Utilisateur;

public interface GifDistantService {
	void ajouterGifDistant(GifDistant gifDistant, Utilisateur utilisateur);
	
	List<GifDistant> recupererGifDistants();
}
