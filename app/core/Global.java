package core;

import com.avaje.ebean.Ebean;
import core.authentication.VoltRole;
import play.*;
import models.VoltUser;

public class Global extends GlobalSettings {

  public void onStart(Application app) {

    if (VoltUser.find.all().size() == 0) {
      VoltUser testUser = new VoltUser("test@test.com", "password", VoltRole.VOLUNTEER);
      Ebean.save(testUser);

      VoltUser testLeader = new VoltUser("testleader@test.com", "password", VoltRole.GROUP_LEADER);
      Ebean.save(testLeader);
    }
  }

  public void onStop(Application app) {
    Logger.info("Application shutdown...");
  }
}