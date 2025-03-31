package com.foodiefly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodiefly.entity.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
//@Repository
//public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{
//
//	Restaurant findById(int rastaurantId);
//	
//}
