package fr.orsys.kingsley.calendrier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.calendrier.business.GifDistant;

public interface GifDistantDao extends JpaRepository<GifDistant, Long> {

}
