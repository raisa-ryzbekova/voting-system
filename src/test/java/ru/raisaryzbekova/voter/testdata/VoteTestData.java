package ru.raisaryzbekova.voter.testdata;

import ru.raisaryzbekova.voter.model.Vote;

import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.LocalTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.raisaryzbekova.voter.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final int VOTE1_ID = START_SEQ + 16;
    public static final int VOTE2_ID = START_SEQ + 17;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, of(2018, Month.NOVEMBER, 7), of(10, 0));
    public static final Vote VOTE2 = new Vote(VOTE2_ID, of(2018, Month.NOVEMBER, 7), of(10, 30));
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, of(2018, Month.NOVEMBER, 7), of(10, 40));
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, of(2018, Month.NOVEMBER, 8), of(10, 0));

    public static final List<Vote> ADMIN_VOTES = List.of(VOTE4, VOTE1);

    public static Vote getCreated() {
        return new Vote(of(2018, Month.NOVEMBER, 9), of(10, 15));
    }

    public static Vote getDuplicateCreated() {
        return new Vote(of(2018, Month.NOVEMBER, 7), of(10, 40));
    }

    public static Vote getUpdated() {
        return new Vote(VOTE2_ID, VOTE2.getDate(), of(10, 59));
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "restaurant").isEqualTo(expected);
    }
}
