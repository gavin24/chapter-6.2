package com.ackerman.j.gavin.zootrack.serviceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by gavin.ackerman on 2016-05-11.
 */
public class AnimalServiceTest extends AndroidTestCase
{
    private AnimalServiceImpl animalService;
    private boolean isBound;
    Animal animal = new Animal();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), AnimalServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        animal = new Animal.Builder()
                .name("John")
                .species("bear")
                .age(24)
                .Country("england")
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AnimalServiceImpl.ActivateServiceLocalBinder binder
                    = (AnimalServiceImpl.ActivateServiceLocalBinder) service;
            animalService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testGetAllAnimals()
    {
        Assert.assertNotNull(animalService.getAllAnimals());
    }

    @Test
    public void testCreateAnimal()
    {
        Animal animal = new Animal.Builder()
                .name("John")
                .species("bear")
                .age(24)
                .Country("england")
                .build();
        animal = animalService.addAnimal(animal);
        Assert.assertNotNull(animal.getId());
    }

    @Test
    public void testUpdateAnimal()
    {
        Animal animal = new Animal.Builder()
                .name("Peter")
                .species("bear")
                .age(24)
                .Country("england")
                .build();
        animalService.updateAnimal(animal);
      //  Animal newEntity = animalService.(id);
        Assert.assertEquals("george",animal.getName());
       // Assert.assertTrue(animalService.updateAnimal(animal));
    }
}
