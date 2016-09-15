package apis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.Authenticate;
import bd.ConexaoDB;
import bd.CucaDAO;
import exceptions.CucaException;
import models.Cuca;
import models.Rating;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CucaAPI extends Controller {

    private static Connection con = ConexaoDB.getConexaoMySQL();
    private static CucaDAO cucaDAO = new CucaDAO();

    @Authenticate
    public static Result list(final String searchTerm) throws SQLException {
        if (searchTerm != null) {
            List<Cuca> cucasFiltradas = new ArrayList<>();
            cucasFiltradas = cucaDAO.consultaCucaSearchTerm(con, searchTerm);
            return ok(Json.toJson(cucasFiltradas));
        } else {
            List<Cuca> allCucas = new ArrayList<>();
            allCucas = cucaDAO.consultaAllCucas(con);
            return ok(Json.toJson(allCucas));
        }
    }

    @Authenticate
    public static Result atualizar() throws Exception {
        final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
        try {
            cucaDAO.atualizarCuca(con, cuca);
            return ok(Json.toJson(cuca));
        } catch (final CucaException e) {
            return internalServerError(Json.toJson(e));
        }
    }

    @Authenticate
    public static Result salvarRating() throws Exception {
        final Rating rating = new ObjectMapper().readValue(request().body().asJson().traverse(), Rating.class);
        final boolean update = cucaDAO.darNota(con, rating);
        return ok(Json.toJson(update));
    }

    @Authenticate
    public static Result getOpcoes(final long id) throws Exception {
        List<Cuca> opcoes = new ArrayList<>();
        opcoes = cucaDAO.getCucaEvento(con, id);
        return ok(Json.toJson(opcoes));
    }

    @Authenticate
    public static Result save() throws Exception {
        final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
        try {
            cucaDAO.inserirCuca(con, cuca);
            return ok(Json.toJson(true));
        } catch (final CucaException e) {
            return internalServerError(Json.toJson(e));
        }
    }

    @Authenticate
    public static Result remover(final long id) throws SQLException {
        cucaDAO.removerCuca(con, id);
        return ok(Json.toJson(true));
    }

}
