package controllers;

import play.*;
import play.mvc.*;
import core.authentication.RestrictToRegisteredAction.*;
import core.authentication.RestrictToRoleAction.*;

import views.html.*;

public class Application extends Controller {

  public static Result index() {
    return ok(index.render("Your new application is ready."));
  }

  public static Result signup() {
    return ok(signup.render());
  }

  public static Result login() {
    return ok(login.render());
  }

  @RestrictToRole(roles = "GROUP_LEADER") // For test purposes only, should be restrict to registered
  public static Result profile() { return ok(profile.render()); }

}
