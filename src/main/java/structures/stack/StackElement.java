package structures.stack;

class StackElement<E> {
    private E item;
    private StackElement next;

    StackElement(E item, StackElement next) {
        this.item = item;
        this.next = next;
    }

    E getItem() {
        return item;
    }

    StackElement<E> getNext() {
        return next;
    }

    boolean hasNext() {
        return getNext() != null;
    }

    void setNext(StackElement<E> element) {
        next = element;
    }
}
