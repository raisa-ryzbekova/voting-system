package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.Restaurant;

import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 3;
    public static final int RESTAURANT2_ID = START_SEQ + 4;
    public static final int RESTAURANT3_ID = START_SEQ + 5;

    public static final List<Integer> RESTAURANTS_ID = List.of(RESTAURANT1_ID, RESTAURANT3_ID);

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "Ресторан 1", "222222", "Улица, д.1", of(2018, Month.NOVEMBER, 7));
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT2_ID, "Ресторан 2", "222223", "Улица, д.2", of(2018, Month.NOVEMBER, 6));
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT3_ID, "Ресторан 3", "222224", "Улица, д.3", of(2018, Month.NOVEMBER, 7));

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "votes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "votes").isEqualTo(expected);
    }
}
