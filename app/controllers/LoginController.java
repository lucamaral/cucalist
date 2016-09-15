package controllers;

import login.SessionMnpt;
import models.Pessoa;
import play.mvc.Controller;

public class LoginController extends Controller {

    private SessionMnpt sessionMNPT;

    public void logIn(final Pessoa pessoa) {
        sessionMNPT.putInSession(pessoa);
    }

    public void logOut() {
        session().clear();
    }

}
