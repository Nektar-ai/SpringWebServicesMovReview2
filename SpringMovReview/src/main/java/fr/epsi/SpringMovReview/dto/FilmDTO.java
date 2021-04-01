package fr.epsi.SpringMovReview.dto;

import org.springframework.data.annotation.Id;

import fr.epsi.SpringMovReview.entity.Film;

public class FilmDTO {

    public String name;
    
    public FilmDTO() {
    }

    public FilmDTO(String name) {
        this.name = name;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}