package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;

import play.mvc.BodyParser;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A controller to handle registration requests.
 */
public class SignUpController extends Controller {

  /**
   * Handles requests to sign up for the application. Requests should
   * be in Json with all appropriate fields filled in:
   *
   * Mandatory fields: username, password
   *
   * Optional fields:
   *
   * @return 200 OK result containing the outcome of the registration.
   */
  @BodyParser.Of(BodyParser.Json.class)
  public static Result signUp() {

    JsonNode body = request().body().asJson(); // Grab the body of the request as Json.
    String username = body.get("username").asText();
    String password = body.get("password").asText();

    // TODO: Should check for existing user and do serverside validation

    // TODO: Keep signup process up to date with User construction
    return ok(Json.toJson("Looks good to me!"));
  }
}