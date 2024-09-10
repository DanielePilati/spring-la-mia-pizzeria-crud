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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.repo.FoodRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	private FoodRepository repo;

	// READ
	@GetMapping()
	public String index(Model model) {

		List<Food> foods = repo.findAll();
		model.addAttribute("foods", foods);
		model.addAttribute("search", new Food());

		return "/foods/index";
	}

	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable("id") Integer foodId) {

		model.addAttribute("foods", repo.findById(foodId).get());

		return "/foods/show";
	}

	@GetMapping("/search/")
	public String search(Model model, @RequestParam("name") String name) {

		model.addAttribute("search", new Food());
		model.addAttribute("foods", repo.findByNameContains(name));

		return "/foods/index";
	}

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("food", new Food());

		return "/foods/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("food") Food formFood, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/foods/create";
		}

		repo.save(formFood);
		
		attributes.addFlashAttribute("message", "Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/foods";
	}

	// UPDATE
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer foodId) {

		model.addAttribute("food", repo.findById(foodId).get());

		return "/foods/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("food") Food formFood, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/foods/edit";
		}

		repo.save(formFood);

		attributes.addFlashAttribute("food", repo.findById(formFood.getId()).get());
		attributes.addFlashAttribute("message", "Updated");
		attributes.addFlashAttribute("class", "warning");

		return "redirect:/foods";
	}

	// DELETE
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer foodId, RedirectAttributes attributes) {

		repo.deleteById(foodId);

		attributes.addFlashAttribute("message", "Eliminated");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/foods";
	}

}
