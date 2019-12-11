package _14_RedBlackTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * 红黑树：左子树的值都比父节点小，右子树的值都比父节点大
 * */
public class RBTree<K extends Comparable<K>,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //判断节点的颜色
    public boolean isRed(Node node){
        if(node==null)
            return BLACK;
        return node.color;
    }

    // 向红黑树中添加新的元素
    public void add(K key, V value){
        root = add(root,key,value);
        root.color = BLACK; //根节点一直为黑色
    }

    // 向以node为根的红黑树中插入元素，递归算法
    // 返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value){
        if(node == null){
            size ++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key ,value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key ,value);
        else {  //key已存在就修改value
            node.value = value;
        }

        //    黑              红
        //     \   左旋转->   /
        //     红           黑
        if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        //      黑
        //     /             黑
        //    红     ->     / \
        //   /             红 红
        //  红
        if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        //    黑            红
        //   / \    ->     / \
        //  红  红        黑  黑
        if(isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // 看红黑树中是否包含元素key
    public boolean contains(K key){
        return contains(root, key);
    }

    // 看以node为根的红黑树中是否包含元素key, 递归算法
    private boolean contains(Node node, K key){
        if(node == null)
            return false;

        if(key.compareTo(node.key) == 0)
            return true;
        else if(key.compareTo(node.key) < 0)
            return contains(node.left, key);
        else // e.compareTo(node.key) > 0
            return contains(node.right, key);
    }

    // 红黑树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    // 前序遍历以node为根的红黑树, 递归算法
    private void preOrder(Node node){
        if(node == null)
            return;

        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 红黑树的非递归前序遍历
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.key);

            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    // 红黑树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    // 中序遍历以node为根的红黑树, 递归算法
    private void inOrder(Node node){

        if(node == null)
            return;

        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    // 红黑树的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    // 后序遍历以node为根的红黑树, 递归算法
    private void postOrder(Node node){
        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    // 红黑树的层序遍历(通过队列来记录遍历顺序)
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.key);

            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    // 寻找红黑树的最小元素
    public Node minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root);
    }

    // 返回以node为根的红黑树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 寻找红黑树的最大元素
    public Node maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root);
    }

    // 返回以node为根的红黑树的最大值所在的节点
    private Node maximum(Node node){
        if(node.right == null)
            return node;

        return maximum(node.right);
    }

    // 从红黑树中删除最小值所在节点, 返回最小值
    public Node removeMin(){
        Node ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的红黑树中的最小节点
    // 返回删除节点后新的红黑树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;   //使最小值的右子树成为最小值父节点的左子树
        }
        //一直递归
        node.left = removeMin(node.left);
        return node;
    }

    // 从红黑树中删除最大值所在节点
    public Node removeMax(){
        Node ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的红黑树中的最大节点
    // 返回删除节点后新的红黑树的根
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从红黑树中删除元素为e的节点
    public void remove(K key){
        root = remove(root, key);
    }

    // 删除掉以node为根的红黑树中值为e的节点, 递归算法
    // 返回删除节点后新的红黑树的根
    private Node remove(Node node, K key){
        //没有找到此节点
        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // e.compareTo(node.key) == 0
            // 待删除节点左子树为空的情况：使待删节点的右子树成为待删节点父节点的右子树
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况：使待删节点的左子树成为待删节点父节点的左子树
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点(后继节点)     或者找前驱节点(左子树中最大节点)
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

}
