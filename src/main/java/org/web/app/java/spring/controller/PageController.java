package org.web.app.java.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.repo.FoodRepository;


@Controller
@RequestMapping("/")
public class PageController {
	
	@Autowired
	private FoodRepository repo;
	
	@GetMapping()
	public String homePage(Model model) {
		
		List<Food> foods = repo.findAll();
		model.addAttribute("foods", foods);
		
		return "/pages/home";
	}

}
