package core.authentication;

import play.mvc.Result;
import play.libs.F;
import play.mvc.Http;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RestrictToRegisteredAction extends play.mvc.Action.Simple {

  public F.Promise<Result> call(Http.Context ctx) throws Throwable {
    System.out.println("Checking if registered");
    if (Authenticator.isLoggedIn() && Authenticator.isRegistered()) {
      return delegate.call(ctx);
    } else {
      Result unauthorized = Authenticator.onUnauthorized(ctx);
      return F.Promise.pure(unauthorized);
    }
  }

  @With(RestrictToRegisteredAction.class)
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RestrictToRegistered {
  }
}