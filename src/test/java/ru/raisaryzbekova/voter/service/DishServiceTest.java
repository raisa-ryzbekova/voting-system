package ru.raisaryzbekova.voter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.raisaryzbekova.voter.testdata.DishTestData.*;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.RESTAURANT1_ID;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;

    @Test
    void create() throws Exception {
        Dish created = getCreated();
        service.create(created, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), created, DISH1, DISH2, DISH3, DISH4);
        LocalDate expected = created.getDate();
        LocalDate actual = service.get(created.getId(), RESTAURANT1_ID).getDate();
        assertEquals(expected, actual);
    }

    @Test
    void duplicateDescriptionCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new Dish(null, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 1 (Ресторан 1)", 500), RESTAURANT1_ID));
    }

    @Test
    void validation() throws Exception {
        validateRootCause(() -> service.create(new Dish(null, null, "Бизнес-ланч", 500), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Dish(null, of(2018, Month.NOVEMBER, 11), "  ", 500), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Dish(null, of(2018, Month.NOVEMBER, 11), "Б", 500), RESTAURANT1_ID), ConstraintViolationException.class);
    }

    @Test
    void get() throws Exception {
        Dish actual = service.get(DISH1_ID, RESTAURANT1_ID);
        assertMatch(actual, DISH1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(DISH1_ID, 1));
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(RESTAURANT1_ID), DISHES);
    }

    @Test
    void getByDate() throws Exception {
        assertMatch(service.getByDate(RESTAURANT1_ID, of(2018, Month.NOVEMBER, 7)), DISHES_BY_DATE);
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void updateNotFound() throws Exception {
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(DISH1, 1));
        assertEquals(e.getMessage(), "Not found entity with id=" + DISH1_ID);
    }

    @Test
    void delete() throws Exception {
        service.delete(DISH1_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), DISH2, DISH3, DISH4);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(DISH1_ID, 1));
    }
}