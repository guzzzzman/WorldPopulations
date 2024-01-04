// Jose Guzman
// December 15, 2023
// File Name: WorldPopulations.java
// To Compile in terminal type: javac WorldPopulations.java
// To Run the program in terminal type: java WorldPopulations
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WorldPopulations {
    public static final int SIZE = 126;

    public static void main(String[] args) {

        int[] populationDensity = new int[SIZE];
        String[] city = new String[SIZE];
        String[] country = new String[SIZE];

        String fileName = "worldPopulations.csv";
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        }

        inputStream.nextLine(); // Skip header
        int index = 0;

        while (inputStream.hasNextLine()) {
            String record = inputStream.nextLine();
            String[] fields = record.split(",");

            try {
                populationDensity[index] = Integer.parseInt(fields[2]);  // Assuming Population Density is in the third column
            } catch (NumberFormatException ex) {
                // Handle non-numeric values, set default (0 in this case)
                populationDensity[index] = 0;
            }

            country[index] = fields[1];
            city[index] = fields[0];
            index++;
        }

        System.out.println(city[0] + " " + country[0] + " " + populationDensity[0]);
        System.out.println(city[index - 1] + " " + country[index - 1] + " " + populationDensity[index - 1]);

        int indexMax = findMax(populationDensity);
        System.out.println(city[indexMax] + " " + country[indexMax] + " " + populationDensity[indexMax]);
        indexMax = findMaxForCountry(populationDensity, country, "United States");  // Replace with the desired country
        System.out.println(city[indexMax] + " " + country[indexMax] + " " + populationDensity[indexMax]);
    }

    public static int findMax(int[] array) {
        int max = array[0];
        int indexMax = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                indexMax = i;
            }
        }

        return indexMax;
    }

    public static int findMaxForCountry(int[] array, String[] countries, String nameOfCountry) {
        int max = array[0];
        int indexMax = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max && countries[i].equalsIgnoreCase(nameOfCountry)) {
                max = array[i];
                indexMax = i;
            }
        }

        return indexMax;
    }
}

/*
33200000 Tokyo/Yokohama 0
1500000 Accra 0
33200000 Tokyo/Yokohama 0
33200000 Tokyo/Yokohama 0
*/