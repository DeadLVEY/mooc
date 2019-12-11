package _12_UnionFind;

// 第二版：用数组构建孩子指向父节点的树：id值用来指向父节点，元素是否连通取决于其父节点是否对应的id[]值相同
// 时间复杂度取决于查操作O(h)，因为并操作主要步骤也是查操作，所以也为O(h)
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        // 初始化, 每一个id[i]指向自己, 表示每一个元素自己自成一个集合
        for (int i = 0; i < size; i++)
            parent[i] = i;
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

    // 合并元素p和元素q所属的集合，只用将根设置相同即可   O(h) 复杂度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        pRoot = qRoot;
    }
}