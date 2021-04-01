package fr.epsi.SpringMovReview.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import org.springframework.web.bind.ServletRequestUtils;

import fr.epsi.SpringMovReview.dto.FilmDTO;
import fr.epsi.SpringMovReview.entity.Film;
import fr.epsi.SpringMovReview.service.MovieService;

@Controller
public class AccueilController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/")
	public String accueillir(@ModelAttribute("movies") ArrayList<String[]> m, @ModelAttribute Film film, @ModelAttribute FilmDTO filmdto, Model model) {
		
		String webImgPath = "https://image.tmdb.org/t/p/w500";
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
						s};				
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
	
	@PostMapping("/research")
	public String research(@ModelAttribute Film film, @ModelAttribute FilmDTO filmdto, Model model) throws IOException {
		
		String jsonServRemote;
		String webImgPath = "https://image.tmdb.org/t/p/w500";
		String title = filmdto.getTitle();
				
		jsonServRemote = movieService.loadJsonResearch(title);
		JSONObject obj = new JSONObject(jsonServRemote);
		JSONArray res = obj.getJSONArray("results");
		
		ArrayList<String> movie = new ArrayList<String>();
		for (int i = 0; i < res.length() ; i++)
		{
			movie.add(res.getJSONObject(0).getString("original_title")); 
			movie.add(webImgPath+res.getJSONObject(0).getString("poster_path"));			
			movie.add(res.getJSONObject(0).getString("overview"));
			movie.add(res.getJSONObject(0).getString("id"));
		}
		
		model.addAttribute("movie", movie);
		
		return "research";
	}

	@PostMapping("/like")
	public String like(@ModelAttribute Film film, Model model) {
		model.addAttribute("film", film);
		movieService.like(film);
		System.out.println("Film liked APRES REPO : " + film.toString());
		return "redirect:/";
	}

	@PostMapping("/dislike/{id}")
	public String dislike(@ModelAttribute Film film, Model model) {
		model.addAttribute("film", film);
		movieService.dislike(film);
		return "redirect:/";
	}
}

 
