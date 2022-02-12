package fr.orsys.kingsley.calendrier.service;

import java.util.List;

import fr.orsys.kingsley.calendrier.business.Theme;

public interface ThemeService {
	Theme ajouterTheme(String nom);

	List<Theme> recupererThemes();

	Theme recupererTheme(Long themeId);
}
