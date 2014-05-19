package com.ecivil.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkNavigation {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("redirect:/index");
	}

}