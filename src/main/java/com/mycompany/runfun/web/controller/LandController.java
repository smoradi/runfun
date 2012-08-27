package com.mycompany.runfun.web.controller;

import com.mycompany.runfun.domain.Land;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lands")
@Controller
@RooWebScaffold(path = "lands", formBackingObject = Land.class)
public class LandController {
}
