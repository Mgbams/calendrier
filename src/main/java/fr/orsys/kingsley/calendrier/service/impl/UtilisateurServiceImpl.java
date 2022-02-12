package fr.orsys.kingsley.calendrier.service.impl;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import fr.orsys.kingsley.calendrier.business.Utilisateur;
import fr.orsys.kingsley.calendrier.dao.UtilisateurDao;
import fr.orsys.kingsley.calendrier.service.ThemeService;
import fr.orsys.kingsley.calendrier.service.UtilisateurService;
import fr.orsys.kingsley.calendrier.util.NbInscrits;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

private UtilisateurDao utilisateurDao;
private ThemeService themeService;
    
    // Ce constructeur va provoquer l'injection de dépendance
    
    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao, ThemeService themeService) {
        super();
        this.utilisateurDao = utilisateurDao;
        this.themeService = themeService;
    }

//    @Override
//    public Utilisateur ajouterUtilisateur(String nom, String prenom, String email, String motDePasse, Theme theme) {
//    	// utilisateur.setNbPoints(utilisateur.);
//    	Utilisateur utilisateur = new Utilisateur();
//    	utilisateur.setNom(nom);
//    	utilisateur.setPrenom(prenom);
//    	utilisateur.setPrenom(prenom);
//    	utilisateur.setEmail(email);
//    	utilisateur.setMotDePasse(motDePasse);
//    	utilisateur.setTheme(theme);
//        return utilisateurDao.save(new Utilisateur());
//    }

    @Override
    public List<Utilisateur> recupererUtilisateurs() {
        return utilisateurDao.findAll();
    }

	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {
		return utilisateurDao.findLastByEmailAndMotDePasse(email, motDePasse);
	}

	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.save(utilisateur);
	}
	
	 @Override
	    public Utilisateur ajouterUtilisateurAleatoire() {
	        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("fr-FR"), new RandomService());
	        
	        Utilisateur utilisateur = new Utilisateur();
	        utilisateur.setNom(fakeValuesService.letterify("?????"));
	        utilisateur.setPrenom(fakeValuesService.letterify("?????"));
	        utilisateur.setEmail(fakeValuesService.letterify("?????@orsys.fr"));
	        utilisateur.setMotDePasse(fakeValuesService.letterify("?????"));
	        Faker faker = new Faker(new Locale("fr-FR"));
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(2018, 1, 1);
	        Date dateDebut = calendar.getTime();
	        calendar = Calendar.getInstance();
	        Date dateFin = calendar.getTime();
	        Date dateAleatoire = faker.date().between(dateDebut, dateFin);
	        calendar.setTime(dateAleatoire);
	        System.out.println(dateAleatoire);
	        // Big up Moulaye !
	        utilisateur.setDateHeureInscription(dateAleatoire.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	        Random random = new Random();
	        utilisateur.setTheme(themeService.recupererTheme(Long.valueOf(1 + random.nextInt(2))));
	        utilisateurDao.save(utilisateur);
	        return utilisateur;
	    }

	@Override
	public List<NbInscrits> recupererNbInscrits() {
		return utilisateurDao.findNbInscrits();
	}

	@Override
	public List<Utilisateur> recupererUtilisateursAyantReagirAuMoinsCinqFois() {
		return utilisateurDao.findUtilisateursHavingAtLeastFiveReactions();
	}
	
	@Override
	public Utilisateur majUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.save(utilisateur);
	}

}
