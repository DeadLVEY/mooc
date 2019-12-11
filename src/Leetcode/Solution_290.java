package Leetcode;

import java.util.HashMap;

/**
 * 模式匹配
 * pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 */
public class Solution_290 {
    public boolean wordPattern(String pattern, String str) {
        char[] str1 = pattern.toCharArray();
        String[] str2 = str.split(" ");

        if(str1.length != str2.length)
            return false;

        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        for(int i=0;i<str1.length;i++){
            //第一个无法判断所以直接将内容和位置存储到map中
            //不判断是否存在会空指针异常
            int i1 = map1.containsKey(str1[i]) ? map1.get(str1[i]) : -1;
            int i2 = map2.containsKey(str2[i]) ? map2.get(str2[i]) : -1;

            if(i1 != i2)
                return false;

            map1.put(str1[i],i);
            map2.put(str2[i],i);
        }

        return true;
    }
}
