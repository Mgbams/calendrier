package fr.orsys.kingsley.calendrier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.calendrier.business.Reaction;

public interface ReactionDao extends JpaRepository<Reaction, Long> {

}
