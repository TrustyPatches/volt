package controllers;

import core.authentication.RestrictToRoleAction.*;
import models.VoltUser;
import play.libs.Json;
import play.mvc.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserController extends Controller {

//  @RestrictToRole("ADMINISTRATOR")
  public static Result getAll() {
    return ok(Json.toJson(VoltUser.find.all()));
  }

//  @RestrictToRole("ADMINISTRATOR")
  public static Result getAllNames() {
    List<String> names = VoltUser.find.all().stream()
            .map(u -> u.getFullName())
            .collect(Collectors.toList());
    return ok(Json.toJson(names));
  }

}