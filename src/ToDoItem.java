/**
 * This class models a task ToDoItem containing - a date,a description and an
 * importance level
 *
 * @author shreya.jaiswal
 *
 */
public class ToDoItem{
    /** This task's date */
    private Date date;
    /** This task's description */
    private String description;
    /** This task's importance:HIGH,MEDIUM, or LOW */
    private Importance importanceLevel;

    // constructor
    public ToDoItem(Date date, String description, Importance importanceLevel) {
        this.date = date;
        this.description = description;
        this.importanceLevel = importanceLevel;
    }

    /**
     * Constructs a new ToDoItem from a CSV file's input string
     * <p>
     * Throws illegal argument exception if date or importance are invalid
     * 
     * @param toDo : String from CSV file
     * @return a new ToDoItem
     */
    public static ToDoItem buildFromCSV(String toDo) {
        int count = 0;
        int fromIndex = 0;
        while ((fromIndex = toDo.indexOf(",", fromIndex)) != -1) {
            count = count + 1;
            fromIndex = fromIndex + 1;
        }

        if (count != 2) {
           
            throw new IllegalArgumentException();
        }
        String[] toDoArray = toDo.split(",");
        Date date = Date.fromYYYYMMDDString(toDoArray[0]);
        String description = toDoArray[1];

        Importance importanceLevel = null;
        if (toDoArray[2].equals("HIGH")) {
            importanceLevel = Importance.HIGH;
        } else if (toDoArray[2].equals("MEDIUM")) {
            importanceLevel = Importance.MEDIUM;
        } else if (toDoArray[2].equals("LOW")) {
            importanceLevel = Importance.LOW;
        } else {
           
            throw new IllegalArgumentException();
        }

        return new ToDoItem(date, description, importanceLevel);

    }

    /**
     * Returns a CSV representation of input string
     * 
     * @return String of CSV format: date,description,importance
     */
    public String getAsCSV() {
        return this.date.getAsYYYYMMDD() + "," + this.description + "," + this.importanceLevel;
    }

    /**
     * Getter for ToDoItem's date
     * 
     * @return this task's date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Getter for ToDoItem's importance
     * 
     * @return this task's importance
     */
    public Importance getImportanceLevel() {
        return this.importanceLevel;
    }

    /**
     * Getter for ToDoItem's description
     * 
     * @return this task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns string representation of the task
     */
    public String toString() {
        return "*" + "\n" + " Date: " + this.date.toString() + "\n" + " Importance:" + this.importanceLevel + "\n"
                +" "+ this.description + "\n";

    }

}
