package authentication;

import play.mvc.With;

@With(AuthenticatedAction.class)

public @interface Authenticate {

}
