package fr.epsi.SpringMovReview.dto;

public class FilmDTO {

    public String title;
    
    public FilmDTO() {
    }

    public FilmDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}