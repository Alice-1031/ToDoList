import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver class for the ToDoList application.
 * 
 * @author shreya.jaiswal
 *
 */
public class ToDoDriver {
    /** Scanner class variable takes user Input for the entire program */
    public static Scanner userInput;

    /**
     * Main method interacts with user and drives the program.
     * 
     * @param args
     */
    public static void main(String[] args) {
        userInput = new Scanner(System.in);
        System.out.print("Welcome to the TodoList application.\n Enter your username: ");

        String userName = userInput.nextLine();
        ToDoList toDo = new ToDoList(userName);
        boolean condition = true;
        try {
            toDo = ToDoList.buildFromUsername(userName);
        } catch (IllegalArgumentException e) {
            System.out.print(
                    "An error occurred trying to read the file for your username.\n" + "Create new Todo List? (Y/N)");
            // clear input buffer
            userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("N") || !input.equals("Y")) {
                return;
            }

        }
        while (condition) {

            System.out.println("Options: \n" + "1) Show tasks sorted by date\n" + "2) Show tasks sorted by importance\n"
                    + "3) Add new task\n" + "4) Save and exit");
            // clear input buffer
            userInput = new Scanner(System.in);
            String inputAsStirng = userInput.nextLine();
            try {
                int input = Integer.parseInt(inputAsStirng);
                System.out.println("Your choice: " + input);

                switch (input) {
                case 1:

                    try {
                        System.out.print(toDo.getAsDateSortedString() + "\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("No tasks in list");
                    }
                    break;

                case 2:

                    try {
                        System.out.println(toDo.getAsImportanceSortedString() + "\n");

                    } catch (IllegalArgumentException e) {
                        System.out.println("No tasks in list");
                    }
                    break;

                case 3: {
                    System.out.print("Enter a date (YYYY-MM-DD): ");
                    // clear input buffer
                    userInput = new Scanner(System.in);
                    String dateAsString = userInput.nextLine();
                    Date date;

                    if (!dateAsString.matches("^\\d{4}\\-\\d{2}\\-\\d{2}")) {
                        System.out.println("Dates must be entered in YYYY-MM-DD format");
                        break;
                    } // regex to allow YYYY-MM-DD input format only

                    try {
                        // illegal argument exception for invalid date
                        Date.fromYYYYMMDDDashString(dateAsString);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dates must be entered in YYYY-MM-DD format");
                        break;
                    }
                    date = Date.fromYYYYMMDDDashString(dateAsString);

                    System.out.print("Enter an importance (HIGH, MEDIUM, LOW): ");
                    // clear input buffer
                    userInput = new Scanner(System.in);
                    String importance = userInput.nextLine();
                    Importance importanceLevel;
                    if (importance.equals("HIGH")) {
                        importanceLevel = Importance.HIGH;
                    } else if (importance.equals("MEDIUM")) {
                        importanceLevel = Importance.MEDIUM;
                    } else if (importance.equals("LOW")) {
                        importanceLevel = Importance.LOW;
                    } else {
                        System.out.println("Bad importance choice");
                        break;
                    }

                    System.out.print("Enter a short description (no commas): ");
                    // clear input buffer
                    userInput = new Scanner(System.in);
                    String description = userInput.nextLine();

                    if (description.contains(",")) {
                        System.out.println("No commas allowed");
                        break;
                    }
                    // create new item and append to list
                    ToDoItem item = new ToDoItem(date, description, importanceLevel);
                    toDo.addTask(item);
                    break;
                }

                case 4: {
                    toDo.finalize();
                    // save and exit
                    condition = false;
                    break;

                } // 4
                default: // catch and warn if invalid input
                {
                    System.out.println("invalid choice");
                    continue;
                }
                }// switch
            } catch (InputMismatchException e) {
                System.out.println("invalid choice");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("invalid choice");
                continue;
            }

        } // while

    }// main

}
