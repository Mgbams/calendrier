package fr.orsys.kingsley.calendrier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.orsys.kingsley.calendrier.business.Theme;

@RepositoryRestResource(exported = true)
public interface ThemeDao extends JpaRepository<Theme, Long> {

}
