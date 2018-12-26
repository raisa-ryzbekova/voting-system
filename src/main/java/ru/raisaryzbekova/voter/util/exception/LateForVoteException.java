package ru.raisaryzbekova.voter.util.exception;

public class LateForVoteException extends RuntimeException {

    public LateForVoteException(String message) {
        super(message);
    }
}