package ru.raisaryzbekova.voter.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.raisaryzbekova.voter.model.Restaurant;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(AdminRestaurantsRestController.REST_URL)
public class AdminRestaurantsRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/admin/restaurants";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithResponseEntity(@RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{restaurantId}")
    public Restaurant get(@PathVariable("restaurantId") int id) throws NotFoundException {
        return super.get(id);
    }

    @GetMapping("/{restaurantId}/by-date")
    public Restaurant getWithMeals(@PathVariable("restaurantId") int id, @RequestParam(value = "date", required = false) LocalDate date) {
        return super.getWithDishes(id, date);
    }

    @GetMapping("/by-date-with-dishes")
    public List<Restaurant> getListWithDishes(@RequestParam(value = "date", required = false) LocalDate date) {
        return super.getListWithDishes(date);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("restaurantId") int id) throws NotFoundException {
        super.update(restaurant, id);
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurantId") int id) throws NotFoundException {
        super.delete(id);
    }
}