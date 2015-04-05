package controllers;

import play.*;
import play.mvc.*;

import play.mvc.BodyParser;
import com.fasterxml.jackson.databind.JsonNode;

public class SignUpController extends Controller {

  @BodyParser.Of(BodyParser.Json.class)
  public static Result signUp() {

    JsonNode body = request().body().asJson(); // Grab the body of the request as Json.
    String email = body.get("email").asText();
    String password = body.get("password").asText();

    return ok("Looks good to me!");
  }
}