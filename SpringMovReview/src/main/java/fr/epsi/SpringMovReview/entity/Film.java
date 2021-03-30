package fr.epsi.SpringMovReview.entity;


import org.springframework.data.annotation.Id;


public class Film {

    @Id
    public String id;

    public String idFilm;
    public int likes;

    public Film() {
    }

    public Film(String id) {
        this.idFilm = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Film[id=%s, idFilm='%s', likes='%s']",
                id, idFilm, likes);
    }
}
