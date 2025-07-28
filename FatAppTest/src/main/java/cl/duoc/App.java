package cl.duoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class App {

  static Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    logger.debug("Starting the application...");

    System.out.println("Enter your name: ");
    logger.debug("Prompting user for name input");

    String nombre = scan.nextLine();
    logger.debug("User input received: {}", nombre);

    System.out.printf("Hello, %s!\n", nombre);
    logger.debug("Exiting application...");
  }
}