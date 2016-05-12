package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.FoodRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class FoodRepoTest extends AndroidTestCase {
    private static final String TAG="Food TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        FoodRepository repo = new FoodRepositoryImpl(this.getContext());
        // CREATE  price(price).name(name).type(type)
        Food createEntity = new Food.Builder()
                .name("Chicken")
                .type("Meat")
                .price(500)
                .stock(30)
                .build();
        Food insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Food> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Food entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Food updateEntity = new Food.Builder()
                .copy(entity)
                .name("beef")
                .build();
        repo.update(updateEntity);
        Food newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","beef",newEntity.getname());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Food deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
