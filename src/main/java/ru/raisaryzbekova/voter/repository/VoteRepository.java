package ru.raisaryzbekova.voter.repository;

import ru.raisaryzbekova.voter.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    Vote get(int id, int userId);

    List<Vote> getAll(int userId);

    boolean delete(int id, int userId);

    default List<Vote> getWithRestaurants(LocalDate date) {
        throw new UnsupportedOperationException();
    }
}