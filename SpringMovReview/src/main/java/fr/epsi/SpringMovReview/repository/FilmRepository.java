package fr.epsi.SpringMovReview.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.epsi.SpringMovReview.entity.Film;


public interface FilmRepository extends MongoRepository<Film, String> {

    public Film findByIdFilm(String idFilm);
    
    public Film findByTitle(String original_title);

}