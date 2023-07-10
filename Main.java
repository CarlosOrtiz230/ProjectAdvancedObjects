import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String csvFile = "BankUser.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                String identificationNumber = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String dob = data[3];
                String address = data[4];
                String phoneNumber = data[5];
                String checkingAccount = data[6];
                double checkingStartingBalance = Double.parseDouble(data[7]);
                String savingsAccount = data[8];
                double savingsStartingBalance = Double.parseDouble(data[9]);
                String creditAccountNumber = data[10];
                double creditMax = Double.parseDouble(data[11]);
                double creditStartingBalance = Double.parseDouble(data[12]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
