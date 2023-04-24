import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
* This program uses a binary search to find the index of a number.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-04-24
*/

public final class Binary {
    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private Binary() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        // Declare list and variable.
        final ArrayList<String> inputList = new ArrayList<String>();
        String indexStr = "";
        final String sp = " ";

        try {
            // Choose a file to get input from.
            final File input = new File("input.txt");
            final Scanner scanInput = new Scanner(input);

            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("output.txt");

            // Loop to read each line of input file.
            while (scanInput.hasNextLine()) {
                // Add the next line.
                inputList.add(scanInput.nextLine());
            }

            // Create an array with all elements in the input list.
            final String[] inputArr = new String[inputList.size()];
            for (int location = 0; location < inputArr.length; location++) {
                inputArr[location] = inputList.get(location);
            }

            // Loop to send each array to function.
            for (int counter = 0; counter < inputArr.length;
                counter = counter + 2) {
                // Convert inputArr to int.
                final int size = inputArr[counter].split(sp).length;
                final int[] arrInt = new int[size];
                for (int location = 0; location < size; location++) {
                    arrInt[location] = Integer.parseInt(
                        inputArr[counter].split(sp)[location]);
                }

                // Sort array.
                Arrays.sort(arrInt);

                // Get number to search for.
                final int search = Integer.parseInt(inputArr[counter + 1]);
                final int index = search(arrInt, search, 0, arrInt.length - 1);
                System.out.println(index);
                indexStr = indexStr + index + "\n";
            }

            // Write to output file.
            output.write(indexStr);

            // Close writer.
            output.close();

        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
    }

    /**
    * This function uses a binary search.
    *
    * @param listOfNum from file
    * @param searchNum also from file
    * @param start of list
    * @param end of list
    * @return index of number
    */
    public static int search(int[] listOfNum, int searchNum,
        int start, int end) {
        // Base case 1: number not found in list.
        if (start > end) {
            return -1;
        }

        // Set mid point.
        final int mid = (start + end) / 2;

        if (listOfNum[mid] == searchNum) {
            return mid;
        } else if (listOfNum[mid] > searchNum) {
            return search(listOfNum, searchNum, start, mid - 1);
        } else {
            return search(listOfNum, searchNum, mid + 1, end);
        }
    }
}
