package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.Before;

import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;
import play.mvc.Result;
import play.test.FakeRequest;

public class TestSignUpController extends WithApplication {

  JsonNodeFactory factory;
  ObjectNode node;

  @Before
  public void onStart() {
    factory = new JsonNodeFactory(true);
    node = factory.objectNode();

    // Does this really access configured h2 database "test" set in application.conf?
    FakeApplication fake = Helpers.fakeApplication(inMemoryDatabase("test"));
  }

  @Test
  public void testCallSignUpWithFilledInFields() {
    node.put("username", "MrTest").put("password", "password");
    Result result = callAction(
            routes.ref.SignUpController.signUp(),
            new FakeRequest(POST, "api/signup").withJsonBody(node)
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result)).isEqualTo("true");
  }

  @Test
  public void testCallSignUpWithEmptyUsername() {
    node.put("username", "").put("password", "password");
    Result result = callAction(
            routes.ref.SignUpController.signUp(),
            new FakeRequest(POST, "api/signup").withJsonBody(node)
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result)).isEqualTo("false");
  }

  @Test
  public void testCallSignUpWithEmptyPassword() {
    node.put("username", "MrTest").put("password", "");
    Result result = callAction(
            routes.ref.SignUpController.signUp(),
            new FakeRequest(POST, "api/signup").withJsonBody(node)
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result)).isEqualTo("false");
  }
}