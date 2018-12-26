package ru.raisaryzbekova.voter.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.AbstractBaseEntity;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.service.RestaurantService;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.raisaryzbekova.voter.util.ValidationUtil.assureIdConsistent;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public Restaurant get(int id) throws NotFoundException {
        log.info("get {}", id);
        return restaurantService.get(id);
    }

    public Restaurant getWithDishes(int id, LocalDate date) {
        log.info("getWithDishes {} by date {}", id, date);
        return restaurantService.getWithDishes(id, date);
    }

    public List<Restaurant> getListWithDishes(LocalDate date) {
        List<Integer> ids = this.idsRestaurantsByDate(date);
        log.info("getListWithDishes {} by date {}", ids, date);
        return restaurantService.getListWithDishes(ids, date);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    public void update(Restaurant restaurant, int id) throws NotFoundException {
        log.info("update {} with id={}", restaurant, restaurant.getId());
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public void delete(int id) throws NotFoundException {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    private List<Integer> idsRestaurantsByDate(LocalDate date) {
        List<Restaurant> restaurants = restaurantService.getAllByDate(date);
        return restaurants.stream().map(AbstractBaseEntity::getId).collect(Collectors.toList());
    }
}