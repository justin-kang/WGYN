import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.lang.Integer;
import java.io.IOException;

public class Number {

    private static final int NUM_INPUTS = 4;

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        // Get the four input numbers and check for validity
        validate_inputs(nums, args);
        Collections.sort(nums);
        // Store the results of combinations of numbers in an index
        Index index = null;
        try { index = Index.load("wgyn_index.db"); }
        catch (IOException | ClassNotFoundException e) { index = new Index(); }
        if (index == null) index = new Index();
        // Do the calculations here
        Calculate calc = new Calculate(nums, index);
        calc.arithmetic();
        // Detect which of the 100 numbers are possible
        Set<Double> set = index.get(nums);
        for (double i = 1; i <= 100; i++) {
            if (set.contains(i)) System.out.println((int)i + ": Possible");
            else System.out.println((int)i + ": Not Possible");
        }
    }

    private static void validate_inputs(List<Integer> nums, String[] args) {
        // Make sure there are four numbers for inputs
        if (args.length < NUM_INPUTS) invalid_inputs();
        for (int i = 0; i < NUM_INPUTS; i++) {
            // Makes sure inputs are numbers
            try { nums.add(Integer.parseInt(args[i])); }
            catch (NumberFormatException e) { invalid_string(args[i]); }
            // Makes sure numbers are single digit and non-negative
            if (invalid_input(nums.get(i))) invalid_number();
        }
    }
    
    private static boolean invalid_input(int num) {
        return num < 0 || num > 9;
    }

    private static void invalid_inputs() {
        System.out.println("Not enough inputs. Try again");
        System.exit(-1);
    }

    private static void invalid_string(String s) {
        System.out.println(s + " is not a number. Try again");
        System.exit(-1);
    }

    private static void invalid_number() {
        System.out.println("Number must be between 0 and 9. Try again.");
        System.exit(-1);
    }
}
