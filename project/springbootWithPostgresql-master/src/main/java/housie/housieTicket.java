package housie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import logs.LogController; 

public class housieTicket {
	
	public static void main(String[] args) {
        
		try {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number of rows..");
        int row= sc.nextInt();
        
        System.out.println("Please enter number of columns..");
        int coloumn=sc.nextInt();
        
        int[][] ticket = generateTicket(row,coloumn);
        
        printTicket(ticket,row,coloumn);
		}catch(Exception e) {
			LogController.logException(e);
			
		}
    }

    // Generates a Housie ticket
    public static int[][] generateTicket(int row, int col) {
        int[][] ticket = new int[row][col];
        Random random = new Random();

        // Generate numbers for each column
        for (int i = 0; i < col; i++) {
            List<Integer> numbers = new ArrayList<>();

            // Generate unique random numbers for the column
            while (numbers.size() < row) {
                int num = random.nextInt(10) + (i * 10) + 1; // Generate a number between 1 and 10 (inclusive) for each column
                if (!numbers.contains(num)) {
                    numbers.add(num);
                }
            }

            // Randomly assign the numbers to the column
            Collections.shuffle(numbers);
            for (int j = 0; j < row; j++) {
                ticket[j][i] = numbers.get(j);
            }
        }

        return ticket;
    }

    // Prints the Housie ticket
    public static void printTicket(int[][] ticket,int row,int col) {
        System.out.println("Housie Ticket:");
        System.out.println("-------------------");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(ticket[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("-------------------");
    }


}
