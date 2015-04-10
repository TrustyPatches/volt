package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.Before;

import play.test.WithApplication;
import play.mvc.Result;
import play.test.FakeRequest;

public class TestLoginRequests extends WithApplication {

  JsonNodeFactory factory;
  ObjectNode node;

  @Before
  public void onStart() {
    factory = new JsonNodeFactory(true);
    node = factory.objectNode();
  }

  @Test
  public void testCallLoginWithValidCredentials() {
    // TODO Once test creds are removed from database, test will fail
    /*
    Tests should be set up to use either a test in memory DB or faked global
    class. Consider both in case tests running in parallel to application
    affect prod database (!)
     */
    node.put("username", "test@test.com").put("password", "password");
    FakeRequest faker = new FakeRequest(POST, "api/login").withJsonBody(node);
    Result result = callAction(
            routes.ref.LoginController.login(),
            faker
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).contains("true"));
    System.out.println(faker.getWrappedRequest().session().data().keySet());
//    assertThat(faker.getWrappedRequest().session().get("username")).isEqualTo("test@test.com");
  }

  @Test
  public void testCallLoginWithInvalidCredentials() {
    node.put("username", "random@test.com").put("password", "password");
    FakeRequest faker = new FakeRequest(POST, "api/login").withJsonBody(node);
    Result result = callAction(
            routes.ref.LoginController.login(),
            faker
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).contains("false"));
  }

}