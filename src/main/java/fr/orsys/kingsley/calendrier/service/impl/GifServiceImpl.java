package fr.orsys.kingsley.calendrier.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.kingsley.calendrier.business.Gif;
import fr.orsys.kingsley.calendrier.dao.GifDao;
import fr.orsys.kingsley.calendrier.service.GifService;

@Service
public class GifServiceImpl implements GifService {
	private GifDao gifDao;

	// Ce constructeur va provoquer l'injection de dépendance

	public GifServiceImpl(GifDao gifDao) {
        super();
        this.gifDao = gifDao;
    }

	@Override
	public Gif ajouterGif(Gif gif) {
		return gifDao.save(gif);
	}

	@Override
	public List<Gif> recupererGifs() {
		return gifDao.findAll();
	}
}
