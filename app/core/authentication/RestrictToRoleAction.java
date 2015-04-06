package core.authentication;

import models.VoltUser;
import play.mvc.Action;
import play.mvc.Result;
import play.libs.F;
import play.mvc.Http;
import play.mvc.With;

import core.authentication.VoltRole;
import core.authentication.RestrictToRegisteredAction.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class RestrictToRoleAction extends Action<RestrictToRoleAction.RestrictToRole> {

  @RestrictToRegistered
  public F.Promise<Result> call(Http.Context ctx) throws Throwable {
    VoltUser u = VoltUser.find.where().eq("username", ctx.session().get("username")).findUnique();
    String[] roleSplit = configuration.roles().toUpperCase().split(",");
    for (String s : roleSplit) {
      if (u == null ||
              VoltRole.valueOf(s.trim()) == u.role) {
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
    String roles();
  }
}