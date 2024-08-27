package org.web.app.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foods")
public class FoodController {
	
	@GetMapping("/")
	public String food(Model model) {
		
		
		return "/foods/index";
	}

}
