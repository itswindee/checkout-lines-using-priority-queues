// Name:        Emily Zhu
// Class:       CS 3305/W04/
// Term:        Fall 2023
// Instructor:  Carla McManus
// Assignment:  05-Part-2-Shoppers

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int numCheckoutLines = 5;
        Queue<Integer>[] checkoutLines = new Queue[numCheckoutLines];
        Random random = new Random();

        //initialize checkout lines
        for (int i = 0; i < numCheckoutLines; i++) {
            checkoutLines[i] = new LinkedList<>();
        }

        int numCustomers = 50; //

        //generate and add customers to queues
        for (int i = 1; i < 6; i++) {
            int shortestLineIndex = findShortestLine(checkoutLines);
            checkoutLines[shortestLineIndex].offer(i);
            System.out.println("Customer " + i + " is entering Queue " + (shortestLineIndex + 1));
        }
        System.out.println();
//simulate checkout process
        for (int time = 6; time < numCustomers; time++) {
            int chance = random.nextInt(3);
            if(chance == 0) { //33% chance to remove customer
                int randLine = random.nextInt(5);
                if (!checkoutLines[randLine].isEmpty()) {
                    printLines(checkoutLines);
                    int customer = checkoutLines[randLine].poll();
                    System.out.println("Customer " + customer + " is checking out from Queue " + (randLine + 1));
                }
            }
            int chanceToAddCustomer = random.nextInt(2);
            if(chanceToAddCustomer == 0) {
                printLines(checkoutLines);
                int shortestLineIndex = findShortestLine(checkoutLines);
                checkoutLines[shortestLineIndex].offer(time);
                System.out.println("Customer " + time + " is entering Queue " + (shortestLineIndex + 1));
            }
        }
    }
    //find the shortest checkout line
    private static int findShortestLine(Queue<Integer>[] checkoutLines) {
        int shortestLineIndex = 0;
        int minLength = checkoutLines[0].size();

        for (int i = 1; i < checkoutLines.length; i++) {
            int length = checkoutLines[i].size();
            if (length < minLength) {
                minLength = length;
                shortestLineIndex = i;
            }
        }
        return shortestLineIndex;
    }

    public static void printLines(Queue<Integer>[] checkoutLines) {
        for(int i = 0; i < 5; i++) {
            System.out.println("Queue " + (i + 1) + ": Total customer: " + checkoutLines[i].size());
        }
        System.out.println();
    }

    //check if all checkout lines are empty
    private static boolean allQueuesEmpty(Queue<Integer>[] checkoutLines) {
        for (Queue<Integer> line : checkoutLines) {
            if (!line.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}