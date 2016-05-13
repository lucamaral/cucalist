package apis;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Cuca;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CucaAPI extends Controller {

	private final static List<Cuca> cucas = new ArrayList<>();

	public static Result list(String searchTerm) {
		System.out.println(searchTerm);
		if (searchTerm != null) {
			List<Cuca> cucasFiltradas = new ArrayList<>();
			for (Cuca cuca : cucas) {
				if (cuca.getOrigem().contains(searchTerm) || cuca.getTipo().contains(searchTerm)) {
					cucasFiltradas.add(cuca);	
				}
			}
			return ok(Json.toJson(cucasFiltradas));
		}
		return ok(Json.toJson(cucas));
	}
	
	
	
	public static Result save() throws Exception {
		final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
		cucas.add(cuca);
		cuca.setId(cuca.getClasseID());
		return ok(Json.toJson(true));
	}
	
	public static Result remover(Long id){
		Cuca cucaARemover = new Cuca();
		for(Cuca cuca: cucas){
			if(cuca.getId() == id){
				cucaARemover = cuca;
			}
		}
		cucas.remove(cucaARemover);
		return ok(Json.toJson(true));
	}

}
