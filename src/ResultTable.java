import java.util.HashMap;

/*
 * @author Michael Holmes
 */

public class ResultTable {
    private final HashMap<Integer, Integer> table;

    public ResultTable() {
        this.table = new HashMap<>();
    }

    public synchronized void put(int digit, int piDigit) {
        table.put(digit, piDigit);
    }

    public synchronized int get(int digit) {
        return table.get(digit);
    }
}

