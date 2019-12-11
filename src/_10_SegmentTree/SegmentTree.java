package _10_SegmentTree;

/**
 * 线段树(区间树)：是平衡二叉树而不是完全二叉树
 * 不考虑add操作假定数组大小不会动态改变的话，n个数的话数组大小为4n=最后一层2n(很多null)+前面所有节点2n(看做是满二叉树)
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> meger) {
        this.merger = meger;

        data = (E[])new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        // 一直到叶子节点
        if(l==r){
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l)/2;    //其实就是(l+r)/2 防止太大越界
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        buildSegmentTree(leftTreeIndex,l, mid);
        buildSegmentTree(rightTreeIndex,mid+1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    // 查询返回区间[queryL, queryR]的值(和)
    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        // 如果刚好找到这个区间
        if(l==queryL && r==queryR)
            return tree[treeIndex];

        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 如果区间只在某一单侧子树中
        if(queryL>=mid+1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if(queryR<=mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }

        // 区间分别在两个子树中
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult,rightResult);
    }

    // 将index位置的值，更新为e
    public void set(int index, E e){

        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e;
        // 再去递归改变tree数组中的存储的值
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e){
        if(l==r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(index>=mid+1){
            set(rightTreeIndex,mid+1,r,index,e);
        }else { // index <= mid
            set(leftTreeIndex,l,mid,index,e);
        }

        //重新设置每个受影响的父节点的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }


    public static void main(String[] args) {
        Integer [] a = {-4,7,3,-6,-1,2,4};
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(a, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a+b;
            }
        });

        //测试set方法
        segmentTree.set(1,6);
        segmentTree.set(2,-2);
        //-4,6,-2,-6,-1,2,4
        System.out.println(segmentTree);

        System.out.println(segmentTree.query(0,1));
        System.out.println(segmentTree.query(2,4));
        System.out.println(segmentTree.query(1,2));
        System.out.println(segmentTree.query(0,6));
    }
}
