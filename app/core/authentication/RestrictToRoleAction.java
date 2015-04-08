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

    VoltUser u = VoltUser.find.where().eq("username", ctx.session().get("username")).findUnique();
    VoltRole[] roleSplit = configuration.value();
    System.out.println(u.role);
    for (VoltRole s : roleSplit) {
      if (s == u.role) {
        return delegate.call(ctx);
      }
    }
    Result unauthorized = Authenticator.onUnauthorized(ctx);
    return F.Promise.pure(unauthorized);
  }

//  @RestrictToRegistered
//  public F.Promise<Result> call(Http.Context ctx) throws Throwable {
//    VoltUser u = VoltUser.find.where().eq("username", ctx.session().get("username")).findUnique();
//    String[] roleSplit = configuration.value().toUpperCase().split(",");
//    for (String s : roleSplit) {
//      if (u == null ||
//              VoltRole.valueOf(s.trim()) == u.role) {
//        return delegate.call(ctx);
//      }
//    }
//    Result unauthorized = Authenticator.onUnauthorized(ctx);
//    return F.Promise.pure(unauthorized);
//  }

  @With(RestrictToRoleAction.class)
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RestrictToRole {
    VoltRole[] value();
  }
}