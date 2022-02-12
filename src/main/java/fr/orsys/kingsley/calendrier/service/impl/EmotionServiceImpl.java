package fr.orsys.kingsley.calendrier.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.kingsley.calendrier.business.Emotion;
import fr.orsys.kingsley.calendrier.dao.EmotionDao;
import fr.orsys.kingsley.calendrier.service.EmotionService;

@Service
public class EmotionServiceImpl implements EmotionService {
	private EmotionDao emotionDao;

	// Ce constructeur va provoquer l'injection de dépendance
	public EmotionServiceImpl(EmotionDao emotionDao) {
		super();
		this.emotionDao = emotionDao;
	}

	@Override
	public Emotion ajouterEmotion(String nom, String code) {
		return emotionDao.save(new Emotion(nom, code));
	}

	@Override
	public List<Emotion> recupererEmotions() {
		return emotionDao.findAll();
	}
}
