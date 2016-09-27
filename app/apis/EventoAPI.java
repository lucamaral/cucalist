package apis;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.Autenticado;
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

    @Autenticado
    public static Result list(final String searchTerm) throws Exception {
        if (searchTerm != null) {
            List<Evento> eventosFiltrados = new ArrayList<>();
            eventosFiltrados = eventoDAO.getEventoSearchTerm(con, searchTerm);
            return ok(Json.toJson(eventosFiltrados));
        } else {
            List<Evento> allEventos = new ArrayList<>();
            allEventos = eventoDAO.getAllEventos(con);
            return ok(Json.toJson(allEventos));
        }
    }

    @Autenticado
    public static Result getEvento(final long id) throws Exception {
        final Evento evento = eventoDAO.getEventoID(con, (int) id);
        return ok(Json.toJson(evento));
    }

    @Autenticado
    public static Result remove(final long id) throws Exception {
        final Evento eventoARemover = eventoDAO.getEventoID(con, (int) id);
        eventoDAO.deleteEvento(con, eventoARemover);
        return ok(Json.toJson(true));
    }

    @Autenticado
    public static Result save() throws Exception {
        final Evento evento = new ObjectMapper().readValue(request().body().asJson().traverse(), Evento.class);
        try {
            eventoDAO.addEvento(con, evento);
            return ok(Json.toJson(true));
        } catch (final CucaException e) {
            return internalServerError(Json.toJson(e));
        }
    }

    @Autenticado
    public static Result update() throws Exception {
        final Evento evento = new ObjectMapper().readValue(request().body().asJson().traverse(), Evento.class);
        try {
            eventoDAO.updateEvento(con, evento);
            return ok(Json.toJson(true));
        } catch (final CucaException e) {
            return internalServerError(Json.toJson(e));
        }
    }

}
