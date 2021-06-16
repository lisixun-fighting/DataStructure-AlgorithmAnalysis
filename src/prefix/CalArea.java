package prefix;

import prefix.pojo.Round;
import prefix.pojo.Shape;
import prefix.pojo.Square;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CalArea {
    public static int totalArea(Shape[] arr) {
        int total = 0;
        for (Shape s : arr) {
            if(s != null)
                total += s.area();
        }
        return total;
    }

    public static int totalArea(Collection<? extends Shape> arr) {
        int total = 0;
        for (Shape s : arr) {
            if (s != null)
                total += s.area();
        }
        return total;
    }

    public static void main(String[] args) {
        Shape[] arr = new Shape[10];
        Set<Shape> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i % 2 == 1 ? new Round(1) : new Square(1,1);
            set.add(i % 2 == 1 ? new Round(1) : new Square(1,1));
        }
        int res1 = totalArea(arr);
        int res2 = totalArea(set);
        System.out.println(res1);
        System.out.println(res2);
    }

    public String largestNumber(int[] nums) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            int index = i;
            for (int j = i+1; j < nums.length; j++) {
                if(((nums[j]+""+nums[index])).compareTo(nums[index]+""+nums[j]) < 0) {
                    index = j;
                }
            }
            if(nums[index] != nums[i]) {
                nums[index] = nums[i] + nums[index];
                nums[i] = nums[index] - nums[i];
                nums[index] = nums[index] - nums[i];
            }
            sb.append(nums[i]);
        }
        if(nums[0] == 0) return "0";
        return sb.toString();
    }

}
