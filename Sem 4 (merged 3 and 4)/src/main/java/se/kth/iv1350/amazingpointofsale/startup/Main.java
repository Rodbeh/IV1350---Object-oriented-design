package se.kth.iv1350.amazingpointofsale.startup;

import se.kth.iv1350.amazingpointofsale.controller.Controller;
import se.kth.iv1350.amazingpointofsale.integration.DatabaseHandler;
import se.kth.iv1350.amazingpointofsale.integration.ItemIdentifierInvalidException;
import se.kth.iv1350.amazingpointofsale.view.View;

/**
 * @author rodbeh
 * 
 */

public class Main {

   public static void main(String[] args) throws ItemIdentifierInvalidException {
        DatabaseHandler handler = new DatabaseHandler();
        Controller contr = new Controller(handler);
        View view = new View(contr);
        view.runFakeExecution();
    }
   
}
