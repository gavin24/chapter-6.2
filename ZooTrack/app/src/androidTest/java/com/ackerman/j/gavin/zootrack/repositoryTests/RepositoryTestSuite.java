package com.ackerman.j.gavin.zootrack.repositoryTests;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AnimalRepoTest.class,
        EmployeeRepoTest.class,
        EnclosureRepoTest.class,
        FoodRepoTest.class,
        ScheduleRepoTest.class,
        ShowRepoTest.class,
        StaffRepoTest.class,
        TicketsRepoTest.class
})

public class RepositoryTestSuite {

}
