package org.web.app.java.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>  {

}
