package controllers;

import core.authentication.VoltRole;
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

  @RestrictToRegistered
  public static Result profile() { return ok(profile.render()); }

  @RestrictToRole({ VoltRole.ADMINISTRATOR })
  public static Result users() { return ok(users.render()); }
}
