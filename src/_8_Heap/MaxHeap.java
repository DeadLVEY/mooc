package _8_Heap;

/**
 * 最大堆：根结点最大，且每个结点的值都比其孩子的值大。
 * 堆是完全二叉树，完全二叉树一定是平衡二叉树(空树或每个节点两个子树的高度差不超过1)
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    // heapify:直接传入数组，将其转换为堆：从最后一个节点的父节点开始siftDown
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }

    // 返回堆中的元素个数
    public int size(){
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引     (索引从0开始，当然也可以从1开始，计算公式稍微改变)
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){
        while(k>0 && data.get(parent(k)).compareTo(data.get(k))<0){
            data.swap(k,parent(k));
            k=parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax(){
        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){
        //当前节点有左子树时就循环(即有孩子节点时)
        while(leftChild(k)<data.getSize()){
            //找出左右孩子节点中的最大值，保存为data[j]
            int j = leftChild(k);
            //如果右孩子存在并且右孩子节点值大于左孩子
            if(j+1<data.getSize() && data.get(j+1).compareTo(data.get(j))>0){
                j = rightChild(k);  //或者写成 j++;
            }

            //直到比孩子节点都要大就停止
            if(data.get(k).compareTo(data.get(j))>0)
                break;

            data.swap(k,j);
            k = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}