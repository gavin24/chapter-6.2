package com.ackerman.j.gavin.zootrack.services;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Domain.Food;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public interface FoodService {
    Food addFood(Food food);
    int addStock(int stock, Food food);
   int removeStock(int stock, Food food);
}
