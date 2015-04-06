package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import models.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import core.authentication.VoltRole;

@Entity
public class VoltUser extends Model implements User {

  @Id
  public String username;
  @Constraints.Required
  public String password;
  @Constraints.Required
  public VoltRole role;

  public VoltUser(String email, String password, VoltRole role) {
    this.username = email;
    this.password = password;
    this.role = role;
  }

  public static Finder<Long, VoltUser> find = new Finder<Long, VoltUser>(
          Long.class, VoltUser.class
  );

}