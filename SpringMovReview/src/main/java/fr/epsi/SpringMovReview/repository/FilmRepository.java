package fr.epsi.SpringMovReview.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.epsi.SpringMovReview.entity.Film;


public interface FilmRepository extends MongoRepository<Film, String> {

    public Film findByIdFilm(String idFilm);
//    public List<Film> findByLastName(String lastName);
//    public void saveData(String s);

}