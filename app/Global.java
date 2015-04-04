import play.GlobalSettings;
import securesocial.core.RuntimeEnvironment;
import service.MyEnvironment;

public class Global extends GlobalSettings {
  private RuntimeEnvironment env = new MyEnvironment();

  @Override
  public <A> A getControllerInstance(Class<A> controllerClass) throws Exception {
    A result;

    try {
      result = controllerClass.getDeclaredConstructor(RuntimeEnvironment.class).newInstance(env);
    } catch (NoSuchMethodException e) {
      result = super.getControllerInstance(controllerClass);
    }
    return result;
  }
}
