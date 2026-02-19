import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;
import java.util.Arrays;


public class Main {

    public static void hasTransmissionError(byte[] transmit, byte[] receive) {

        StringBuilder binaryStringTransmit = new StringBuilder(transmit.length);
        StringBuilder binaryStringReceive = new StringBuilder(receive.length);
        StringBuilder powerString = new StringBuilder(transmit.length);
        StringBuilder kCheckValueTransmit = new StringBuilder();
        StringBuilder kCheckValueReceive = new StringBuilder();

        for (byte b : transmit) {
            binaryStringTransmit.append((char) b - 48);
        }

        for (byte b : receive) {
            binaryStringReceive.append((char) b - 48);
        }




        System.out.println("Transmitted File content: " + binaryStringTransmit);
        System.out.println("Total number of bytes read: " + transmit.length);

        System.out.println("\n");


        int N = transmit.length;
        int k = 0;

        while (Math.pow(2, k) <= N + 1){
            k++;
        }

        int M = N - k;
        System.out.println("M data bits are " + M);
        System.out.println("K check bits are " + k);
        System.out.println("\n");


        System.out.println(N);
        System.out.println(k);




        for (int i = 0; i < k; i++) {
            double power = Math.pow(2, i) - 1;
            kCheckValueTransmit.append((char) transmit[transmit.length - 1 - i] -48);
            kCheckValueTransmit.append(" ");
            powerString.append((int)power);
            powerString.append(" ");
        }



        System.out.println("The location of the k check bits are: " + powerString);

        System.out.println("The k check bit values are: " + kCheckValueTransmit);


        System.out.println("\n");
        System.out.println("Received file content: " + binaryStringReceive);
        System.out.println("Total number of bytes read: " + receive.length);


        if (transmit.length != receive.length) {
            System.out.println("Files are not the same size!");
            return;
        }


        for (int i = 0; i < k; i++) {
            kCheckValueReceive.append((char) receive[receive.length - 1 - i] -48);
            kCheckValueReceive.append(" ");

        }



        System.out.println("The location of the k check bits are: " + powerString);
        System.out.println("The k check bit values are: " + kCheckValueReceive);

        String transmitTrim = kCheckValueTransmit.toString().trim();
        System.out.println("Transmitted file content: " + transmitTrim);
        int transmitBinary = Integer.parseInt(transmitTrim, 2);
        int receiveBinary = Integer.parseInt(kCheckValueReceive.toString().trim(), 2);


        String syndromeWord = Integer.toBinaryString(transmitBinary ^ receiveBinary);


        System.out.println("The syndrome word is: " + syndromeWord);






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


            hasTransmissionError(original, rec1);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}