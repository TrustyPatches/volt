package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;

import play.mvc.BodyParser;
import com.fasterxml.jackson.databind.JsonNode;

public class LoginController extends Controller {

  @BodyParser.Of(BodyParser.Json.class)
  public static Result login() {

    JsonNode body = request().body().asJson(); // Grab the body of the request as Json.
    String email = body.get("email").asText();
    String password = body.get("password").asText();

    return ok(Json.toJson("Logged in!"));
  }

  public static Result logout() {
    return ok(Json.toJson("Logged out succesfully"));
  }
}