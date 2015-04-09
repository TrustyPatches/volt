package core.authentication;

import models.VoltUser;
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

/**
 * An action to be composed with other actions using a custom
 * annotation. It should ensure that if a user is not registered
 * or is not a member of the appropriate role than the requested
 * action composed with this one does not execute and the user is
 * instead redirected to some error page or similar.
 *
 * Note that the action is passed a whitelist of roles which should
 * be able to access the delegate action and that all other roles
 * are blacklisted - BUT, if no annotation is used, the action is
 * available publicly. 'Publicly' means literally everyone, whether
 * they are a registered user or not.
 *
 * @see core.authentication.RestrictToRegisteredAction
 */
public class RestrictToRoleAction extends Action<RestrictToRoleAction.RestrictToRole> {

  /**
   * A custom action which first checks if the user is registered
   * and then whether that user's role matches any of the roles
   * specified.
   *
   * @param ctx The Http context of the current request.
   * @return    Either passes the request down to the delegate action
   * if authorization is approved, otherwise returns an unauthorized
   * Result.
   * @throws Throwable
   */
  @RestrictToRegistered
  public F.Promise<Result> call(Http.Context ctx) throws Throwable {
    // This should be taken care of by @RestrictToRegistered annotation
    // but at the moment this isn't getting called correctly
    if (Authenticator.isRegistered()) {
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

  /**
   * A custom annotation for the RestrictToRole action. The annotation
   * takes one parameter, an array of VoltRoles which are *permitted* to
   * access the action.
   */
  @With(RestrictToRoleAction.class)
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RestrictToRole {
    VoltRole[] value();
  }
}