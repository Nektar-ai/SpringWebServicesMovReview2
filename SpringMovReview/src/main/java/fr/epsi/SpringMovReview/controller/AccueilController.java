package fr.epsi.SpringMovReview.controller;

import java.io.IOException;
import java.util.ArrayList;

import fr.epsi.SpringMovReview.entity.Film;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epsi.SpringMovReview.service.MovieService;

@Controller
public class AccueilController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping(path="/")
	public String accueillir(@ModelAttribute("movies") ArrayList<String[]> m, Model model) {
		
		String webImgPath = "https://image.tmdb.org/t/p/w500";
		String jsonServ;
		String jsonServRemote;
		ArrayList<String[]> movieList = new ArrayList<String[]>();
		try {		
			jsonServ = movieService.loadJson();
			jsonServRemote = movieService.loadJsonRemote();
			
			System.out.println("JSON REMOTE FROM CONTROLLER MATE : "+jsonServRemote);
			
			ObjectMapper mapper = new ObjectMapper();

			JSONObject obj = new JSONObject(jsonServRemote);
			JSONArray res = obj.getJSONArray("results");

			for (int i = 0; i < res.length() ; i++)
			{
				String[] mov = {res.getJSONObject(i).getString("original_title"), 
						webImgPath+res.getJSONObject(i).getString("poster_path"),					
						res.getJSONObject(i).getString("overview"),
						res.getJSONObject(i).getString("id")};
				movieList.add(mov);
			}
			
			System.out.println("Taille du fichier : "+jsonServRemote.length());
			System.out.println("Taille de l'array list : "+movieList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("movies", movieList);
		model.addAttribute("film", new Film());


		return "accueil";
	}
	
	@PostMapping(path="/like")
	public String like(@ModelAttribute Film film, Model model) {
		model.addAttribute("film", film);

		System.out.println("Film liked" + film.toString());
		return "redirect:/";
	}
	
	@PostMapping(path="/dislike/{id}")
	public String dislike() {
		return "redirect:/";
	}
}

