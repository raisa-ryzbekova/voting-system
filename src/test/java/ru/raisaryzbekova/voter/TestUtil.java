package ru.raisaryzbekova.voter;

import ru.raisaryzbekova.voter.model.Vote;

import java.time.LocalTime;

public class TestUtil {

    public static void setTimeAfter(Vote vote) {
        vote.setLocalTime(LocalTime.of(11, 0));
    }
}
