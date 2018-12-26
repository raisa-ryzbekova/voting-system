package ru.raisaryzbekova.voter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.repository.RestaurantRepository;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.*;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Override
    public List<Restaurant> getAllByDate(LocalDate date) {
        return restaurantRepository.getAllByDate(date);
    }

    @Override
    public Restaurant getWithDishes(int id, LocalDate date) {
        return checkNotFoundWithId(restaurantRepository.getWithDishes(id, date), id);
    }

    @Override
    public List<Restaurant> getListWithDishes(List<Integer> ids, LocalDate date) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Integer id : ids) {
            restaurants.add(this.getWithDishes(id, date));
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }
}