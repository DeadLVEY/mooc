package Leetcode;

import java.util.TreeMap;

//677
public class MapSum {
    private class Node{
        public int value;
        public TreeMap<Character,Node> next;    //变长的引用，或者Character[26]也行

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for(int i=0;i<key.length();i++){
            char c = key.charAt(i);
            if(!cur.next.containsKey(c)){
                cur.next.put(c,new Node());
            }
            //继续下面的字符
            cur = cur.next.get(c);
        }
        //最后设置值
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        //首先判断是否有包含prefix的子树
        for(char c : prefix.toCharArray()){
            if(!cur.next.containsKey(c)){
                return 0;
            }
            cur = cur.next.get(c);
        }
        //如果存在就递归求所有子树的和
        return sum(cur);
    }

    private int sum(Node node){
        int res = node.value;

        for(char c:node.next.keySet()){
            res += sum(node.next.get(c));
        }
        return res;
    }
}
