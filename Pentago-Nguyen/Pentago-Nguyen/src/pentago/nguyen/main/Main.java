package pentago.nguyen.main;

import pentago.nguyen.controller.Controller;
import pentago.nguyen.model.Pentago;
import pentago.nguyen.view.View;

/**
 * This is the main class.
 *
 * @author Michel
 */
public class Main {

    /**
     * This is the main.
     *
     * @param args null.
     */
    public static void main(String[] args) {
        Pentago pentago = new Pentago();
        View view = new View(pentago);
        Controller controller = new Controller(pentago, view);
        try {
            controller.start();
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
