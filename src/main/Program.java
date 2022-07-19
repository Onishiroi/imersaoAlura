package main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import parser.JsonParser;

public class Program {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=15709c5a1a97b71031bc241047d17e5c";
		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		//System.out.println(body);

		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
	       for (Map<String,String> filme : listaDeFilmes) {
	            System.out.println("Título: " + filme.get("title"));
	            System.out.println("Sinopse: " + filme.get("overview"));
	            
	            System.out.println("URL do poster: https://image.tmdb.org/t/p/w500" + filme.get("backdrop_path"));
	            Double rating = Double.parseDouble(filme.get("vote_average"));
	            //System.out.println(filme.get("vote_average"));
	            System.out.printf("Rating: %.2f%n%n", rating);
	

	        }
	
	}

}
