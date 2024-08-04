package com.restaurant.command;
import javafx.event.Event;


public interface ICommand {
    void execute(Event event) throws Exception;

}

