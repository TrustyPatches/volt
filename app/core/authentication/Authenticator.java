package core.authentication;

import controllers.Application;
import controllers.Assets;
import models.VoltUser;
import play.Play;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Controller.session;

// TODO Class name should probably be changed as the Authenticator doesn't authenticate...
/**
 * A class mainly composed of static methods which is used to handle various
 * queries regarding authentication. Helper methods are stored here which
 * answer questions which must have a definitive implementation and are needed
 * by various other classes. Note that no routes map to the Authenticator,
 * it only defines the response used when access to resources is denied and
 * actual login requests are handled by the LoginController.
 */
public class Authenticator extends Results {

  /**
   * Defines what to do when a request to access a certain resource is denied.
   *
   * @param ctx the current context of the Http request
   * @return    A 403 result containing an error page.
   */
  public static Result onUnauthorized(Http.Context ctx) {
    return Application.errorPage(403, "You are not authorized to access this page");
  }

  /**
   * Checks if the user is logged in.
   *
   * @return    true if the user has a session cooking containing a 'session' parameter
   */
  public static boolean isLoggedIn() {
    return session("username") != null;
  }

  /**
   * Checks if the user is logged in and registered in the system.
   *
   * @return    true if the user's username is valid
   */
  public static boolean isRegistered() {
    return isLoggedIn() && VoltUser.find.where().eq("username", session("username")).findUnique() != null;
  }
}