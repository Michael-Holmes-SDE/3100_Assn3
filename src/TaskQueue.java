import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/*
 * @author Michael Holmes
 */

public class TaskQueue {
    private final LinkedList<Integer> queue;

    public TaskQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(Integer task) {
        queue.addLast(task);
        notifyAll(); // Notify all threads
    }

    public synchronized Integer dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.removeFirst();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void populateQueue(int numDigits) {
        ArrayList<Integer> tasks = new ArrayList<>();
        for (int i = 1; i <= numDigits; i++) {
            tasks.add(i);
        }
        Collections.shuffle(tasks);
        queue.addAll(tasks);
        notifyAll();
    }
}
