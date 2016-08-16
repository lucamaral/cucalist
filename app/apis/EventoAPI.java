package apis;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bd.ConexaoDB;
import bd.EventoDAO;
import exceptions.CucaException;
import models.Evento;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class EventoAPI extends Controller {

    private static Connection con = ConexaoDB.getConexaoMySQL();
    private static EventoDAO eventoDAO = new EventoDAO();

    public static Result list(final String searchTerm) throws Exception {
        System.out.println("cheguei no list da api");
        if (searchTerm != null) {
            List<Evento> eventosFiltrados = new ArrayList<>();
            eventosFiltrados = eventoDAO.consultaEventoSearchTerm(con, searchTerm);
            return ok(Json.toJson(eventosFiltrados));
        } else {
            List<Evento> allEventos = new ArrayList<>();
            allEventos = eventoDAO.getAllEventos(con);
            return ok(Json.toJson(allEventos));
        }
    }

    public static Result getEvento(final long id) throws Exception {
        final Evento evento = eventoDAO.consultaEventoID(con, (int) id);
        return ok(Json.toJson(evento));
    }

    public static Result remove(final long id) throws Exception {
        final Evento eventoARemover = eventoDAO.consultaEventoID(con, (int) id);
        eventoDAO.removerEvento(con, eventoARemover);
        return ok(Json.toJson(true));
    }

    public static Result save() throws Exception {
        final Evento evento = new ObjectMapper().readValue(request().body().asJson().traverse(), Evento.class);
        try {
            eventoDAO.inserirEvento(con, evento);
            return ok(Json.toJson(true));
        } catch (final CucaException e) {
            return internalServerError(Json.toJson(e));
        }
    }

}
