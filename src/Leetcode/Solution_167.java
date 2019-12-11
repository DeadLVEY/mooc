package Leetcode;

/**
 * 对撞指针，分别从头尾扫描，时间复杂度为O(n)
 */
class Solution_167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int i=0,j=numbers.length-1;
        while(i<j){
            if(numbers[i] + numbers[j] == target){
                result[0] = i+1;
                result[1] = j+1;
                return result;
            }else if(numbers[i] + numbers[j] < target){
                i++;
            }else j--;
        }
        return result;
    }
}
