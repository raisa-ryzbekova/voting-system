package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getByDate(int restaurantId, LocalDate date);

    List<Dish> getAll(int restaurantId);

    boolean delete(int id, int restaurantId);
}