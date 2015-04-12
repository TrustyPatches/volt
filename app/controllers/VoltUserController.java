package controllers;

import core.authentication.RestrictToRoleAction.*;
import core.authentication.VoltRole;
import models.VoltUser;
import play.libs.Json;
import play.mvc.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller to handle requests for data pertaining to the
 * VoltUser model.
 */
public class VoltUserController extends Controller {

  /**
   * Returns a list of all VoltUser objects currently registered with
   * the application.
   *
   * @return    Json serialized array of VoltUser objects
   */
  // TODO Make sure administrators only have access to members of their own ring (just in case)
  @RestrictToRole({ VoltRole.ADMINISTRATOR })
  public static Result getAll() {
    return ok(Json.toJson(VoltUser.find.all()));
  }

  /**
   * Returns a list of full names (not usernames) for all VoltUser
   * objects currently registered with the application.
   *
   * @return    Json serialized array of strings containing names.
   */
  @RestrictToRole({ VoltRole.ADMINISTRATOR })
  public static Result getAllNames() {
    List<String> names = VoltUser.find.all().stream()
            .map(u -> u.getFullName())
            .collect(Collectors.toList());
    return ok(Json.toJson(names));
  }
}