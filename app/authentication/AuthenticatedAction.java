package authentication;

import org.springframework.beans.factory.annotation.Autowired;

import login.SessionMnpt;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;

public class AuthenticatedAction extends Action<Authenticate> {

    @Autowired
    private SessionMnpt SessionMnpt;

    @Override
    public Promise<SimpleResult> call(final Context ctx) throws Throwable {
        if (SessionMnpt.someoneIsLogged()) {
            return this.delegate.call(ctx);
        }
        ctx.flash().put("erro", "Você não está logado!");
        return F.Promise.pure(redirect("/login"));
    }

}
