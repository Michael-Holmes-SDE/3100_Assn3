//package src;
import java.util.HashMap;

public class ResultTable {
    private final HashMap<Integer, Integer> table;

    public ResultTable() {
        this.table = new HashMap<>();
    }

    // Method to add a result to the table
    public synchronized void addResult(int digitPosition, int piDigit) {
        table.put(digitPosition, piDigit);
    }

    // Method to get a result from the table
    public synchronized Integer getResult(int digitPosition) {
        return table.get(digitPosition);
    }
}
