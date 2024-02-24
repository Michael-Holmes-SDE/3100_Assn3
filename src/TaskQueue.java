//package src;
import java.util.LinkedList;

public class TaskQueue {
    private final LinkedList<ComputeTask> queue;

    public TaskQueue() {
        this.queue = new LinkedList<>();
    }

    // Method to add a task to the queue
    public synchronized void enqueue(ComputeTask task) {
        queue.addLast(task);
        // Notify any waiting threads that an item has been added to the queue
        notifyAll();
    }

    // Method to remove and return the first task from the queue
    public synchronized ComputeTask dequeue() throws InterruptedException {
        // Wait while the queue is empty
        while (queue.isEmpty()) {
            wait();
        }
        return queue.removeFirst();
    }

    // Method to check if the queue is empty
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    // Class representing a compute task
    static class ComputeTask {
        // Task attributes and methods can be added here
    }
}

