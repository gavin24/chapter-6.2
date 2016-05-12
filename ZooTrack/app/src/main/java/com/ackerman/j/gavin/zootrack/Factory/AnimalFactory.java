package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Domain.Food;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface AnimalFactory {

    Animal createAnimal(Long id,String name, String species, int age, String Country,Food food);

}
