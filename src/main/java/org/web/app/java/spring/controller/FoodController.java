package org.web.app.java.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.repo.FoodRepository;

@Controller
@RequestMapping("/foods")
public class FoodController {
	
	@Autowired
	private FoodRepository repo;
	
	@GetMapping()
	public String index(Model model) {
		
		List<Food> foods = repo.findAll();
		model.addAttribute("foods", foods);
		
		return "/foods/index";
	}
	
	@GetMapping("/show/{id}")
	public String showFood(Model model, @PathVariable("id") Integer foodId) {
		
		model.addAttribute("food", repo.findById(foodId).get());
		
		return "/foods/show";
	}

}
