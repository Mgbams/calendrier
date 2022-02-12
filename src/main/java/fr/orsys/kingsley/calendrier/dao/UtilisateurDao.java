package fr.orsys.kingsley.calendrier.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.orsys.kingsley.calendrier.business.Utilisateur;
import fr.orsys.kingsley.calendrier.util.NbInscrits;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {
	Utilisateur findLastByEmailAndMotDePasse(String email, String motDePasse);
	
	Utilisateur findByIdAndEmail(Long idUtilisateur, String email);
	
	@Query("FROM Utilisateur WHERE theme.nom = 'Dark'")
	List<Utilisateur> findUtilisateursUsingDarkTheme();
	
	@Query("SELECT u FROM Utilisateur u, Reaction r WHERE r.utilisateur =  u GROUP By r.utilisateur HAVING COUNT(r.utilisateur) >= 5")
	List<Utilisateur> findUtilisateursHavingAtLeastFiveReactions();
	
	// Cette méthode renvoie le nombre d'inscrits par année et par mois
	// Elle utilise la classe util NbInscrits
	@Query("SELECT new fr.orsys.kingsley.calendrier.util.NbInscrits(year(utilisateur.dateHeureInscription), month(utilisateur.dateHeureInscription), count(utilisateur)) " + 
		     "FROM Utilisateur utilisateur " +
		     "GROUP BY year(utilisateur.dateHeureInscription), month(utilisateur.dateHeureInscription)")
	List<NbInscrits> findNbInscrits(); 
}
