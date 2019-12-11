package _12_UnionFind;

// 第一版：id用来存储类别，即相互连通的元素对应id[]中的值相同
// 时间复杂度主要在于并操作O(n)，要把所有连通的元素对应的数组值都设置为相同值，查操作O(1)
public class UnionFind1 implements UF {
    private int[] id;    // 我们的第一版Union-Find本质就是一个数组

    public UnionFind1(int size) {
        id = new int[size];
        // 初始化, 每一个id[i]指向自己, 没有合并的元素
        for (int i = 0; i < size; i++)
            id[i] = i;
    }

    @Override
    public int getSize(){
        return id.length;
    }

    // 查找元素x所对应的集合编号    O(1)复杂度
    private int find(int x) {
        if(x < 0 || x >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[x];
    }

    // 查看元素p和元素q是否所属一个集合    O(1)复杂度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合   O(n) 复杂度
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;

        // 合并过程需要遍历一遍所有元素, 将两个元素的所属集合编号合并
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
    }
}