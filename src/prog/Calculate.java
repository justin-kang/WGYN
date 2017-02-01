import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.lang.Math;
import java.io.IOException;

public class Calculate {
    /*
     * Addition
     * Subtraction
     * Multiplication
     * Division
     * Exponentiation
     * Logarithm base b
     * Factorials
     * Concatenation
     * Grouping
     * Save index
     */
     List<Integer> nums;
     List<List<Integer>> lists;
     Index index;

     public Calculate(List<Integer> arr, Index ind) {
        nums = arr;
        lists = new ArrayList<>();
        index = ind;
     }

     private void build_lists() {
        List<Integer> list = new ArrayList<>();
        list.add(nums.get(0));
        list.add(nums.get(1));
        // 0 1
        lists.add(list);
        list.remove(nums.get(1));
        list.add(nums.get(2));
        // 0 2
        lists.add(list);
        list.remove(nums.get(2));
        list.add(nums.get(3));
        // 0 3
        lists.add(list);
        list.remove(nums.get(0));
        list.add(nums.get(1));
        // 1 3
        lists.add(list);
        list.remove(nums.get(3));
        list.add(nums.get(2));
        // 1 2
        lists.add(list);
        list.remove(nums.get(1));
        list.add(nums.get(3));
        // 2 3
        lists.add(list);
     }

     public void arithmetic() {
        build_lists();
        List<Double> listd = new ArrayList<>();
        Set<Double> set = new HashSet<>();
        for (List<Integer> list : lists) {
            listd.add((double)list.get(0));
            listd.add((double)list.get(1));
            set.add(listd.get(0) + listd.get(1));
            set.add(listd.get(0) - listd.get(1));
            set.add(listd.get(1) - listd.get(0));
            set.add(listd.get(0) * listd.get(1));
            if (list.get(1) != 0) set.add(listd.get(0) / listd.get(1));
            if (list.get(0) != 0) set.add(listd.get(1) / listd.get(0));
            set.add(Math.pow(listd.get(0), listd.get(1)));
            set.add(Math.pow(listd.get(1), listd.get(0)));
            if (list.get(0) != 0 && list.get(1) != 0) {
                set.add(Math.log(listd.get(0)) / Math.log(listd.get(1)));
                set.add(Math.log(listd.get(1)) / Math.log(listd.get(0)));
            }
            set.add(listd.get(0) * 10 + listd.get(1));
            set.add(listd.get(1) * 10 + listd.get(0));
            index.add(list, set);
            listd.clear();
            set.clear();
        }
        try { index.save("wgyn_index.db"); }
        catch (IOException e) { }
    }
}
