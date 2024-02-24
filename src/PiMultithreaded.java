/*
 * @author Michael Holmes
 */

// GETTING THE WRONG VALUES FOR PI

public class PiMultithreaded {

    static class WorkerThread extends Thread {
        private final TaskQueue taskQueue;
        private final ResultTable resultTable;

        public WorkerThread(TaskQueue taskQueue, ResultTable resultTable) {
            this.taskQueue = taskQueue;
            this.resultTable = resultTable;
        }

        //@Override
        public void run() {
            while (!taskQueue.isEmpty()) {
                try {
                    int digit = taskQueue.dequeue();
		            Bpp bpp = new Bpp(); // MAKE EASIER TO READ
                    int piDigit = bpp.getDecimal(digit);
                    resultTable.put(digit, piDigit);

                    // Print out "." for every 10 digits finished
                    if (digit % 10 == 0) {
                        System.out.print(".");
                        System.out.flush();
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

        TaskQueue taskQueue = new TaskQueue();
        taskQueue.populateQueue(1000);
        
        ResultTable resultTable = new ResultTable();

        // Create and start worker threads
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
        System.out.println("\nComputed value of Pi:"); // ISN'T CORRECT YET, TEST
        System.out.print("3.");
        for (int i = 1; i < 1001; i++) {
            int piDigit = resultTable.get(i);
            System.out.print(piDigit);
        }

        // Calculate and print total wall clock time
        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal wall clock time: " + (endTime - startTime) + " ms");
    }
}

