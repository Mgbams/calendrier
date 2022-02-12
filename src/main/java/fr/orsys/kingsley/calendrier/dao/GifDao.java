package fr.orsys.kingsley.calendrier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.calendrier.business.Gif;

public interface GifDao extends JpaRepository<Gif, Long> {
	
//	@Query(name = "FROM Gif g ORDER BY size(g.reactions) DESC")
//	List<Gif> findGifSortedByReactions();
}
