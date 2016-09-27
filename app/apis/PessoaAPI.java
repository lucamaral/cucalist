package apis;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import authentication.Autenticado;
import bd.ConexaoDB;
import bd.PessoaDAO;
import models.Pessoa;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class PessoaAPI extends Controller {

    private static Connection con = ConexaoDB.getConexaoMySQL();
    private static PessoaDAO pessoaDAO = new PessoaDAO();

    @Autenticado
    public static Result listPessoas() throws Exception {
        List<Pessoa> allPessoas = new ArrayList<>();
        allPessoas = pessoaDAO.getAllPessoas(con);
        return ok(Json.toJson(allPessoas));
    }

    @Autenticado
    public static Result listParticipantes(final long id) throws Exception {
        List<Pessoa> participantes = new ArrayList<>();
        participantes = pessoaDAO.getEventoParticipantes(con, id);
        return ok(Json.toJson(participantes));
    }

}
