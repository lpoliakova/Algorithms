package graphs.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumbersList {
    public static List<Integer> readNumbers(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        List<Integer> numbers = new ArrayList<>();
        while (scan.hasNext()) {
            numbers.add(scan.nextInt());
        }
        return numbers;
    }

    public static List<Long> readLongNumbers(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        List<Long> numbers = new ArrayList<>();
        while (scan.hasNext()) {
            numbers.add(scan.nextLong());
        }
        return numbers;
    }
}
