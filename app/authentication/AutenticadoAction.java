package authentication;

import login.SessionMnpt;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;

public class AutenticadoAction extends Action<Autenticado> {

    private static SessionMnpt sessionMnpt = new SessionMnpt();

    @Override
    public Promise<SimpleResult> call(final Context ctx) throws Throwable {
        if (sessionMnpt.someoneIsLogged()) {
            return this.delegate.call(ctx);
        }
        ctx.flash().put("erro", "Você não está logado!");
        return F.Promise.pure(redirect("/login"));
    }

}
