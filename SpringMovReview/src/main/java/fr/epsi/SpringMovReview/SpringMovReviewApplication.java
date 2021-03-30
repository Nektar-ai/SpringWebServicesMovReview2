package fr.epsi.SpringMovReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import fr.epsi.SpringMovReview.apifetch.MovieRestTemplate;

@SpringBootApplication
public class SpringMovReviewApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringMovReviewApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMovReviewApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			MovieRestTemplate mvrt = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=719886a0b8020a1ae30c9c5d174c01d3&language=fr-FR", MovieRestTemplate.class);
			log.info(mvrt.toString());
		};
	}
}
