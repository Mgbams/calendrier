package fr.orsys.kingsley.calendrier.service;

import java.util.List;

import fr.orsys.kingsley.calendrier.business.Utilisateur;
import fr.orsys.kingsley.calendrier.util.NbInscrits;

public interface UtilisateurService {
	Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
	
	List<Utilisateur> recupererUtilisateurs();
	
	Utilisateur recupererUtilisateur(String email, String motDePasse);

	Utilisateur ajouterUtilisateurAleatoire();
	
	List<NbInscrits> recupererNbInscrits();
	
	List<Utilisateur> recupererUtilisateursAyantReagirAuMoinsCinqFois();

	Utilisateur majUtilisateur(Utilisateur utilisateur);

	// Utilisateur ajouterUtilisateur(String nom, String prenom, String email, String motDePasse, Theme theme);
}
