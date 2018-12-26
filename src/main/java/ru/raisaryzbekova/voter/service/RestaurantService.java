package ru.raisaryzbekova.voter.service;

import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAllByDate(LocalDate date);

    Restaurant getWithDishes(int id, LocalDate date);

    List<Restaurant> getListWithDishes(List<Integer> ids, LocalDate date);

    List<Restaurant> getAll();

    void update(Restaurant restaurant) throws NotFoundException;

    void delete(int id) throws NotFoundException;
}