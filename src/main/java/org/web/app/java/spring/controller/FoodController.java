package org.web.app.java.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.model.Search;
import org.web.app.java.spring.repo.FoodRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	private FoodRepository repo;

	@GetMapping()
	public String index(Model model) {

		List<Food> foods = repo.findAll();
		model.addAttribute("foods", foods);
		model.addAttribute("search", new Search());

		return "/foods/index";
	}

	@GetMapping("/show/{id}")
	public String showFood(Model model, @PathVariable("id") Integer foodId) {

		model.addAttribute("foods", repo.findById(foodId).get());

		return "/foods/show";
	}

	@GetMapping("/search/")
	public String searchName(Model model, @RequestParam("name") String name) {

		model.addAttribute("search", new Search());
		model.addAttribute("foods", repo.findByNameContains(name));

		return "/foods/index";
	}

	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("food", new Food());

		return "/foods/create";
	}

	@PostMapping("/create")
	public String add(@Valid @ModelAttribute("food") Food formFood, BindingResult br, Model model) {

		if (br.hasErrors()) {
			return "/foods/create";
		}

		repo.save(formFood);

		return "redirect:/foods";
	}

}
