import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

public class Number {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(4);
        // Get the four input numbers and check for validity
        validate_inputs(nums, args);
        Collections.sort(nums);
        // Store the results of combinations of numbers in an index
        Index index = new Index();
        // Do the calculations here

        // Actually detect which of the 100 numbers are possible
        for (int i = 1; i <= 100; i++) {
            if (index.get(nums).contains(i))
                System.out.println(i + ": Possible");
            else System.out.println(i + ": Not Possible");
        }
    }

    private static void validate_inputs(List<Integer> nums, String[] args) {
        // Make sure there are four numbers for inputs
        if (args.length < nums.size()) invalid_inputs();
        for (int i = 0; i < nums.size(); i++) {
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
