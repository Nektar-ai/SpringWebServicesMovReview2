package fr.epsi.SpringMovReview.entity;


import org.springframework.data.annotation.Id;

public class Film {

    @Id
    public String id;
    public String idFilm;
    public String title;
    public int likes;
    
    public Film() {
    }

    public Film(String id) {
        this.idFilm = id;
    }

    public String getTitle() {
		return title;
	}

	public void setName(String title) {
		this.title = title;
	}

	public Film(String id, int likes) {
        this.idFilm = id;
        this.likes = likes;
    }
    
    public Film (Film f) {
    	this.id = f.getId();
    	this.idFilm = f.getIdFilm();
    	this.likes = f.getLikes();
    	this.title = f.getTitle();
    }
    
    public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}

	public String getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(String idFilm) {
		this.idFilm = idFilm;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
    
    @Override
    public String toString() {
        return String.format(
                "Film[id=%s, idFilm='%s', likes='%s']",
                id, idFilm, likes);
    }
    
    public void like() {
    	this.likes++;
    }
}
