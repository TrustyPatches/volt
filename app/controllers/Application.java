package controllers;

import play.Logger;
import play.libs.F;
import play.*;
import play.mvc.*;
import securesocial.core.BasicProfile;
import securesocial.core.RuntimeEnvironment;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SecuredAction;
import securesocial.core.java.UserAwareAction;
import service.DemoUser;
import views.html.index;
import views.html.linkResult;


public class Application extends Controller {

  public static Logger.ALogger logger = Logger.of("application.controllers.Application");
  private RuntimeEnvironment<DemoUser> env;

  public Application(RuntimeEnvironment<DemoUser> env) {
    this.env = env;
  }

  @SecuredAction
  public Result index() {
    if (logger.isDebugEnabled()) {
      logger.debug("access granted to index");
    }
    DemoUser user = (DemoUser) ctx().args.get(SecureSocial.USER_KEY);
    return ok(index.render(user, SecureSocial.env()));
  }

  @UserAwareAction
  public Result userAware() {
    DemoUser demoUser = (DemoUser) ctx().args.get(SecureSocial.USER_KEY);
    String userName;
    if (demoUser != null) {
      BasicProfile user = demoUser.main;
      if (user.firstName().isDefined()) {
        userName = user.firstName().get();
      } else if (user.fullName().isDefined()) {
        userName = user.fullName().get();
      } else {
        userName = "authenticated user";
      }
    } else {
      userName = "guest";
    } 
    return ok("Hello " + userName + ", you are seeing a public page");
  }

  @SecuredAction
  public Result linkResult() {
    DemoUser current = (DemoUser)ctx().args.get(SecureSocial.USER_KEY);
    return ok(linkResult.render(current, current.identities));
  }

  public F.Promise<Result> currentUser() {
    return SecureSocial.currentUser(env).map(new F.Function<Object, Result>() {
      @Override
       public Result apply(Object maybeUser) throws Throwable {
        String id;

        if (maybeUser != null) {
          DemoUser user = (DemoUser) maybeUser;
          id = user.main.userId();
        } else {
          id = "not available. Please log in.";
        }
        return ok("your id is " + id);
      }
    });
  }
}
