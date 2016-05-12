package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Enclosure;
import com.ackerman.j.gavin.zootrack.Repository.EnclosureRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.EnclosureRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class EnclosureRepoTest extends AndroidTestCase {
    private static final String TAG="Enclosure TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        EnclosureRepository repo = new EnclosureRepositoryImpl(this.getContext());
        // CREATE
        Enclosure createEntity = new Enclosure.Builder()
                .type("steel")
                .name("fence")
                .coach("adrian")
                .build();
        Enclosure insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Enclosure> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Enclosure entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Enclosure updateEntity = new Enclosure.Builder()
                .copy(entity)
                .type("wood")
                .build();
        repo.update(updateEntity);
        Enclosure newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","wood",newEntity.getType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Enclosure deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
