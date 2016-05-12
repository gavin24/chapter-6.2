package com.ackerman.j.gavin.zootrack.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.FoodRepositoryImpl;
import com.ackerman.j.gavin.zootrack.services.FoodService;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public class FoodServiceImpl extends Service implements FoodService {
    final private FoodRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static FoodServiceImpl service = null;

    public static FoodServiceImpl getInstance()
    {
        if(service == null)
            service = new FoodServiceImpl();
        return service;
    }

    private FoodServiceImpl()
    {
        repository = new FoodRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public FoodServiceImpl getService() {
            return FoodServiceImpl.this;
        }
    }
    @Override
    public boolean addStock(int stock,Food food) {
        try{

            Food found = repository.findById(food.getId());
            Food updatedFood = new Food.Builder()
                    .id(found.getId())
                    .price(found.getprice())
                    .name(found.getname())
                    .stock(found.getStock()+stock)
                    .type(found.getType())
                    .build();
            return repository.update(updatedFood).getId() == food.getId();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeStock(int stock,Food food) {
        try{

            Food found = repository.findById(food.getId());
            Food updatedFood = new Food.Builder()
                    .id(found.getId())
                    .price(found.getprice())
                    .name(found.getname())
                    .stock(found.getStock()-stock)
                    .type(found.getType())
                    .build();
            return repository.update(updatedFood).getId() == food.getId();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return false;
    }
}
