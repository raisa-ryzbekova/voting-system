package ru.raisaryzbekova.voter.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Vote;

import java.net.URI;
import java.util.List;

import static ru.raisaryzbekova.voter.web.SecurityUtil.authUserId;


@RestController
@RequestMapping(ProfileVoteRestController.REST_URL)
public class ProfileVoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/profile/votes";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote, @RequestParam(value = "restaurantId", required = false) int restaurantId) {
        Vote created = super.create(vote, authUserId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) {
        return super.get(id, authUserId());
    }

    @GetMapping
    public List<Vote> getAll() {
        return super.getAll(authUserId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id, @RequestParam(value = "restaurantId", required = false) int restaurantId) {
        super.update(vote, id, authUserId(), restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id, authUserId());
    }
}