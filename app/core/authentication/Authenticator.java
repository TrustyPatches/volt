package core.authentication;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Controller.session;

public class Authenticator extends Results {

  public static Result onUnauthorized(Http.Context ctx) {
    return unauthorized(views.html.unauthorized.render());
  }

  public static boolean isLoggedIn() {
    return session("username") != null;
  }
}