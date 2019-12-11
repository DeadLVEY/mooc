package Leetcode;

import java.util.HashMap;

/**
 * 同构字符串：也可以用2个Map来实现(同290)
 * 输入: s = "egg", t = "add"
 * 输出: true
 */
public class Solution_205 {
    public boolean isIsomorphic(String s, String t) {
        //长度不等直接返回false
        if(s.length() != t.length()){
            return false;
        }

        //<s第i字符，t第i字符>
        HashMap<Character, Character> map = new HashMap<>();

        for(int i=0;i<s.length();i++){
            //如果map中没有配对就返回false
            if(!map.containsKey(s.charAt(i))){
                if(map.containsValue(t.charAt(i)))
                    return false;
                //如果两个字符都没有，就建立一个映射关系
                map.put(s.charAt(i),t.charAt(i));
            }else {
                //如果map中有s第i字符，但是其value不等于t第i字符，就返回false
                if(map.get(s.charAt(i)) != t.charAt(i))
                    return false;
            }
        }

        return true;
    }
}
