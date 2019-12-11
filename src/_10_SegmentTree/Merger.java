package _10_SegmentTree;
/**
 * 定义用于操作两个节点之间操作的接口(例如相加求和)
 * */
public interface Merger<E> {
    E merge(E a, E b);
}
