package ru.raisaryzbekova.voter.service;

import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DishService {

    Dish create(Dish dish, int restaurantId);

    Dish get(int id, int restaurantId) throws NotFoundException;

    List<Dish> getByDate(int restaurantId, LocalDate date);

    List<Dish> getAll(int restaurantId);

    void update(Dish dish, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;
}