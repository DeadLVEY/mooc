package Leetcode;

import java.util.Arrays;

/**
 * 三路快排
 */
public class Solution_75 {
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void sortColors(int[] nums) {
        int zero=-1,two=nums.length;
        int i = 0;
        while(i<two){
            if(nums[i] == 0){
                swap(nums,++zero,i++);
            }else if(nums[i] == 1){
                i++;
            }else{
                swap(nums,i,--two);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{0,1,2,0,2,0,1,0,2,0,1,0,2,0,1,0,2,2,2};
        new Solution_75().sortColors(array);
        System.out.println(Arrays.toString(array));
    }
}
