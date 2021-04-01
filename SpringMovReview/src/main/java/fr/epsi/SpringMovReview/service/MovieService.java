package fr.epsi.SpringMovReview.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.epsi.SpringMovReview.apifetch.MovieRestTemplate;
import fr.epsi.SpringMovReview.entity.Film;
import fr.epsi.SpringMovReview.repository.FilmRepository;

@Service
public class MovieService {

	@Autowired
	private FilmRepository movieRepository;
	
	public String loadJson () throws IOException
	{
	    Resource resource = new ClassPathResource("static/moviejson.txt");
        String stringSon = Files.readString(Path.of(resource.getURI()));
        return stringSon;
	}
	
	public String loadJsonRemote () throws IOException
	{
		/* TMDB API Key : 719886a0b8020a1ae30c9c5d174c01d3
		 * Exemple API request : https://api.themoviedb.org/3/movie/550?api_key=719886a0b8020a1ae30c9c5d174c01d3
		 * API Read Access Token (v4 auth) : eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MTk4ODZhMGI4MDIwYTFhZTMwYzljNWQxNzRjMDFkMyIsInN1YiI6IjYwMzhjMTlhMzIyYjJiMDA1NTI1NzkzOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jYvJTMpwaUNXjTBCXcBEsze0sLtJjUMaKlAyYgpM2fQ
		 */
		
		RestTemplate restTemplate = new RestTemplate();
		MovieRestTemplate mrt = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=719886a0b8020a1ae30c9c5d174c01d3&language=fr-FR", MovieRestTemplate.class);
        return mrt.toString();
	}
	
	public void like (Film f)
	{
		Film f2 = movieRepository.findByIdFilm(f.idFilm);
		if (f2 == null)
		{
			movieRepository.save(f);
		} else {
			f2.setLikes(f2.getLikes()+1);
			movieRepository.save(f2);
		}
	}
	
	public void dislike (Film f)
	{
		Film f2 = movieRepository.findByIdFilm(f.idFilm);
		if (f2 == null)
		{
			movieRepository.save(f);
		} else {
			f2.setLikes(f2.getLikes()-1);
			movieRepository.save(f2);
		}
	}
	
	public Film findFilmByIdFilm(String id) {
		Film f = movieRepository.findByIdFilm(id);
		return f;
	}
	
}

//Path fileName = Path.of("static/moviejson.txt");    