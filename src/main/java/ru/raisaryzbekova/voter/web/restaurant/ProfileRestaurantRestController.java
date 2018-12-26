package ru.raisaryzbekova.voter.web.restaurant;

import org.springframework.web.bind.annotation.*;
import ru.raisaryzbekova.voter.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ProfileRestaurantRestController.REST_URL)
public class ProfileRestaurantRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping("/{restaurantId}/by-date")
    public Restaurant getWithDishes(@PathVariable("restaurantId") int id, @RequestParam(value = "date", required = false) LocalDate date) {
        return super.getWithDishes(id, date);
    }

    @GetMapping("/by-date-with-dishes")
    public List<Restaurant> getListWithDishes(@RequestParam(value = "date", required = false) LocalDate date) {
        return super.getListWithDishes(date);
    }
}