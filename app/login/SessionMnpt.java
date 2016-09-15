package login;

import models.Pessoa;
import play.mvc.Controller;

public class SessionMnpt extends Controller {

    private static final String SESSION_USER_EMAIL = "emailUser";
    private static final String SESSION_USER_NAME = "nameUser";
    private static final String SESSION_USER_PASSWORD = "passwordUser";

    public void putInSession(final Pessoa pessoa) {
        session().clear();
        session(SESSION_USER_EMAIL, pessoa.getEmail());
        session(SESSION_USER_NAME, pessoa.getNome());
        session(SESSION_USER_PASSWORD, pessoa.getSenha());
    }

    public Pessoa getFromSession() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setNome(session().get(SESSION_USER_NAME));
        pessoa.setEmail(session().get(SESSION_USER_EMAIL));
        pessoa.setSenha(session().get(SESSION_USER_PASSWORD));
        return pessoa;
    }

    public boolean someoneIsLogged() {
        return session().get(SESSION_USER_NAME) != null;
    }

}
