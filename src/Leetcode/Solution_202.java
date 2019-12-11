package Leetcode;

import java.util.TreeSet;

/**
 * 快乐数
 * 输入: 19   输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class Solution_202 {
    public boolean isHappy(int n) {
        TreeSet<Integer> set = new TreeSet<>();
        int m = 0;

        while(true){
            while(n!=0){
                m += Math.pow(n%10,2);
                n /= 10;
            }
            if(m==1){
                return true;
            }
            //如果陷入循环中就说明不是快乐数
            if(set.contains(m)){
                return false;
            }else {
                set.add(m);
                n = m;
                m = 0;
            }
        }
    }
}
