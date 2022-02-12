package fr.orsys.kingsley.calendrier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.orsys.kingsley.calendrier.business.Emotion;

@RepositoryRestResource(exported = true)
public interface EmotionDao extends JpaRepository<Emotion, Long> {

}
