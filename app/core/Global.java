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

      VoltUser testTeamLeader = new VoltUser("teamleader@test.com", "password", VoltRole.TEAM_LEADER);
      Ebean.save(testTeamLeader);

      VoltUser testGroupLeader = new VoltUser("groupleader@test.com", "password", VoltRole.GROUP_LEADER);
      Ebean.save(testGroupLeader);

      VoltUser testAdmin = new VoltUser("admin@test.com", "password", VoltRole.ADMINISTRATOR);
      Ebean.save(testAdmin);
    }
  }

  public void onStop(Application app) {
    Logger.info("Application shutdown...");
  }
}