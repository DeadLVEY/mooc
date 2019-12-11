package Leetcode;

import java.util.*;

public class Solution_347 {

    class Freq implements Comparable<Freq>{
        Integer key;    //int也可以无所谓
        Integer freq;

        public Freq(Integer key, Integer freq) {
            this.key = key;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq f) {
            return this.freq-f.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : nums){
            if(!map.containsKey(i)){
                map.put(i,1);
            }else {
                map.put(i,map.get(i)+1);
            }
        }

        //优先队列只存储key，java提供的是最小堆
        PriorityQueue<Freq> q = new PriorityQueue<>();

        for(Integer key : map.keySet()){
            if(q.size()<k){
                q.add(new Freq(key,map.get(key)));
            }else if(map.get(key)>q.peek().freq){
                q.remove();
                q.add(new Freq(key,map.get(key)));
            }
        }

        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            list.add(q.remove().key);
        }
        return list;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        printList((new Solution_347()).topKFrequent(nums, k));
    }
}