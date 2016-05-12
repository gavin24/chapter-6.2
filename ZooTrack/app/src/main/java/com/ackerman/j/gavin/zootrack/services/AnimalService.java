package com.ackerman.j.gavin.zootrack.services;

import android.content.Context;

import com.ackerman.j.gavin.zootrack.Domain.Animal;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public interface AnimalService {
    Animal addAnimal(Animal animal);
    Animal updateAnimal( Animal animal);
   Animal deleteAnimal( Animal animal);
    ArrayList<Animal> getAllAnimals( );
    int removeAllAnimals();
}
