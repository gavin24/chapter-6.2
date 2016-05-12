package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Repository.Impl.ShowRepositoryImpl;
import com.ackerman.j.gavin.zootrack.Repository.ShowRepository;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class ShowRepoTest extends AndroidTestCase {
    private static final String TAG="Show TEST";
    private Long id;
    private Date day = new Date(14/06/2014);
    public void testCreateReadUpdateDelete() throws Exception {
        ShowRepository repo = new ShowRepositoryImpl(this.getContext());
        // CREATE    .name(name).day(showDay)
        Show createEntity = new Show.Builder()
                .name("gavin")
                .day(day)
                .build();
        Show insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Show> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Show entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Show updateEntity = new Show.Builder()
                .copy(entity)
                .name("tommy")
                .build();
        repo.update(updateEntity);
        Show newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","tommy",newEntity.getname());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Show deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
