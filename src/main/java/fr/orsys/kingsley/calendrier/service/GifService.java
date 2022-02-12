package fr.orsys.kingsley.calendrier.service;

import java.util.List;

import fr.orsys.kingsley.calendrier.business.Gif;

public interface GifService {
	Gif ajouterGif(Gif gif);
	
	List<Gif> recupererGifs();
}
