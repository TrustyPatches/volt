package core;

import com.avaje.ebean.Ebean;
import play.*;
import models.User;

public class Global extends GlobalSettings {

  public void onStart(Application app) {

    if (User.find.all().size() == 0) {
      User testUser = new User("test@test.com", "password");
      Ebean.save(testUser);
    }
  }

  public void onStop(Application app) {
    Logger.info("Application shutdown...");
  }
}