package com.mycompany.runfun.web.controller;

import com.mycompany.runfun.domain.User;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
@RooWebScaffold(path = "users", formBackingObject = User.class, create = false, update = false)
public class UserController {
}
