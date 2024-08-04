package com.restaurant.command;
import javafx.event.Event;

public class CommandInvoker {
    private ICommand ICommand;

    public void setCommand(ICommand ICommand) {
        this.ICommand = ICommand;
    }

    public void executeCommand(Event event) throws Exception {
        if (ICommand != null) {
            ICommand.execute(event);
        }
    }
}
