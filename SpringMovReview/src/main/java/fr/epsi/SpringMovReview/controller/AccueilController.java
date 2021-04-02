package fr.epsi.SpringMovReview.controller;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import fr.epsi.SpringMovReview.dto.FilmDTO;
import fr.epsi.SpringMovReview.entity.Film;
import fr.epsi.SpringMovReview.service.MovieService;

@Controller
public class AccueilController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping(value={"/api/home", "/"})
	public String accueillir(@ModelAttribute("movies") ArrayList<String[]> m, @ModelAttribute Film film, @ModelAttribute FilmDTO filmdto, Model model) {
		
			String webImgPath = "https://image.tmdb.org/t/p/w500";
		String webBckPath = "https://image.tmdb.org/t/p/original";
		String jsonServRemote;
		ArrayList<String[]> movieList = new ArrayList<String[]>();
		try {					
			jsonServRemote = movieService.loadJsonRemote();

			JSONObject obj = new JSONObject(jsonServRemote);
			JSONArray res = obj.getJSONArray("results");

			for (int i = 0; i < res.length() ; i++)
			{
				String s;
				film = movieService.findFilmByIdFilm(res.getJSONObject(i).getString("id"));
				if (film == null)
				{
					s = "0";
				} else {
					s = String.valueOf(film.getLikes());
				}
				
				String[] mov = {res.getJSONObject(i).getString("original_title"), 
						webImgPath+res.getJSONObject(i).getString("poster_path"),					
						res.getJSONObject(i).getString("overview"),
						res.getJSONObject(i).getString("id"),
						s,
						webBckPath+res.getJSONObject(i).getString("backdrop_path")};
				movieList.add(mov);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("movies", movieList);
		model.addAttribute("film", film);		
		model.addAttribute("filmdto", filmdto);
		
		return "accueil";
	}
	
	@GetMapping("/api/research/{id}")
	public String details(@ModelAttribute Film film, @ModelAttribute FilmDTO filmdto, @PathVariable(value="id") String id  ,Model model) throws IOException {
		
		film = movieService.findFilmByIdFilm(id);
		if (film == null)
		{
			String jsonServRemote;
			String webImgPath = "https://image.tmdb.org/t/p/w500";
			String webBckPath = "https://image.tmdb.org/t/p/original";
			String title = filmdto.getTitle();
					
			jsonServRemote = movieService.loadJsonResearch(title);
			JSONObject obj = new JSONObject(jsonServRemote);
			JSONArray res = obj.getJSONArray("results");
			
			ArrayList<String> movie = new ArrayList<String>();
			for (int i = 0; i < res.length() ; i++)
			{
				String s;
				film = movieService.findFilmByIdFilm(res.getJSONObject(i).getString("id"));
				if (film == null)
				{
					s = "0";
				} else {
					s = String.valueOf(film.getLikes());
				}
				movie.add(res.getJSONObject(0).getString("original_title")); 
				movie.add(webImgPath+res.getJSONObject(0).getString("poster_path"));			
				movie.add(res.getJSONObject(0).getString("overview"));
				movie.add(res.getJSONObject(0).getString("id"));
				movie.add(s);
				movie.add(webBckPath+res.getJSONObject(0).getString("backdrop_path"));
			}
		}
		model.addAttribute("movie", film);
		return "research";
	}
	
	@PostMapping("/api/research")
	public String research(@ModelAttribute Film film, @ModelAttribute FilmDTO filmdto, Model model) throws IOException {
		
		String jsonServRemote;
		String webImgPath = "https://image.tmdb.org/t/p/w500";
		String webBckPath = "https://image.tmdb.org/t/p/original";
		String title = filmdto.getTitle();
				
		jsonServRemote = movieService.loadJsonResearch(title);
		JSONObject obj = new JSONObject(jsonServRemote);
		JSONArray res = obj.getJSONArray("results");
		
		ArrayList<String> movie = new ArrayList<String>();
		for (int i = 0; i < res.length() ; i++)
		{
			String s;
			film = movieService.findFilmByIdFilm(res.getJSONObject(i).getString("id"));
			if (film == null)
			{
				s = "0";
			} else {
				s = String.valueOf(film.getLikes());
			}
			movie.add(res.getJSONObject(0).getString("original_title")); 
			movie.add(webImgPath+res.getJSONObject(0).getString("poster_path"));			
			movie.add(res.getJSONObject(0).getString("overview"));
			movie.add(res.getJSONObject(0).getString("id"));
			movie.add(s);
			movie.add(webBckPath+res.getJSONObject(0).getString("backdrop_path"));
		}
		
		model.addAttribute("movie", movie);
		
		return "research";
	}

	@PostMapping("/like")
	public RedirectView like(@ModelAttribute Film film, Model model) {
		model.addAttribute("film", film);
		movieService.like(film);
		return new RedirectView("/api/home");
	}

	@PostMapping("/dislike/{id}")
	public String dislike(@ModelAttribute Film film, Model model) {
		model.addAttribute("film", film);
		movieService.dislike(film);
		return "redirect:/api/home";
	}
}

