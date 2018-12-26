package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.Dish;

import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 6;

    public static final Dish DISH1 = new Dish(DISH1_ID, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 1 (Ресторан 1)", 500);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 2 (Ресторан 1)", 500);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 3 (Ресторан 1)", 500);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, of(2018, Month.NOVEMBER, 6), "Бизнес-ланч 4 (Ресторан 1)", 500);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 1 (Ресторан 2)", 500);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 2 (Ресторан 2)", 500);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 3 (Ресторан 2)", 500);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 1 (Ресторан 3)", 500);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 2 (Ресторан 3)", 500);
    public static final Dish DISH10 = new Dish(DISH1_ID + 9, of(2018, Month.NOVEMBER, 7), "Бизнес-ланч 3 (Ресторан 3)", 500);

    public static final List<Dish> DISHES = List.of(DISH1, DISH2, DISH3, DISH4);
    public static final List<Dish> DISHES_BY_DATE = List.of(DISH1, DISH2, DISH3);

    public static Dish getCreated() {
        return new Dish(null, of(2018, Month.NOVEMBER, 11), "Созданный бизнес-ланч", 500);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, DISH1.getDate(), "Обновленный бизнес-ланч", 300);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}