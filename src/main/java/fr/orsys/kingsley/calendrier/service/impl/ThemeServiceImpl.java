package fr.orsys.kingsley.calendrier.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.kingsley.calendrier.business.Theme;
import fr.orsys.kingsley.calendrier.dao.ThemeDao;
import fr.orsys.kingsley.calendrier.service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {
	private ThemeDao themeDao;
    
    // Ce constructeur va provoquer l'injection de dépendance
    
    public ThemeServiceImpl(ThemeDao themeDao) {
        super();
        this.themeDao = themeDao;
    }

    @Override
    public Theme ajouterTheme(String nom) {
        return themeDao.save(new Theme(nom));
    }

    @Override
    public List<Theme> recupererThemes() {
        return themeDao.findAll();
    }
    
    @Override
    public Theme recupererTheme(Long id) {
        return themeDao.findById(id).orElse(null);
    }
}
