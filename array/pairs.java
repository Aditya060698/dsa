package array;

import java.util.*;

public class pairs {
    public static void main(String[] args) {
        System.out.println("Implementing pair logic");
        int [] arr=new int[]{10,5,2,3,-6,9,11};
        int S=4;
//        int[] result=pairSum(arr,S);
                int[] result=twoSum(arr,S);

        System.out.println(result[0]+" "+result[1]);
    }
    public static int[] pairSum(int arr[], int S){
        //Creating a set to check if pairing with any element already pushed in set can give us the desired
        //sum,
        Set<Integer> set=new HashSet<>();
        int[] res=new int[2];
        Arrays.stream(arr).forEach((ele) -> {
            if (set.contains(S-ele)){
                 res[0]=ele;
                 res[1]=S-ele;
            }else{
                set.add(ele);
            }
        });
        return res;
    }
    public static int[] twoSum(int[] nums, int target) {

        // Set<Integer> set=new HashSet<>();
        int[] res=new int[2];
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target-nums[i]) && map.get(target-nums[i])!=i){
                res[0]=map.get(target-nums[i]);
                res[1]=i;
            }else{
                map.put(nums[i],i);
            }
        }
        return res;

    }
}


