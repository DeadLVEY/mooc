package _12_UnionFind;

// 第四版：通过rank数组记录根所在树的深度来进行并操作
public class UnionFind4 implements UF{
    private int[] parent;   // parent[i]表示第i个元素所指向的父节点
    private int[] rank;     // rank[i]表示以i为根的树的深度

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合，且深度为1
        for(int i = 0 ; i < size ; i ++){
            parent[i] = i;
            rank[i] = 1;
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

        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else{ // sz[qRoot] == sz[pRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
