package controllers;

import core.authentication.Authenticator;
import models.VoltUser;
import play.libs.Json;
import play.mvc.*;

import play.mvc.BodyParser;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A controller to handle login requests sent from a client.
 */
// TODO BCrypt password hashing!
public class LoginController extends Controller {

  /**
   * Receives a login request and checks whether the supplied
   * username is present in the database (identification) and whether
   * the supplied password matches the hash stored against that
   * identity (authentication).
   *
   * @return    200 OK response containing the outcome of the
   * attempted login.
   */
  @BodyParser.Of(BodyParser.Json.class)
  public static Result login() {
    // TODO *Must* perform server side validation of supplied creds
    JsonNode body = request().body().asJson();
    String username = body.get("username").asText();
    String password = body.get("password").asText();

    VoltUser u = VoltUser.find.where().eq("username", username).findUnique();
    System.out.println(u.password);
    if (u != null && u.password.equals(password)) {
      session("username", username);
      return ok(Json.toJson(true));
    } else {
      return ok(Json.toJson(false));
    }
  }

  /**
   * Handles a request checking if the user is logged in or not.
   *
   * @return    true if the user has a session cookie containing the
   * username parameter, false otherwise.
   */
  public static Result isLoggedIn() {
    return ok(Json.toJson(Authenticator.isLoggedIn()));
  }

  /**
   * Handles a request for the value held in the session cookie
   * for the 'username' parameter.
   *
   t @return    200 OK response containing the username of a logged in
   * user, an empty string if that parameter does not exist.
   */
  public static Result getUsername() {
    return ok(Json.toJson((session("username") != null) ? session("username") : ""));
  }

  /**
   * Handles logout requests. Simply clears the current user's session.
   *
   * @return  200 OK response
   */
  public static Result logout() {
    session().clear();
    return ok();
  }
}
