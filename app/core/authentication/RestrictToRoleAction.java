package core.authentication;

import models.VoltUser;
import play.api.Logger;
import play.mvc.Action;
import play.mvc.Result;
import play.libs.F;
import play.mvc.Http;
import play.mvc.With;

import core.authentication.RestrictToRegisteredAction.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RestrictToRoleAction extends Action<RestrictToRoleAction.RestrictToRole> {

  @RestrictToRegistered
  public F.Promise<Result> call(Http.Context ctx) throws Throwable {
    // This should be taken care of by @RestrictToRegistered annotation
    // but at the moment this isn't getting called correctly
    if (Authenticator.isLoggedIn() && Authenticator.isRegistered()) {
      ;
    } else {
      Result unauthorized = Authenticator.onUnauthorized(ctx);
      return F.Promise.pure(unauthorized);
    }
    // <- End of registration check ->

    VoltUser u = VoltUser.find.where().eq("username", ctx.session().get("username")).findUnique();
    for (VoltRole s : configuration.value()) {
      if (s == u.role) {
        return delegate.call(ctx);
      }
    }
    Result unauthorized = Authenticator.onUnauthorized(ctx);
    return F.Promise.pure(unauthorized);
  }

  @With(RestrictToRoleAction.class)
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RestrictToRole {
    VoltRole[] value();
  }
}