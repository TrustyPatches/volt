package controllers;

import core.authentication.VoltRole;
import play.Play;
import play.mvc.*;
import core.authentication.RestrictToRegisteredAction.*;
import core.authentication.RestrictToRoleAction.*;

import views.html.*;

import java.io.File;

/**
 * Handles GET requests for all views as well as error pages.
 */
// TODO Switch views from being full pages to being partials
public class Application extends Controller {

  public static final String COMPONENT_VIEWS = "public/app/components/";
  public static final String SHARED_VIEWS = "public/app/shared/";
  public static final String ERROR_VIEWS = "public/app/error/";

  public static Result index() {
    return ok(index.render("Your new application is ready."));
  }

  public static Result signup() {
    return fetch("authentication/signup/signup.html");
  }

  public static Result login() {
    return fetch("authentication/login/login.html");
  }

  @RestrictToRegistered
  public static Result profile() {
    return fetch("profile/profile.html");
  }

  @RestrictToRole({VoltRole.ADMINISTRATOR})
  public static Result users() {
    return fetch("admin/users.html");
  }

  private static Result fetch(String filePath) {
    return fetch(filePath, true);
  }

  private static Result fetch(String filePath, boolean component) {
    try {
      filePath = (component) ? COMPONENT_VIEWS + filePath : SHARED_VIEWS + filePath;
      return ok(Play.application().getFile(filePath), true);
    } catch (Exception e) {
      return errorPage(404, e.getMessage());
    }
  }

  public static Result errorPage(int errorCode, String errorMessage) {
    switch (errorCode) {
      case (404):
        return notFound(resolveErrorPage("notfound.html"), true);
      case (403):
        return unauthorized(resolveErrorPage("unauthorized.html"), true);
      default:
        return internalServerError();
    }
  }

  private static File resolveErrorPage(String filePath) {
    return Play.application().getFile(ERROR_VIEWS + filePath);
  }
}
