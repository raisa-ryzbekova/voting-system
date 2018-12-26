package ru.raisaryzbekova.voter.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.Vote;
import ru.raisaryzbekova.voter.service.VoteService;

import java.time.LocalDate;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.assureIdConsistent;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;

public abstract class AbstractVoteController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    public Vote create(Vote vote, int userId, int restaurantId) {
        log.info("create {}", vote);
        checkNew(vote);
        return voteService.create(vote, userId, restaurantId);
    }

    public Vote get(int id, int userId) {
        log.info("get {} userId={}", id, userId);
        return voteService.get(id, userId);
    }

    public List<Vote> getWithRestaurants(LocalDate date) {
        log.info("getWithRestaurants by date {}", date);
        return voteService.getWithRestaurants(date);
    }

    public List<Vote> getAll(int userId) {
        log.info("getAll userId={}");
        return voteService.getAll(userId);
    }

    public void update(Vote vote, int id, int userId, int restaurantId) {
        log.info("update {} userId={}", vote, userId);
        assureIdConsistent(vote, id);
        voteService.update(vote, userId, restaurantId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        voteService.delete(id, userId);
    }
}