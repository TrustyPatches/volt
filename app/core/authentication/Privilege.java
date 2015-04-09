package core.authentication;

import java.util.EnumSet;
import java.util.Set;

/**
 * Currently unused. Was initially to be used to assign privileges to roles
 * and allow greater granularity and generality to the access control. At
 * the moment the trade off between those positives and the increase in
 * possibility of introducing errors into the access control configuration
 * haven't made it worth it. If the access control mechanism is lifted out
 * and generalised as a lightweight framework it is likely that a Privilege
 * class like this one would be used to form the bridge between roles and
 * allowed actions and would be customised by the implementation.
 *
 * One of the reasons why it doesn't seem necessary right now is because
 * the role system happens to cascade: Admin > Group Leader > Team Leader
 * > Volunteer and any new roles are likely to slot into this cascading
 * model.
 */
public enum Privilege {

  VOLUNTEER(1<<0),
  TEAM_LEADER(1<<1),
  GROUP_LEADER(1<<2),
  ADMIN(1<<3);

  private final long privilegeValue;

  Privilege(long privilegeValue) {
    this.privilegeValue = privilegeValue;
  }

  public long getPrivilegeValue() {
    return privilegeValue;
  }

  public static EnumSet<Privilege> getPrivilegeSet(long privilegeValue) {
    EnumSet privilegeFlags = EnumSet.noneOf(Privilege.class);
    for (Privilege p : Privilege.values()) {
      long flagValue = p.privilegeValue;
      if ((flagValue & privilegeValue) == flagValue) {
        privilegeFlags.add(p);
      }
    }
    return privilegeFlags;
  }

  public long getPrivilegeSetValue(Set<Privilege> flags) {
    long value = 0;
    for (Privilege p : flags) {
      value |= p.getPrivilegeValue();
    }
    return value;
  }
}