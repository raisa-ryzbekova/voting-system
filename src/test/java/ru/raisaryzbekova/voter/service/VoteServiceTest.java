package ru.raisaryzbekova.voter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.raisaryzbekova.voter.model.Vote;
import ru.raisaryzbekova.voter.testdata.RestaurantTestData;
import ru.raisaryzbekova.voter.util.exception.LateForVoteException;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.LocalTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.raisaryzbekova.voter.TestUtil.setTimeAfter;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.*;
import static ru.raisaryzbekova.voter.testdata.UserTestData.ADMIN_ID;
import static ru.raisaryzbekova.voter.testdata.UserTestData.USER1_ID;
import static ru.raisaryzbekova.voter.testdata.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Autowired
    protected VoteServiceImpl voteService;

    @BeforeEach
    void setTimeBefore() {
        voteService.localTime = LocalTime.of(10, 59);
    }

    @Test
    void createAfter() throws Exception {
        Vote created = getCreated();
        voteService.localTime = LocalTime.of(11, 00);
        setTimeAfter(created);
        assertThrows(LateForVoteException.class, () -> service.create(created, USER1_ID, RESTAURANT1_ID));
    }

    @Test
    void createBefore() throws Exception {
        Vote created = getCreated();
        service.create(created, USER1_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(USER1_ID), created, VOTE2);
    }

    @Test
    void duplicateDateCreate() throws Exception {
        Vote created = getDuplicateCreated();
        assertThrows(DataAccessException.class, () -> service.create(created, USER1_ID, RESTAURANT3_ID));
    }

    @Test
    void validation() throws Exception {
        validateRootCause(() -> service.create(new Vote(null, null, of(10, 0)), USER1_ID, RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Vote(of(2018, Month.NOVEMBER, 11), null), USER1_ID, RESTAURANT1_ID), ConstraintViolationException.class);
    }

    @Test
    void get() throws Exception {
        Vote actual = service.get(VOTE2_ID, USER1_ID);
        assertMatch(actual, VOTE2);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(VOTE1_ID, 1));
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(ADMIN_ID), ADMIN_VOTES);
    }

    @Test
    void getListGetWithRestaurants() throws Exception {
        List<Vote> votes = service.getWithRestaurants(of(2018, Month.NOVEMBER, 7));
        assertMatch(votes.get(0), VOTE1);
        assertMatch(votes.get(1), VOTE2);
        assertMatch(votes.get(2), VOTE3);
        RestaurantTestData.assertMatch(votes.get(0).getRestaurant(), RESTAURANT_3);
        RestaurantTestData.assertMatch(votes.get(1).getRestaurant(), RESTAURANT_3);
        RestaurantTestData.assertMatch(votes.get(2).getRestaurant(), RESTAURANT_1);
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        service.update(updated, USER1_ID, RESTAURANT3_ID);
        assertMatch(service.get(VOTE2_ID, USER1_ID), updated);
    }

    @Test
    void updateNotFound() throws Exception {
        Vote updated = getUpdated();
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(VOTE2, 1, 1));
        assertEquals(e.getMessage(), "Not found entity with id=" + VOTE2_ID);
    }

    @Test
    void delete() throws Exception {
        service.delete(VOTE1_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), VOTE4);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(VOTE1_ID, 1));
    }
}