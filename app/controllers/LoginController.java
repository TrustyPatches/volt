package controllers;

import models.VoltUser;
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

    VoltUser u = VoltUser.find.where().eq("username", email).findUnique();
    System.out.println(u.password);
    if (u != null && u.password.equals(password)) {
      session("username", email);
      return ok(Json.toJson(true));
    } else {
      return ok(Json.toJson(false));
    }
  }

  public static Result isLoggedIn() {
    return ok(Json.toJson(session("username") != null));
  }

  public static Result getUsername() {
    return ok(Json.toJson((session("username") != null) ? session("username") : ""));
  }

  public static Result logout() {
    session().clear();
    return ok();
  }
}
