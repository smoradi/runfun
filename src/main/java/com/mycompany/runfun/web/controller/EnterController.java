package com.mycompany.runfun.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.runfun.domain.User;

@RequestMapping("/enter")
@Controller
public class EnterController extends BaseController {

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String enterUser(@Valid User user, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			return VIEW_INDEX;
		}
		uiModel.asMap().clear();
		List<User> users = User.findUsersByUsernameEquals(user.getUsername()).getResultList();
		if (users.size() == 0) {
			user.persist();
		}
		return "redirect:/records/" + user.getUsername() + "?form";
	}
}
