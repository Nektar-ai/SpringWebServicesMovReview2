package fr.epsi.SpringMovReview.entity;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmRepository extends MongoRepository<Film, String> {

    public Film findByIdFilm(String idFilm);
    public List<Film> findByLastName(String lastName);

}