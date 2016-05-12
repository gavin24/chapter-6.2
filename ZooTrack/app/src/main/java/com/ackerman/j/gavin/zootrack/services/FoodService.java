package com.ackerman.j.gavin.zootrack.services;

import com.ackerman.j.gavin.zootrack.Domain.Food;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public interface FoodService {
    boolean addStock(int stock, Food food);
    boolean removeStock(int stock, Food food);
}
