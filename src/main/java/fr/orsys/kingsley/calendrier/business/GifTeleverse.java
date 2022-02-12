package fr.orsys.kingsley.calendrier.business;

import javax.persistence.Entity;

@Entity
public class GifTeleverse extends Gif{
	
	private String nomFichierOriginal;

	public String getNomFichierOriginal() {
		return nomFichierOriginal;
	}

	public void setNomFichierOriginal(String nomFichierOriginal) {
		this.nomFichierOriginal = nomFichierOriginal;
	}
	
	
}