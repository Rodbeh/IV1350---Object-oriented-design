package se.kth.iv1350.amazingpointofsale.view;

import se.kth.iv1350.amazingpointofsale.controller.Controller;
import se.kth.iv1350.amazingpointofsale.integration.DatabaseConnectionFailureException;
import se.kth.iv1350.amazingpointofsale.integration.ItemIdentifierInvalidException;
import se.kth.iv1350.amazingpointofsale.model.DefaultItemFactory;
import se.kth.iv1350.amazingpointofsale.model.ItemFactory;
import se.kth.iv1350.amazingpointofsale.util.ConsoleLogger;
import se.kth.iv1350.amazingpointofsale.util.FileLogger;
import se.kth.iv1350.amazingpointofsale.util.TotalRevenueFileOutput;


/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller. 
 */
public class View {
    private final Controller contr;
    private final ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();
    private final FileLogger fileLogger = FileLogger.getInstance();
    private final ConsoleLogger consoleLogger = new ConsoleLogger();
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers. 
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller. 
     * 
     * @throws ItemIdentifierInvalidException when the item identifier is invalid (checked)
     * @throws DatabaseConnectionFailureException when the database cannot be reached (unchecked)
     */
    public void runFakeExecution() throws ItemIdentifierInvalidException, DatabaseConnectionFailureException {
        startSale();
        //scanItem();
        scanItemException();
        //addDiscount();
        endSale();
    }
    
    private void startSale() {
        ItemFactory itemFactory = new DefaultItemFactory();
        contr.startSale(itemFactory);
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\t\t          Ny försäljning påbörjad");
        System.out.println("-------------------------------------------------------------------------");
    }

    private void scanItem() throws ItemIdentifierInvalidException, DatabaseConnectionFailureException {
        System.out.println(contr.scanItem("7310865000361", 1));
        System.out.println(contr.scanItem("7318690066903", 3));
        System.out.println(contr.scanItem("7311041060216", 1));
        System.out.println(contr.scanItem("8715800002315", 1));
        System.out.println(contr.scanItem("7310340002279", 2));
        System.out.println(contr.scanItem("7310865005168", 3));    
    }
    
    private void scanItemException() {
        try {
            System.out.println(contr.scanItem("7310865000361", 1));
            System.out.println(contr.scanItem("7318690066903", 3));
            System.out.println(contr.scanItem("7311041060216", 1));
            System.out.println(contr.scanItem("8715800002315", 1));
            System.out.println(contr.scanItem("7310340002279", 2));
            System.out.println(contr.scanItem("404", 3));
        } catch(DatabaseConnectionFailureException | ItemIdentifierInvalidException exception) {
            errorMessageHandler.showErrorMessage(exception.getMessage());
            fileLogger.log(exception);
            //consoleLogger.log(exception);
        }
    }
        
    /*private void addDiscount() {
        boolean discountAdded = contr.discountAppliedIfCustomerMember("8002151234");
        if(discountAdded) {
            System.out.println("Kund är medlem: rabatt har lagts till.");
        } else {
            System.out.println("Kund är ej medlem: Rabatt kunde inte läggas till.");
        }
    }*/
    
    private void endSale() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\t\t          Försäljning är avslutad");
        System.out.println("-------------------------------------------------------------------------");
        try {
            System.out.printf("Totalt: %-1.2fkr%n", contr.getTotal());
            System.out.println("Mottaget kontant: " + 500 + "kr");
            System.out.println("-------------------------------------------------------------------------");
            contr.endSale(500);
        } catch(IllegalStateException exception) {
            errorMessageHandler.showErrorMessage("Försäljningen har inte påbörjats ännu.");
        }
    }
  
}

