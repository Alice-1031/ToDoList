import java.util.Comparator;

/**
 * Comparator class for tasks by their importance
 * 
 * @author shreya.jaiswal
 *
 */
public class ImportanceLevelComparator implements Comparator<ToDoItem> {

    /**
     * Compares two importance levels of the specified two ToDoItems
     */
    @Override
    public int compare(ToDoItem arg0, ToDoItem arg1) {

        Importance importance0 = arg0.getImportanceLevel();
        Importance importance1 = arg1.getImportanceLevel();

        if (importance1.equals(Importance.HIGH) && !importance0.equals(Importance.HIGH)) {
            return 1;
        } else if (importance1.equals(Importance.MEDIUM) && importance0.equals(Importance.LOW)) {
            return 1;
        } else if (importance1.equals(Importance.LOW) && !importance0.equals(Importance.LOW)) {
            return -1;
        } else if (importance1.equals(Importance.MEDIUM) && importance0.equals(Importance.HIGH)) {
            return -1;
        }

        return 0;
    }

}
