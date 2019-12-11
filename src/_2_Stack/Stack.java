package _2_Stack;

/**
 * 定义需要实现的栈的接口
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
