package Leetcode;

/**
 * 求最长无重复字符的子序列
 */
public class Solution_3 {
    public int lengthOfLongestSubstring(String s) {
        //所有的ASCII对应的字符，一个char8位(用桶来标记字符是否出现过)
        int[] freq = new int[256];

        int result = 0;
        int l=0,r=-1;

        while(l < s.length()){
            //当++r所在位置的字符没有重复时
            if(r+1<s.length() && freq[s.charAt(r+1)]==0){
                freq[s.charAt(++r)] = 1;
            }else { //遍历结束或者++r所在位置字符与l所在位置的字符相同
                freq[s.charAt(l++)] = 0;
            }
            result = Math.max(result,r-l+1);
        }

        return result;
    }
}
