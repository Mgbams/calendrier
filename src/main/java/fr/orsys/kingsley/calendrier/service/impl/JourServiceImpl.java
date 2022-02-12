package fr.orsys.kingsley.calendrier.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.orsys.kingsley.calendrier.business.GifDistant;
import fr.orsys.kingsley.calendrier.business.Jour;
import fr.orsys.kingsley.calendrier.dao.JourDao;
import fr.orsys.kingsley.calendrier.service.JourService;

@Service
public class JourServiceImpl implements JourService {
	private JourDao jourDao;

	// Ce constructeur va provoquer l'injection de dépendance
	public JourServiceImpl(JourDao jourDao) {
		super();
		this.jourDao = jourDao; 
	}

//	@Override
//	public Jour ajouterJour(LocalDate date, int nbPoints) {
//		return jourDao.save(new Jour(date, nbPoints));
//	}
	
	 @Override
	    public Jour ajouterJour(LocalDate date) {
		 	Random random = new Random();
	        return jourDao.save(new Jour(date, 20 + random.nextInt(31)));
	    }
	 

		@Override
		public Page<Jour> recupererJours(Pageable pageable) {
			return jourDao.findAll(pageable);
		}
		

	@Override
	public List<Jour> recupererJours() {
		return jourDao.findAll();
	}

	@Override
	public Jour enregistrerJour(Jour jour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jour recupererJour(LocalDate idJour) {
		return jourDao.recupererJour(idJour);
	}

	@Override
	public boolean supprimerJour(LocalDate date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long compterJours() {
		return jourDao.countJour();
	}

	@Override
	public void associerGif(GifDistant gifDistant) {
		Jour jour = gifDistant.getJour();
		jour.setGif(gifDistant);
		jourDao.save(jour);
	}
}
