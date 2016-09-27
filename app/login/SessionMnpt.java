package login;

import java.sql.SQLException;

import bd.ConexaoDB;
import bd.PessoaDAO;
import models.Pessoa;
import play.mvc.Controller;

public class SessionMnpt extends Controller {

    private static PessoaDAO pessoaDAO = new PessoaDAO();

    private static final String SESSION_USER_EMAIL = "emailUser";
    private static final String SESSION_USER_PASSWORD = "passwordUser";

    public void putInSession(final Pessoa pessoa) {
        session().clear();
        session(SESSION_USER_EMAIL, pessoa.getEmail());
        session(SESSION_USER_PASSWORD, pessoa.getSenha());
    }

    public Pessoa getFromSession() throws SQLException {
        return pessoaDAO.getPessoaEmail(ConexaoDB.getConexaoMySQL(), session().get(SESSION_USER_EMAIL));

    }

    public boolean someoneIsLogged() {
        return session().get(SessionMnpt.SESSION_USER_EMAIL) != null;
    }

}
