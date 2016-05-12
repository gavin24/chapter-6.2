package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Factory.AnimalFactory;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class AnimalFactoryImpl implements AnimalFactory {
    private static AnimalFactoryImpl factory = null;

    private AnimalFactoryImpl(){

    }

    public static AnimalFactoryImpl getInstance(){
        if (factory == null)
            factory = new AnimalFactoryImpl();

        return factory;
    }
    @Override
    public Animal createAnimal(Long id,String name, String species, int age, String Country,Food food)
    {
        Animal animal = new Animal.Builder().id(id).name(name).species(species).age(age).Country(Country).food(food)
                .build();

        return animal;
    }
}