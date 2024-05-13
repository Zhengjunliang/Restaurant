package com.restaurant.command;
import javafx.event.Event;

public class CommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(Event event) throws Exception {
        if (command != null) {
            command.execute(event);
        }
    }
}
