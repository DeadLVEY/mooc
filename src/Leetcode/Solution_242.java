package Leetcode;

/**
 * 判断2个字符串所有字符是否相同(可以直接重新排序比较是否相等)
 * 只包含小写字母
 */
public class Solution_242 {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;

        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c-'a'] ++;
        }
        for(char c : t.toCharArray()){
            freq[c-'a'] --;
            if(freq[c-'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
