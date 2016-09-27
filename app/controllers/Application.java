package controllers;

import authentication.Autenticado;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result login() {
        return ok(views.html.login.render());
    }

    public static Result logout() {
        session().clear();
        return ok(views.html.login.render());
    }

    @Autenticado
    public static Result index() {
        return ok(views.html.index.render());
    }

    @Autenticado
    public static Result eventos() {
        return ok(views.html.eventos.render());
    }

    @Autenticado
    public static Result cucas() {
        return ok(views.html.cucas.render());
    }

}
