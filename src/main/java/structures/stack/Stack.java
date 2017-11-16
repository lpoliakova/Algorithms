package structures.stack;

import java.util.EmptyStackException;

public class Stack<E> {

    private Node head;

    public boolean isEmpty() {
        return head == null;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return head.getItem();
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        E item = head.getItem();
        head = head.getNext();
        return item;
    }

    public void push(E item) {
        head = new Node(item, head);
    }

    private class Node {
        private E item;
        private Node next;

        Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }

        E getItem() {
            return item;
        }

        Node getNext() {
            return next;
        }
    }

}
