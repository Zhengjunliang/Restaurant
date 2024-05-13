package com.restaurant.command;
import javafx.event.Event;


public interface Command {
    void execute(Event event) throws Exception;

}

