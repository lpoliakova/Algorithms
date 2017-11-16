package structures.queue;

class QueueElement<E> {

    private E element;
    private QueueElement<E> next;

    QueueElement(E element) {
        this.element = element;
    }

    QueueElement(E element, QueueElement next) {
        this(element);
        this.next = next;
    }

    E getElement() {
        return element;
    }

    QueueElement<E> getNext() {
        return next;
    }

    boolean hasNext() {
        return next != null;
    }

    void setNext(QueueElement<E> next) {
        this.next = next;
    }
}
