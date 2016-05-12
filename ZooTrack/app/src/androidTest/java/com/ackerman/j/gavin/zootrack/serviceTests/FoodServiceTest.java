package com.ackerman.j.gavin.zootrack.serviceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.FoodRepositoryImpl;
import com.ackerman.j.gavin.zootrack.services.Impl.FoodServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by gavin.ackerman on 2016-05-11.
 */
public class FoodServiceTest extends AndroidTestCase {
    private FoodServiceImpl foodService;
    private boolean isBound;
    Food food = new Food();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), FoodServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        food = new Food.Builder()
                .name("Chicken")
                .type("Meat")
                .price(500)
                .stock(30)

                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FoodServiceImpl.ActivateServiceLocalBinder binder
                    = (FoodServiceImpl.ActivateServiceLocalBinder) service;
            foodService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testAddStock() {

        Food createEntity = new Food.Builder()
                .name("Chicken")
                .type("Meat")
                .price(500)
                .stock(30)
                .build();
        foodService.addFood(createEntity);
       Assert.assertEquals(35, foodService.addStock(5, createEntity));

    }
    @Test
    public void testRemoveStock() {
        Food createEntity = new Food.Builder()
                .name("Beef")
                .type("Meat")
                .price(500)
                .stock(30)
                .build();
        foodService.addFood(createEntity);
        Assert.assertEquals(25, foodService.removeStock(5, createEntity));
    }
}
