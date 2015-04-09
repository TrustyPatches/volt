package core.authentication;

/**
 * A list of possible Roles specific to the Volt platform.
 * The roles cascade from left to right so an Administrator
 * has all the rights of an Administrator and all roles to
 * the right and a Group Leader has the rights of a Group
 * Leader and a Volunteer but none of any of the roles to
 * the left and so on.
 *
 * Roles should always be accessed by name, not by their
 * ordering. This is not just because the order may change,
 * but mainly so that uses of a VoltRole are explicit and
 * what is being intended is clear in the code.
 */
public enum VoltRole {
  ADMINISTRATOR, TEAM_LEADER, GROUP_LEADER, VOLUNTEER
}