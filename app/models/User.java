package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

  @Id
  public String email;
  @Constraints.Required
  public String password;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  } 

  public static Finder<Long, User> find = new Finder<Long, User>(
          Long.class, User.class
  );

}