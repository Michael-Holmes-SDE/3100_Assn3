//package src;
/*
 * @author Michael Holmes
 */

import java.util.ArrayList;

//import Bpp.getDecimal;

public class PiMultithreaded {

    //System.out.println("Not going into main");
    public static void main(String[] args) {
        TaskQueue queue = new TaskQueue();
        
        // Get randomized list of tasks to add to queue
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i < 1001; i++) {
            digits.add(i);
        }
        java.util.Collections.shuffle(digits);
        System.out.println(digits);

        //for (int i = 0; i < 1000; i++) {
        //    queue.enqueue(Bpp.getDecimal(digits.get(i)));;
        //}
    }


}


// At the end, print "3.XXXX" since we're only doing to the right of the decimal



