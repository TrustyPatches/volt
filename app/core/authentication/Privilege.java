package core.authentication;

import java.util.EnumSet;
import java.util.Set;

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