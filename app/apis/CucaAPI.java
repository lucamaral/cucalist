package apis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bd.ConexaoDB;
import bd.CucaDAO;
import models.Cuca;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CucaAPI extends Controller {
	
	private static Connection con = ConexaoDB.getConexaoMySQL();
	private static CucaDAO cucaDAO = new CucaDAO();

	public static Result list(String searchTerm) throws SQLException {
		if (searchTerm != null) {
			List<Cuca> cucasFiltradas = new ArrayList<>();
			cucasFiltradas = cucaDAO.consultaCucaSearchTerm(con, searchTerm);
			return ok(Json.toJson(cucasFiltradas));
		}else{
			List<Cuca> allCucas = new ArrayList<>();
			allCucas = cucaDAO.consultaAllCucas(con);
			return ok(Json.toJson(allCucas));
		}
	}
	
	public static Result atualizar() throws Exception{
		final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
		cucaDAO.atualizarCuca(con, cuca);
		return ok(Json.toJson(cuca));
	}
	
	public static Result save() throws Exception {
		final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
		cucaDAO.inserirCuca(con, cuca);
		return ok(Json.toJson(true));
	}
	
	public static Result remover(long id) throws SQLException{
		Cuca cucaARemover = new Cuca();
		cucaARemover = cucaDAO.consultaCucaID(con, id);
		cucaDAO.removerCuca(con, cucaARemover);
		return ok(Json.toJson(true));
	}

}
