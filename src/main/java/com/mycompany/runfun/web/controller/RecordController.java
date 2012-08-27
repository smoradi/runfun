package com.mycompany.runfun.web.controller;

import com.mycompany.runfun.domain.Record;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/records")
@Controller
@RooWebScaffold(path = "records", formBackingObject = Record.class)
public class RecordController {
}
