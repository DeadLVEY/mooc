package _12_UnionFind;

// 第三版：增加一个数组用于记录根节点所在集合中的元素个数
// 在并操作时将元素个数少的根指向元素个数多的根，以降低树的高度h(但是通过元素个数来判断太片面了，反而可能会增加h)
public class UnionFind3 implements UF{

    private int[] parent; // parent[i]表示第i个元素所指向的父节点
    private int[] sz;     // sz[i]表示以i为根的集合中元素个数

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for(int i = 0 ; i < size ; i ++){
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 查找元素x所对应的根节点编号    O(h)复杂度
    private int find(int x) {
        if(x < 0 || x >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        while(x!=parent[x]){
            //一直向上找到自己所属树的根节点
            x = parent[x];
        }
        return x;
    }

    // 查看元素p和元素q是否所属一个集合    O(h)复杂度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 根据两个元素所在树的元素个数不同判断合并方向
    // 将元素个数少的集合合并到元素个数多的集合上
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;

        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else{ // sz[qRoot] <= sz[pRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
