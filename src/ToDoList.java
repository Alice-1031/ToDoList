import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class models a to do list.
 * 
 * @author shreya.jaiswal
 *
 */
public class ToDoList {
    /** array list to hold the tasks */
    private ArrayList<ToDoItem> theTasks;
    /** instance variable stores user name for recording in file */
    private String username;

    // constructor
    public ToDoList(String username) {
        this.username = username;
        // empty toDoList
        theTasks = new ArrayList<ToDoItem>();

    }

    /**
     * Builds a ToDoList object from an existing file.
     * <p>
     * Throws illegal argument exception if file does not exist or file is corrupt.
     * 
     * @param userName :String userName input by user
     * @return saved ToDoList
     */
    public static ToDoList buildFromUsername(String userName) {

        List<String> theLines;
        try {
            theLines = Files.readAllLines(Paths.get(userName + ".txt"), StandardCharsets.UTF_8);
            ToDoList toDoList = new ToDoList(userName);
            for (int i = 0; i < theLines.size(); i++) {
                try {
                    ToDoItem toDo = ToDoItem.buildFromCSV(theLines.get(i));
                    toDoList.addTask(toDo);
                } catch (IndexOutOfBoundsException e) {
                  
                    throw new IllegalArgumentException();
                }
            }
            return toDoList;
        } catch (IOException e) {
           
            throw new IllegalArgumentException();
        }

    }

    /**
     * Adds a new task to the ToDoList object.
     * 
     * @param item :ToDoItem to be added to ToDoList.
     */
    public void addTask(ToDoItem item) {
        theTasks.add(item);
    }

    /**
     * Returns a string representation of ToDoList sorted according to Dates
     * <p>
     * Throws illegal argument exception if list is empty.
     * 
     * @return String representing a ToDoList sorted by dates
     */
    public String getAsDateSortedString() {
        if (theTasks.size() < 1) {
            throw new IllegalArgumentException();
        }
        
        Comparator<ToDoItem> comparisonBox = new DateComparator();
        Collections.sort(theTasks,comparisonBox);
        
        String result = "";
        for (int i = 0; i < theTasks.size(); i++) {

            result = result +"\n"+ theTasks.get(i).toString();
        }
        return result+"\n";

    }

    /**
     * Returns a string representation of ToDoList sorted according to Dates
     * <p>
     * Throws illegal argument exception if list is empty.
     * 
     * @return String representing a ToDoList sorted by dates
     */
    public String getAsImportanceSortedString() {

        if (theTasks.size() < 1) {
            throw new IllegalArgumentException();
        }
        Comparator<ToDoItem> comparisonBox = new ImportanceLevelComparator();
        Collections.sort(theTasks, comparisonBox);
        String result = "";
        for (int i = 0; i < theTasks.size(); i++) {

            result = result +"\n"+ theTasks.get(i).toString();

        }
        return result+"\n";
    }

    /**
     * Saves the updated ToDoList to a new file, or an existing file
     * <p>
     * of .txt format as specified by user.
     */
    public void finalize() {

        ArrayList<String> theTasksAsCSV = new ArrayList<String>();
        for (int i = 0; i < theTasks.size(); i++) {
            theTasksAsCSV.add(theTasks.get(i).getAsCSV());
        }

        String toWrite = "";
        for (String toDo : theTasksAsCSV) {
            toWrite = toWrite + toDo + "\n";
        }
        try {
            Files.write(Paths.get(this.username + ".txt"), toWrite.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new InputMismatchException();
        }

    }

}
