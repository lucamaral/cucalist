package apis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bd.CucaDAO;
import models.Cuca;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CucaAPI extends Controller {
	
	private static Connection con = DB.getConnection();
	private static CucaDAO cucaDAO = new CucaDAO();
	
	private final static List<Cuca> cucas = new ArrayList<>();

	public static Result list(String searchTerm) throws SQLException {
		System.out.println(searchTerm);
		if (searchTerm != null) {
			List<Cuca> cucasFiltradas = cucaDAO.consultaCucaSearchTerm(con, searchTerm);
			return ok(Json.toJson(cucasFiltradas));
		}
		return ok(Json.toJson(cucas));
	}
	
	
	
	public static Result save() throws Exception {
		final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
		cucas.add(cuca);
		cucaDAO.inserirCuca(con, cuca);
		return ok(Json.toJson(true));
	}
	
	public static Result remover(Long id) throws SQLException{
		Cuca cucaARemover = new Cuca();
		for(Cuca cuca: cucas){
			if(cuca.getID() == id){
				cucaARemover = cuca;
			}
		}
		cucas.remove(cucaARemover);
		cucaDAO.removerCuca(con, cucaARemover);
		return ok(Json.toJson(true));
	}

}
