package fr.orsys.kingsley.calendrier.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.kingsley.calendrier.business.GifDistant;
import fr.orsys.kingsley.calendrier.business.Utilisateur;
import fr.orsys.kingsley.calendrier.dao.GifDistantDao;
import fr.orsys.kingsley.calendrier.service.GifDistantService;
import fr.orsys.kingsley.calendrier.service.JourService;

@Service
public class GifDistantServiceImpl implements GifDistantService {
	
	private GifDistantDao gifDistantDao;
	private JourService jourService;

	// Ce constructeur va provoquer l'injection de dépendance

	public GifDistantServiceImpl(GifDistantDao gifDistantDao, JourService jourService) {
        super();
        this.gifDistantDao = gifDistantDao;
        this.jourService = jourService;
    }

	@Override
	public void ajouterGifDistant(GifDistant gifDistant, Utilisateur utilisateur) {
		gifDistant.setDateHeureAjout(LocalDateTime.now());
		gifDistant.setUtilisateur(utilisateur);
		
		gifDistantDao.save(gifDistant);
		utilisateur.setNbPoints(utilisateur.getNbPoints() - gifDistant.getJour().getNbPoints());
		jourService.associerGif(gifDistant);
	}

	@Override
	public List<GifDistant> recupererGifDistants() {
		return gifDistantDao.findAll();
	}

}
