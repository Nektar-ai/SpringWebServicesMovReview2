package fr.epsi.SpringMovReview.apifetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

	private Long id;
	private String overview;
	private String poster_path;
	private String release_date;
	private String original_title;
	private String backdrop_path;
	
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getTitle() {
		return original_title;
	}
	public void setTitle(String title) {
		this.original_title = title;
	}

	@Override
	public String toString() {
		
	return 		"{" +
				"\"id\":\"" + id +
				"\", \"original_title\":\"" + original_title +
				"\", \"overview\":\"" + overview.replaceAll("\"", "\\\'") +
				"\", \"poster_path\":\"" + poster_path + '"' +
				"\", \"backdrop_path\":\"" + backdrop_path + '"' +
				'}';		
	}	
}
