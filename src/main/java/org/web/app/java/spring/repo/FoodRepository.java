package org.web.app.java.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>  {

	 public List<Food> findByNameContains(String name);

}
