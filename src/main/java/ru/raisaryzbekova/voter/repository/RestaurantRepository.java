package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    List<Restaurant> getAllByDate(LocalDate date);

    List<Restaurant> getAll();

    boolean delete(int id);

    default Restaurant getWithDishes(int id, LocalDate date) {
        throw new UnsupportedOperationException();
    }
}