package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result eventos() {
        return ok(views.html.eventos.render());
    }

    public static Result cucas() {
        return ok(views.html.cucas.render());
    }

    public static Result evento(final long id) {
        return ok(views.html.evento.render(id));
    }

}
