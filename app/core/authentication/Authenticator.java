package core.authentication;

import controllers.Application;
import controllers.Assets;
import models.VoltUser;
import play.Play;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Controller.session;

public class Authenticator extends Results {

  public static Result onUnauthorized(Http.Context ctx) {
    return Application.errorPage(403, "You are not authorized to access this page");
  }

  public static boolean isLoggedIn() {
    return session("username") != null;
  }

  public static boolean isRegistered() {
    return (VoltUser.find.where().eq("username", session("username")).findUnique() != null);
  }
}