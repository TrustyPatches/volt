package service;

import securesocial.core.RuntimeEnvironment;
import securesocial.core.services.UserService;

class MyEnvironment extends RuntimeEnvironment.Default[DemoUser] {
  override val userService: UserService[DemoUser] = new InMemoryUserService()
}
