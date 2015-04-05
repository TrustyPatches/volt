package controllers;

import models.User;
import play.*;
import play.libs.Json;
import play.mvc.*;

import play.mvc.BodyParser;
import com.fasterxml.jackson.databind.JsonNode;

public class LoginController extends Controller {

  @BodyParser.Of(BodyParser.Json.class)
  public static Result login() {
    JsonNode body = request().body().asJson();
    String email = body.get("email").asText();
    String password = body.get("password").asText();

    User u = User.find.where().eq("email", email).findUnique();
    System.out.println(u.password);
    if (u != null && u.password.equals(password)) {
      session("username", email);
      return ok(Json.toJson(true));
    } else {
      return ok(Json.toJson(false));
    }
  }

  public static Result logout() {
    session().clear();
    return ok();
  }
}
