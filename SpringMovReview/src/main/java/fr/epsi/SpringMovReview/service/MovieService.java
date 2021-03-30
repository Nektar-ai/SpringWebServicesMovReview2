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

//import fr.epsi.moviereview2.repository.MovieRepository;

@Service
public class MovieService {

//	@Autowired
//	private MovieRepository movieRepository;
	
	public String getData() 
	{
		String data = "Je t'envoie des bayes depuis la couche service mate !";
		return data;
	}
	
	public String loadJson () throws IOException
	{
		/* TMDB API Key : 719886a0b8020a1ae30c9c5d174c01d3
		 * Exemple API request : https://api.themoviedb.org/3/movie/550?api_key=719886a0b8020a1ae30c9c5d174c01d3
		 * API Read Access Token (v4 auth) : eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MTk4ODZhMGI4MDIwYTFhZTMwYzljNWQxNzRjMDFkMyIsInN1YiI6IjYwMzhjMTlhMzIyYjJiMDA1NTI1NzkzOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jYvJTMpwaUNXjTBCXcBEsze0sLtJjUMaKlAyYgpM2fQ
		 */

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
}

//Path fileName = Path.of("static/moviejson.txt");    