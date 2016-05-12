package com.ackerman.j.gavin.zootrack.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Repository.AnimalRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.AnimalRepositoryImpl;
import com.ackerman.j.gavin.zootrack.services.AnimalService;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public class AnimalServiceImpl extends Service implements AnimalService {

    final private AnimalRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static AnimalServiceImpl service = null;

    public static AnimalServiceImpl getInstance()
    {
        if(service == null)
            service = new AnimalServiceImpl();
        return service;
    }

    private AnimalServiceImpl()
    {
        repository = new AnimalRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public AnimalServiceImpl getService() {
            return AnimalServiceImpl.this;
        }
    }

    /*
    *Used to add an animal to the database
    *
     */
    @Override
    public Animal addAnimal(Animal animal) {
        try{
            return repository.save(animal);
        }
        catch(Exception x)

       {
            x.printStackTrace();
        }
        return null;
    }
    @Override
    public Animal deleteAnimal(Animal animal) {
        return repository.delete(animal);
    }
  /*  @Override
    public Set<Animal> getAllAnimals() {
        Set<Animal> animals;
        animals = repository.findAll();
        return animals;
    }
*/
    @Override
    public ArrayList<Animal> getAllAnimals() {
        try {
            ArrayList<Animal> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Animal>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public int removeAllAnimals() {
        return repository.deleteAll();
    }
    @Override
    public Animal updateAnimal(Animal animal) {
        return repository.update(animal);
    }
}
