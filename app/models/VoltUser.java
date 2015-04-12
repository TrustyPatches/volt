package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import models.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import core.authentication.VoltRole;

/**
 * A model representing a user of the Volt application.
 */
@Entity
public class VoltUser extends Model implements User {

  @Id
  public String username;
  @Constraints.Required
  public String password;
  @Constraints.Required
  public VoltRole role;
  @Constraints.Required
  public String chapter;
  @Constraints.Required
  public String ring;
  @Constraints.Required
  public String firstName;
  @Constraints.Required
  public String lastName;
  public String email;

  public VoltUser(String email, String password, VoltRole role) {
    this.username = email;
    this.email = email; // TODO: right now usernames must be an email (should be made optional)
    this.password = password;
    this.role = role;
    this.chapter = "testchapter";
    this.ring = "testring";
    this.firstName = "Mr Test";
    this.lastName = "McTesterson";
  }

  @Override
  public String toString() { return getFullName() + ", " + ring + ", " + chapter; }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public static Finder<Long, VoltUser> find = new Finder<Long, VoltUser>(
          Long.class, VoltUser.class
  );

}