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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestLoginInvalidCharacters extends WithApplication {

  private String invalidCharacter;
  private boolean usernameTest;
  JsonNodeFactory factory;
  ObjectNode node;

  public TestLoginInvalidCharacters(String invalidCharacter, boolean usernameTest) {
    this.invalidCharacter = invalidCharacter;
    this.usernameTest = usernameTest;
  }

  @Before
  public void onStart() {
    factory = new JsonNodeFactory(true);
    node = factory.objectNode();
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
            new Object[][]{
                    { "'", true },
                    { "`", true },
                    { "/", true },
                    { "\\", true },
                    { "--", true },
                    { "#", true },
                    { "..", true },
                    { "(", true },
                    { ")", true },
                    { "'", false },
                    { "`", false },
                    { "/", false },
                    { "\\", false },
                    { "--", false },
                    { "#", false },
                    { "..", false },
                    { "(", false },
                    { ")", false }
            }
    );
  }

  @Test
  public void testCallLoginWithInvalidCredentials() {
    if (usernameTest) {
      node.put("username", invalidCharacter + "random@test.com").put("password", "password");
    } else {
      node.put("username", "random@test.com").put("password", invalidCharacter + "password");
    }
    FakeRequest faker = new FakeRequest(POST, "api/login").withJsonBody(node);
    Result result = callAction(
            routes.ref.LoginController.login(),
            faker
    );
    assertThat(status(result)).isEqualTo(OK);
    assertThat(contentAsString(result).contains("true"));
  }
}