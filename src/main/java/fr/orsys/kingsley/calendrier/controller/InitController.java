package fr.orsys.kingsley.calendrier.controller;

import java.time.LocalDate;
import java.time.Month;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.kingsley.calendrier.service.EmotionService;
import fr.orsys.kingsley.calendrier.service.JourService;
import fr.orsys.kingsley.calendrier.service.ThemeService;

@Controller
public class InitController {

	private final ThemeService themeService;
	private final EmotionService emotionService;
	private final JourService jourService;

	public InitController(ThemeService themeService, EmotionService emotionService, JourService jourService) {
		super();
		this.themeService = themeService;
		this.emotionService = emotionService;
		this.jourService = jourService;
	}

	@PostConstruct
	private void init() {
		if (themeService.recupererThemes().isEmpty()) {
			themeService.ajouterTheme("batchata");
			themeService.ajouterTheme("dark");
		}

		if (emotionService.recupererEmotions().isEmpty()) {
			emotionService.ajouterEmotion("Souriant", "&#x1F600;");
			emotionService.ajouterEmotion("Monocle", "&#x1F9D0;");
			emotionService.ajouterEmotion("Bisous", "&#x1F618;");
			emotionService.ajouterEmotion("Coeur", "&#x1F60D;");
			emotionService.ajouterEmotion("PTDR", "&#x1F923;");
		}

		// les 31 jours de Janvier 2022
		// (int)(20 + Math.random() * 31) // generate random numbers in the given range
		if (jourService.recupererJours().isEmpty()) {
			int year = LocalDate.now().getYear();
			Month month = LocalDate.now().getMonth();
			LocalDate date = LocalDate.of(year, month.getValue(), 1);
			while (month.equals(date.getMonth())) {
				jourService.ajouterJour(date);
				date = date.plusDays(1);
			}
		}
	}
}
