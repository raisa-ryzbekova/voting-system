package ru.raisaryzbekova.voter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.raisaryzbekova.voter.model.Vote;
import ru.raisaryzbekova.voter.repository.VoteRepository;
import ru.raisaryzbekova.voter.util.exception.LateForVoteException;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    LocalTime localTime = LocalTime.now();

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        if (localTime.isBefore(LocalTime.of(11, 0, 0))) {
            return repository.save(vote, userId, restaurantId);
        } else {
            throw new LateForVoteException(vote + " is too late, can't be changed");
        }
    }

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Vote> getWithRestaurants(LocalDate date) {
        return repository.getWithRestaurants(date);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        if (localTime.isBefore(LocalTime.of(11, 0, 0))) {
            checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
        } else {
            throw new LateForVoteException(vote + " is too late, can't be changed");
        }
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }
}
