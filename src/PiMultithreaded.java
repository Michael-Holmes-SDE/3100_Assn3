/*
 * @author Michael Holmes
 */


public class PiMultithreaded {
    private static int digitsComputed = 0; // Global counter for total digits computed by all threads combined
    private static final Object lock = new Object(); // Shared lock object for synchronization of all worker threads

    static class WorkerThread extends Thread {
        private final TaskQueue taskQueue;
        private final ResultTable resultTable;

        public WorkerThread(TaskQueue taskQueue, ResultTable resultTable) {
            this.taskQueue = taskQueue;
            this.resultTable = resultTable;
        }

        public void run() {
            while (!taskQueue.isEmpty()) {
                try {
                    int digit = taskQueue.dequeue();
		            Bpp bpp = new Bpp(); // Class to get the nth decimal digit of pi
                    int piDigit = bpp.getDecimal(digit);
                    resultTable.put(digit, piDigit);

                    synchronized (lock) { // Ensure only one thread can access at a time
                        digitsComputed++;

                        // Print out "." for every 10 digits finished
                        if (digitsComputed % 10 == 0) {
                            System.out.print(".");
                            System.out.flush();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int numCores = Runtime.getRuntime().availableProcessors();
        int digitsToCompute = 1000; // For easily changing pi digits to compute

        TaskQueue taskQueue = new TaskQueue();
        taskQueue.populateQueue(digitsToCompute); // The queue is randomized in this method
        
        ResultTable resultTable = new ResultTable();

        // Create and start the worker threads, one thread for each core on the system
        WorkerThread[] threads = new WorkerThread[numCores];
        for (int i = 0; i < numCores; i++) {
            threads[i] = new WorkerThread(taskQueue, resultTable);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < numCores; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the computed value of Pi
        System.out.println("\nComputed value of Pi:");
        System.out.print("3.");
        for (int i = 1; i < digitsToCompute+1; i++) {
            int piDigit = resultTable.get(i);
            System.out.print(piDigit);
        }

        // Calculate and print the total wall clock time
        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal wall clock time: " + (endTime - startTime) + " ms");
    }
}

