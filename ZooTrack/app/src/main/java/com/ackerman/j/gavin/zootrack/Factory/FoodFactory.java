package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Food;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface FoodFactory {

    Food createFood(Long id,float price,String name, String type,int stock);
}
