package com.yanado.controller.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.ShoppingDAO;
import com.yanado.dto.Shopping;
import com.yanado.service.ShoppingService;


@Controller
@SessionAttributes("shopping")
@RequestMapping("shopping/create")
public class CreateShoppingController {
	
	@Autowired
	private ShoppingService service;
	
	@ModelAttribute("shopping")
	public Shopping formBacking(HttpServletRequest request) {
		Shopping shopping = new Shopping();
		return shopping;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopping/form");
		mav.addObject("formtype", "update");
		return mav;
	}


	@RequestMapping(method = RequestMethod.POST)
	public String createShopping(@Valid @ModelAttribute("shopping") Shopping shopping, BindingResult result,
			SessionStatus status) {

		if (result.hasErrors()) {
			return "shopping/form";
		}

		service.createShopping(shopping);
		status.setComplete();
		return "redirect:/shopping/view/all";
	}

}
