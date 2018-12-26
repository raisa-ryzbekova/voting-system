package ru.raisaryzbekova.voter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.testdata.DishTestData;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.raisaryzbekova.voter.testdata.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected VoteService voteService;

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "NewRestaurant", "222225", "Улица, д.4", of(2018, Month.NOVEMBER, 7));
        Restaurant created = restaurantService.create(new Restaurant(newRestaurant));
        newRestaurant.setId(created.getId());
        assertMatch(restaurantService.getAll(), newRestaurant, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void duplicateNameCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                restaurantService.create(new Restaurant(null, "Ресторан 3", "222225", "Улица, д.4", of(2018, Month.NOVEMBER, 7))));
    }

    @Test
    void validation() throws Exception {
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "N", "222225", "Улица, д.4", of(2018, Month.NOVEMBER, 7))), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "  ", "222225", "Улица, д.4", of(2018, Month.NOVEMBER, 11))), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "NewRestaurant", "22222", "Улица, д.4", of(2018, Month.NOVEMBER, 11))), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "NewRestaurant", "2222255", "Улица, д.4", of(2018, Month.NOVEMBER, 11))), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "NewRestaurant", "222225", "  ", of(2018, Month.NOVEMBER, 11))), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "NewRestaurant", "222225", "  ", null)), ConstraintViolationException.class);
    }

    @Test
    void get() throws Exception {
        Restaurant restaurant = restaurantService.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.get(1));
    }

    @Test
    void getAllByDate() throws Exception {
        List<Restaurant> allByDate = restaurantService.getAllByDate(of(2018, Month.NOVEMBER, 7));
        assertMatch(allByDate, RESTAURANT_1, RESTAURANT_3);
    }

    @Test
    void getWithDishes() throws Exception {
        Restaurant restaurant = restaurantService.getWithDishes(RESTAURANT1_ID, of(2018, Month.NOVEMBER, 7));
        assertMatch(restaurant, RESTAURANT_1);
        DishTestData.assertMatch(restaurant.getDishes(), DishTestData.DISH1, DishTestData.DISH2, DishTestData.DISH3);
    }

    @Test
    void getWithDishesNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.getWithDishes(1, of(2018, Month.NOVEMBER, 7)));
    }

    @Test
    void getListGetWithDishes() throws Exception {
        List<Restaurant> restaurants = restaurantService.getListWithDishes(RESTAURANTS_ID, of(2018, Month.NOVEMBER, 7));
        assertMatch(restaurants.get(0), RESTAURANT_1);
        assertMatch(restaurants.get(1), RESTAURANT_3);
        DishTestData.assertMatch(restaurants.get(0).getDishes(), DishTestData.DISH1, DishTestData.DISH2, DishTestData.DISH3);
        DishTestData.assertMatch(restaurants.get(1).getDishes(), DishTestData.DISH8, DishTestData.DISH9, DishTestData.DISH10);
    }

    @Test
    void getAll() throws Exception {
        List<Restaurant> all = restaurantService.getAll();
        assertMatch(all, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("UpdatedName");
        updated.setPhone("333333");
        restaurantService.update(new Restaurant(updated));
        assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    void delete() throws Exception {
        restaurantService.delete(RESTAURANT1_ID);
        assertMatch(restaurantService.getAll(), RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.delete(1));
    }
}