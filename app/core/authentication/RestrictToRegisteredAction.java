package core.authentication;

import play.mvc.Result;
import play.libs.F;
import play.mvc.Http;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An action to be composed with other actions using a custom
 * annotation. It should ensure that if a user is not registered
 * to the application than the requested action composed with this
 * one does not execute and the user is instead redirected to some
 * error page or similar.
 *
 * @see core.authentication.RestrictToRoleAction
 */
public class RestrictToRegisteredAction extends play.mvc.Action.Simple {

  /**
   * A custom action which first checks if the user is registered.
   *
   * @param ctx The Http context of the current request.
   * @return    Either passes the request down to the delegate action
   * if authorization is approved, otherwise returns an unauthorized
   * Result.
   * @throws Throwable
   */
  public F.Promise<Result> call(Http.Context ctx) throws Throwable {
    if (Authenticator.isRegistered()) {
      return delegate.call(ctx);
    } else {
      Result unauthorized = Authenticator.onUnauthorized(ctx);
      return F.Promise.pure(unauthorized);
    }
  }

  /**
   * A custom annotation for the RestrictToRegistered action.
   * Without this annotation, the delegate action is available to
   * literally everyone (in the absence of other access control
   * mechanisms). Adding this annotation means only users registered
   * in the system will be allowed to access the requested resource
   * or action.
   */
  @With(RestrictToRegisteredAction.class)
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RestrictToRegistered {
  }
}