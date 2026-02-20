import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;
import java.util.Arrays;


public class Main {

    public static void hasTransmissionError(byte[] transmit, byte[] receive) {

        // Transmit string that will be printed out
        StringBuilder binaryStringTransmit = new StringBuilder(transmit.length);

        // Receive string that will be printed out
        StringBuilder binaryStringReceive = new StringBuilder(receive.length);

        // The powers from 0 to k.
        StringBuilder powerString = new StringBuilder(transmit.length);

        // These will get the specific bit in each index from 0 to k
        // Note we subtract 1 from each power in order to index string properly.
        StringBuilder kCheckValueTransmit = new StringBuilder();
        StringBuilder kCheckValueReceive = new StringBuilder();


        // Subtract 48 to get either 1 or 0
        for (byte b : transmit) {
            binaryStringTransmit.append((char) b - 48);
        }


        // Same here. Subtract 48 to get 1 or 0
        for (byte b : receive) {
            binaryStringReceive.append((char) b - 48);
        }



        // Length and Transit binary string
        System.out.println("Transmitted File content: " + binaryStringTransmit);
        System.out.println("Total number of bytes read: " + transmit.length);

        System.out.println("\n");



        // Length of the binary string
        int N = transmit.length;

        // Initialize to 0
        int k = 0;


        // While 2 ^ k is less than N + 1, we keep increasing k
        while (Math.pow(2, k) <= N + 1){
            k++;
        }

        // Find M by getting the remaining bits
        int M = N - k;

        // Printing out data and check bits
        System.out.println("M data bits are " + M);
        System.out.println("K check bits are " + k);
        System.out.println("\n");



        // We will get both the powers from 0 to k and the positions in the binary string
        for (int i = 0; i < k; i++) {
            // Calculate power, subtract one to get the correct index in the transmit string
            double power = Math.pow(2, i) - 1;

            // Calculate position by offsets, starting at the end of the string
            // The offset will simply be the power
            // Subtract 48 to get either 1 or 0
            kCheckValueTransmit.append((char) transmit[transmit.length - 1 - (int)power] - 48);

            // Append space to match print outputs
            kCheckValueTransmit.append(" ");

            // Append the power to the power string. Note we must convert to int since power is a double
            powerString.append((int)power);
            powerString.append(" ");
        }


        // Print the k check bits and their values
        System.out.println("The location of the k check bits are: " + powerString);

        System.out.println("The k check bit values are: " + kCheckValueTransmit);


        System.out.println("\n");
        System.out.println("Received file content: " + binaryStringReceive);
        System.out.println("Total number of bytes read: " + receive.length);
        System.out.println("\n");

        // Check if transmit and receive are the same length. If not, exit
        if (transmit.length != receive.length) {
            System.out.println("Files are not the same size!");
            return;
        }


        // Could have done this about in the other loop, but doing it here verifies the receive file is valid
        for (int i = 0; i < k; i++) {
            double power = Math.pow(2, i) - 1;
            kCheckValueReceive.append((char) receive[receive.length - 1 - (int)power] -48);
            kCheckValueReceive.append(" ");
        }


        // Print out check bits and the k check bit values
        System.out.println("The location of the k check bits are: " + powerString);
        System.out.println("The k check bit values are: " + kCheckValueReceive);
        System.out.println("\n");

        // Convert StringBuilder to actual string and remove all spaces in both string
        String transmitTrim = kCheckValueTransmit.toString().trim().replace(" ", "");
        String receiveTrim = kCheckValueReceive.toString().trim().replace(" ", "");

        // Convert these to actual integers to get their differences
        int transmitBinary = Integer.parseInt(transmitTrim, 2);
        int receiveBinary = Integer.parseInt(receiveTrim, 2);


        // xor operation to get difference.
        String syndromeWord = Integer.toBinaryString(transmitBinary ^ receiveBinary);

        // Reverse string
        String syndromeReversed = new StringBuilder(syndromeWord).reverse().toString();


        System.out.println("The syndrome word is: " + syndromeReversed);

        // Convert syndrome word to decimal
        int decimal =  Integer.parseInt(syndromeReversed, 2);
        System.out.println("The location of the error bit in the received data is: " + decimal);




    }




    public static void main(String[] args) {
        try {
            // The "Golden" original file
            byte[] original = Files.readAllBytes(Paths.get("transmitfile.bin"));

            // The four different received versions
            byte[] rec1 = Files.readAllBytes(Paths.get("receivefile1.bin"));
            byte[] rec2 = Files.readAllBytes(Paths.get("receivefile2.bin"));
            byte[] rec3 = Files.readAllBytes(Paths.get("receivefile3.bin"));
            byte[] rec4 = Files.readAllBytes(Paths.get("receivefile4.bin"));

            // Function calls

            /*
                 hasTransmissionError(original, rec1);
                hasTransmissionError(original, rec2);
                hasTransmissionError(original, rec3);
                hasTransmissionError(original, rec4);
             */



        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}