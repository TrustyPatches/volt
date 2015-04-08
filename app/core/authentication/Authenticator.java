package core.authentication;

import controllers.Assets;
import models.VoltUser;
import play.Play;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Controller.session;

public class Authenticator extends Results {

  public static final String UNAUTHORIZED_PAGE = "public/app/components/authentication/unauthorized.html";

  public static Result onUnauthorized(Http.Context ctx) {
    return unauthorized(Play.application().getFile(UNAUTHORIZED_PAGE), true);
  }

  public static boolean isLoggedIn() {
    return session("username") != null;
  }

  public static boolean isRegistered() {
    return (VoltUser.find.where().eq("username", session("username")).findUnique() != null);
  }
}