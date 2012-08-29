package com.mycompany.runfun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.runfun.domain.User;

@RequestMapping("/")
@Controller
public class IndexController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel) {
		uiModel.addAttribute("user", new User());
		return VIEW_INDEX;
	}
	
}
