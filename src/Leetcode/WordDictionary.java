package Leetcode;
//211
import java.util.TreeMap;

public class WordDictionary {
    private class Node{
        public boolean isWord;  //用来记录是否为单词
        public TreeMap<Character,Node> next;    //变长的引用，或者Character[26]也行

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
    private Node root;  //不存储字符的根节点

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!cur.next.containsKey(c)){
                cur.next.put(c,new Node());
            }
            //继续下面的字符
            cur = cur.next.get(c);
        }
        //最后到单词结束并判断之前是否存在该单词
        if(!cur.isWord){
            cur.isWord = true;
        }
    }

    //会有.表示任意一个字母
    public boolean search(String word) {
        return search(root,word,0);
    }

    //index表示单词中字母的位置
    private boolean search(Node node,String word,int index){
        if(index==word.length()){
            return node.isWord;
        }
        char c = word.charAt(index);
        if(c!='.'){
            if(!node.next.containsKey(c)){
                return false;
            }
            return search(node.next.get(c),word,index+1);
        }else {
            for(Character key:node.next.keySet()){
                if(search(node.next.get(key),word,index+1)){
                    return true;
                }
            }
            return false;
        }
    }
}
