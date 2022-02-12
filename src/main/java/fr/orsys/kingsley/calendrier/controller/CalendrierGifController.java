package fr.orsys.kingsley.calendrier.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.orsys.kingsley.calendrier.business.GifDistant;
import fr.orsys.kingsley.calendrier.business.Jour;
import fr.orsys.kingsley.calendrier.business.Utilisateur;
import fr.orsys.kingsley.calendrier.service.EmotionService;
import fr.orsys.kingsley.calendrier.service.GifDistantService;
import fr.orsys.kingsley.calendrier.service.GifService;
import fr.orsys.kingsley.calendrier.service.JourService;
import fr.orsys.kingsley.calendrier.service.ThemeService;
import fr.orsys.kingsley.calendrier.service.UtilisateurService;

@Controller
// @EnableScheduling
public class CalendrierGifController {
	private final UtilisateurService utilisateurService;
	private final ThemeService themeService;
	private final JourService jourService;
	private HttpSession httpSession;
	private static final int NB_JOURS_PAR_PAGE = 7;
	private final GifService gifService;
	private final GifDistantService gifDistantService;
	private final EmotionService emotionService;

	public CalendrierGifController(UtilisateurService utilisateurService, ThemeService themeService,
			HttpSession httpSession, JourService jourService, GifService gifService, GifDistantService gifDistantService,
			 EmotionService emotionService) {
		super();
		this.utilisateurService = utilisateurService;
		this.themeService = themeService;
		this.httpSession = httpSession;
		this.jourService = jourService;
		this.gifService = gifService;
		this.gifDistantService = gifDistantService;
		this.emotionService = emotionService;
	}

	@RequestMapping(value = { "/index", "/" })
	public ModelAndView accueil() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("utilisateurs", utilisateurService.recupererUtilisateursAyantReagirAuMoinsCinqFois());
		
		//deux écritures équivalentes
		//mav.getModel().put("nbInscrits", utilisateurService.recupererNbInscrits())
		mav.addObject("nbInscrits", utilisateurService.recupererNbInscrits());
		return mav;
	}

	@GetMapping(value = { "/inscription" })
	public ModelAndView inscriptionGet(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("themes", themeService.recupererThemes());
		mav.setViewName("inscription");
		return mav;
	}

	@PostMapping(value = { "/inscription" })
	public ModelAndView inscriptionPost(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
			BindingResult result) {
		if (result.hasErrors()) {
			return inscriptionGet(utilisateur);
		} else {
			System.out.println("utilisateur à ajouté " + utilisateur);
			utilisateurService.ajouterUtilisateur(utilisateur);
			// ModelAndView mav = accueil();
			ModelAndView mav = new ModelAndView("redirect:index");
			mav.addObject("notification", "Utilisateur ajout");
			return mav;
		}
	}

	@PostMapping("/connexion")
	public ModelAndView connexion(@RequestParam("EMAIL") String email,
			@RequestParam("MOT_DE_PASSE") String motDePasse) {
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(email, motDePasse);
		if (utilisateur == null) {
			ModelAndView mav = accueil();
			mav.addObject("notification", "Mot de passe et/ou email incorrect");
			return mav;
		} else {
			httpSession.setAttribute("utilisateur", utilisateur);
			ModelAndView mav = new ModelAndView("redirect:calendrier");
			return mav;
		}
	}

	@GetMapping("/calendrier")
	public ModelAndView calendrier(@PageableDefault(size = NB_JOURS_PAR_PAGE, sort = "date") Pageable pageable) {
		if (httpSession.getAttribute("utilisateur") == null) {
			return accueil();
		}
		ModelAndView mav = new ModelAndView("calendrier");
		mav.addObject("pageDeJours", jourService.recupererJours(pageable));
		// Met en session la page choisie
		if (pageable != null) {
			httpSession.setAttribute("numeroDePage", pageable.getPageNumber());
		}
		return mav;
	}

	@GetMapping("/placerGifDistant")
	public ModelAndView placerGifDistantGet(@RequestParam("ID_JOUR") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		ModelAndView mav = new ModelAndView("placerGifDistant");
		GifDistant gifDistant = new GifDistant();
		Jour jour = jourService.recupererJour(date);
		gifDistant.setJour(jour);
		mav.addObject("gifDistant", gifDistant);
		return mav;
	}

	@PostMapping("/placerGifDistant")
	public ModelAndView placerGifDistantPost(@Valid @ModelAttribute GifDistant gifDistant, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mav = placerGifDistantGet(gifDistant.getJour().getDate());
			mav.addObject("gifDistant", gifDistant);
			return mav;
		} else {
			gifDistantService.ajouterGifDistant(gifDistant, (Utilisateur) httpSession.getAttribute("utilisateur"));
			ModelAndView mav = new ModelAndView("redirect:calendrier");
			mav.addObject("gifDistant", gifDistant);
			return mav;
		}

	}
	
//	@GetMapping("/reagir")
//    public ModelAndView reagirGet(@RequestParam("ID_GIF") Long idGif) {        
//        ModelAndView mav = new ModelAndView("reagir");
//        mav.addObject("gif", gifService.recupererGif(idGif));
//        mav.addObject("emotions", emotionService.recupererEmotions());
//        return mav;
//    }
//
//    @PostMapping("/reagir")
//    public ModelAndView reagirPost(@RequestParam("ID_GIF") Long idGif, @RequestParam("ID_EMOTION") Long idEmotion) {        
//        reactionService.ajouterReaction(idGif, idEmotion, (Utilisateur) httpSession.getAttribute("utilisateur"));
//        if (httpSession.getAttribute("numeroDePage")!=null)
//        {
//            return new ModelAndView("redirect:calendrier?page=" + httpSession.getAttribute("numeroDePage"));
//        }
//        else {
//            return new ModelAndView("redirect:calendrier");
//        }
//    }
	
	@GetMapping(value= {"/deconnexion"})
	public ModelAndView deconnexion() {
		httpSession.invalidate();
		ModelAndView mav = new ModelAndView("redirect:index");
		mav.addObject("notification", "Au revoir");
		return mav;
	}

}
