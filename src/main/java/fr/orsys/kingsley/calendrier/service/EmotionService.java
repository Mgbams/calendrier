package fr.orsys.kingsley.calendrier.service;

import java.util.List;

import fr.orsys.kingsley.calendrier.business.Emotion;

public interface EmotionService {
	
	Emotion ajouterEmotion(String nom, String code);

	List<Emotion> recupererEmotions();
}
