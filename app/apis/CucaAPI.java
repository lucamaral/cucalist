package apis;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Cuca;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CucaAPI extends Controller {

    private final static List<Cuca> cucas = new ArrayList<>();

    public static Result list() {
        return ok(Json.toJson(cucas));
    }

    public static Result save() throws Exception {
        final Cuca cuca = new ObjectMapper().readValue(request().body().asJson().traverse(), Cuca.class);
        cucas.add(cuca);
        return ok(Json.toJson(true));
    }

}
