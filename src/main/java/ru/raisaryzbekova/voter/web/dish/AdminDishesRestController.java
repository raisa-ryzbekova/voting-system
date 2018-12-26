package ru.raisaryzbekova.voter.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Dish;
import ru.raisaryzbekova.voter.service.DishService;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.assureIdConsistent;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(AdminDishesRestController.REST_URL)
public class AdminDishesRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService dishService;

    static final String REST_URL = "/rest/admin/restaurants";

    @PostMapping(value = "{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        log.info("create {} in restaurant id={}", dish, restaurantId);
        checkNew(dish);
        Dish created = dishService.create(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{restaurantId}/dishes/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) throws NotFoundException {
        log.info("get {} in restaurant id={}", id, restaurantId);
        return dishService.get(id, restaurantId);
    }

    @GetMapping("/{restaurantId}/dishes-by-date")
    public List<Dish> getByDate(@PathVariable("restaurantId") int restaurantId, @RequestParam(value = "date", required = false) LocalDate date) {
        log.info("getByDate in restaurant id={} date {}", restaurantId, date);
        return dishService.getByDate(restaurantId, date);
    }

    @GetMapping("/{restaurantId}/dishes")
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        log.info("getAll in restaurant id={}");
        return dishService.getAll(restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) throws NotFoundException {
        log.info("update {} in restaurant id={}", dish, restaurantId);
        assureIdConsistent(dish, id);
        dishService.update(dish, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) throws NotFoundException {
        log.info("delete {}", id);
        dishService.delete(id, restaurantId);
    }
}