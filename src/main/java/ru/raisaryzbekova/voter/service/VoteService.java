package ru.raisaryzbekova.voter.service;

import ru.raisaryzbekova.voter.model.Vote;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote create(Vote vote, int userId, int restaurantId);

    Vote get(int id, int userId) throws NotFoundException;

    List<Vote> getWithRestaurants(LocalDate date);

    List<Vote> getAll(int userId);

    void update(Vote vote, int userId, int restaurantId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;
}