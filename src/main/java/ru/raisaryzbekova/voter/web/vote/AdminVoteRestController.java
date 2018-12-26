package ru.raisaryzbekova.voter.web.vote;

import org.springframework.web.bind.annotation.*;
import ru.raisaryzbekova.voter.model.Vote;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(AdminVoteRestController.REST_URL)
public class AdminVoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/admin/votes";

    @Override
    @GetMapping(value = "/votes-with-restaurants-by-date")
    public List<Vote> getWithRestaurants(@RequestParam(value = "date", required = false) LocalDate date) {
        return super.getWithRestaurants(date);
    }
}