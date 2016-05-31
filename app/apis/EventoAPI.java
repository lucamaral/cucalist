package apis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import bd.ConexaoDB;
import bd.EventoDAO;
import models.Evento;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class EventoAPI extends Controller {

	private static Connection con = ConexaoDB.getConexaoMySQL();
	private static EventoDAO eventoDAO = new EventoDAO();
	
	public static Result list(String searchTerm) throws Exception{
		System.out.println("cheguei no list da api");
		if(searchTerm != null){
			List<Evento> eventosFiltrados = new ArrayList<>();
			eventosFiltrados = eventoDAO.consultaEventoSearchTerm(con, searchTerm);
			return ok(Json.toJson(eventosFiltrados)); 
		}else{
			List<Evento> allEventos = new ArrayList<>();
			allEventos = eventoDAO.getAllEventos(con);
			return ok(Json.toJson(allEventos));
		}
	}
	
	public static Result getEvento(long id) throws Exception {
		Evento evento = eventoDAO.consultaEventoID(con, (int)id);
		return ok(Json.toJson(evento));
	}
	
	public static Result remove(long id) throws Exception{
		Evento eventoARemover = eventoDAO.consultaEventoID(con, (int)id);
		eventoDAO.removerEvento(con, eventoARemover);
		return ok(Json.toJson(true));
	}
	
	
	
}
