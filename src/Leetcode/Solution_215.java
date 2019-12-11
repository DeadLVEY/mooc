package Leetcode;

import java.util.PriorityQueue;

/**
 * 求一个数组中第K大的元素
 * 输入: [0,4,5,5,6] 和 k = 4
 * 输出: 4
 * 思路：快排NlogN 可以建立一个只有k个元素的最小堆，最后堆顶就是第k大的元素NlogK
 */
public class Solution_215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((a, b) -> a - b);
        for(int i : nums){
            heap.add(i);
            if(heap.size() > k){
                heap.poll();
            }
        }
        return heap.peek();
    }
}
