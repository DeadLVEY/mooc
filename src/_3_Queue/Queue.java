package _3_Queue;
/**
 * 定义需要实现的栈的接口(跟stack接口方法一样)
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();

}
