package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

import org.junit.Test;

import play.test.WithApplication;
import play.mvc.Result;
import play.test.FakeRequest;

public class TestLoginController extends WithApplication {

  @Test
  public void testCallGetUsernameWithSetCookie() {
    Result result = callAction(
            routes.ref.LoginController.getUsername(),
            new FakeRequest(GET, "/api/profile/username").withSession("username", "MrTest")
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).contains("MrTest"));
  }

  @Test
  public void testCallGetUsernameWithoutCookie() {
    Result result = callAction(
            routes.ref.LoginController.getUsername(),
            new FakeRequest(GET, "/api/profile/username")
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).isEmpty());
  }

  @Test
  public void testCallIsLoggedInTrue() {
    Result result = callAction(
            routes.ref.LoginController.isLoggedIn(),
            new FakeRequest(GET, "/api/profile/loggedin").withSession("username", "MrTest")
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).equals("true"));
  }

  @Test
  public void testCallIsLoggedInFalse() {
    Result result = callAction(
            routes.ref.LoginController.isLoggedIn(),
            new FakeRequest(GET, "api/profile/loggedin")
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).equals("false"));
  }

  @Test
  public void testLogout() {
    FakeRequest faker = new FakeRequest(GET, "api/logout").withSession("username", "MrTest");
    assertThat(faker.getWrappedRequest().session().get("username").equals("MrTest"));
    Result result = callAction(
            routes.ref.LoginController.logout(),
            faker
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(faker.getWrappedRequest().session().isEmpty());
  }
}