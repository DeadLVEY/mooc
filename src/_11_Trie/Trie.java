package _11_Trie;
import java.util.TreeMap;
/**
 * 前缀树(字典树)：暂时只存储字符所以不实现泛型
 */
public class Trie {
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

        @Override//测试用
        public String toString() {
            return  "isWord=" + isWord + "\n" + "next=" + next;
        }
    }

    private Node root;  //不存储字符的根节点
    private int size;   //单词的总个数

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    //添加单词
    public void add(String word){
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
            size ++;
        }
    }

    //查询单词是否存在
    public boolean contains(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!cur.next.containsKey(c)){
                return false;
            }
            cur = cur.next.get(c);
        }
        //panda中pan是否被标记为单词
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(!cur.next.containsKey(c)){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    // 返回是否删除成功(自己测试实现)
    public Boolean remove(String word){
        Node cur = root;
        Node divide = root;     //用来标记可能存在的最后一个分支节点
        char flagLetter=' ';    //用来记录remove中的字母

        for(char c:word.toCharArray()){
            //如果没有待删除单词就直接返回false
            if(!cur.next.containsKey(c)){
                return false;
            }

            //如果待删单词前面有别的单词或者某个字母有多个后续节点就记录当前位置
            //tea teach teacher
            //删除teacher：先记录a所在节点和字母c    再记录h所在节点和字母e    最后执行remove('e')
            //tell tall
            //删除tall：记录t所在节点和字母a    最后执行remove('a')

            if(cur.isWord || cur.next.size()>1){
                divide = cur;
                flagLetter = c; //记录后一个字母
            }
            cur = cur.next.get(c);
        }

        // 遍历结束到了待删单词的末尾
        // 首先如果isWord=false则表明并没有此单词
        if(!cur.isWord){
            return false;
        }

        // 如果有后续节点就直接设置isWord=false
        if(!cur.next.isEmpty()){
            cur.isWord = false;
        }else {
            //如果单词末尾没有节点了，就一直删除到前一个单词末尾或者分支处(divide)
            divide.next.remove(flagLetter);
        }
        size --;
        return true;
    }

    @Override//测试用
    public String toString() {
        return  "size=" + size + "\n" + "root:" + root;
    }
}
