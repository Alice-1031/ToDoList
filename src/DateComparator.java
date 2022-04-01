import java.util.Comparator;

/**
 * Comparator class for tasks by their dates
 * 
 * @author shreya.jaiswal
 *
 */
public class DateComparator implements Comparator<ToDoItem> {

    /**
     * Compares two dates of the specified two ToDoItems
     */
    @Override
    public int compare(ToDoItem arg0, ToDoItem arg1) {
        int date0 = arg0.getDate().getAsYYYYMMDD();
        int date1 = arg1.getDate().getAsYYYYMMDD();

        if (date0 < date1) {
            return -1;
        } else if (date0 > date1) {
            return 1;
        }
        return 0;
    }

}
