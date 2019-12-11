package Leetcode;

/**
 * 判断一个字符串是否由重复的子字符串合成
 * abab -> ab
 *
 * 1.将原字符串复制，去掉首尾1个字符，若还包含原字符串，则表示是由重复子字符串组成
 * 2.或者根据字符串长度求因子15:1,3,5 再看每份子串是否相同
 */
public class Solution_459 {
    public boolean repeatedSubstringPattern(String s) {
        return new String((s+s).toCharArray(),1,s.length()*2-2).contains(s);
    }
}
