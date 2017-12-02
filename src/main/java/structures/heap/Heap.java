package structures.heap;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<E extends Comparable<E>> extends AbstractQueue<E> implements java.util.Queue<E> {

    private ArrayList<E> elements;
    private int size = 0;

    public Heap() {
        elements = new ArrayList<>();
    }

    public Heap(int initialCapacity) {
        elements = new ArrayList<>(initialCapacity);
    }

    public Heap(Heap<E> otherHeap) {
        elements = new ArrayList<>(otherHeap.elements);
    }

    @Override
    public boolean offer(E e) {
        elements.add(e);
        restoreBottom();
        size++;
        return true;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements.get(0);
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = elements.get(0);
        size--;
        elements.set(0, elements.get(size));
        elements.remove(size);
        restoreTop();
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Heap))
            return false;

        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((Heap<?>) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

    @Override
    public Iterator<E> iterator() {
        return new HeapIterator<E>(this);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        elements = new ArrayList<>();
        size = 0;
    }

    private void restoreBottom() {
        int currentPosition = size;
        int checkPosition = getFatherPosition(currentPosition);
        while (checkPosition >= 0) {
            if (elements.get(checkPosition).compareTo(elements.get(currentPosition)) > 0) {
                swap(checkPosition, currentPosition);
            } else {
                break;
            }
            currentPosition = checkPosition;
            checkPosition = getFatherPosition(currentPosition);
        }
    }

    private int getFatherPosition(int position) {
        return (position + 1) / 2 - 1;
    }

    private void restoreTop() {
        int currentPosition = 0;
        int checkPosition = getLeftChildPosition(currentPosition);
        while(checkPosition < size) {
            if (elements.get(checkPosition).compareTo(elements.get(currentPosition)) < 0
                    && checkPosition + 1 != size
                    && elements.get(checkPosition + 1).compareTo(elements.get(currentPosition)) < 0) {
                if (elements.get(checkPosition).compareTo(elements.get(checkPosition + 1)) < 0) {
                    swap(checkPosition, currentPosition);
                    currentPosition = checkPosition;
                } else {
                    swap(checkPosition + 1, currentPosition);
                    currentPosition = checkPosition + 1;
                }
            } else if (elements.get(checkPosition).compareTo(elements.get(currentPosition)) < 0) {
                swap(checkPosition, currentPosition);
                currentPosition = checkPosition;
            } else if (checkPosition + 1 != size
                    && elements.get(checkPosition + 1).compareTo(elements.get(currentPosition)) < 0) {
                swap(checkPosition + 1, currentPosition);
                currentPosition = checkPosition + 1;
            } else {
                break;
            }
        }
    }

    private int getLeftChildPosition(int position) {
        return position * 2 + 1;
    }

    private void swap(int position1, int position2) {
        E temp = elements.get(position1);
        elements.set(position1, elements.get(position2));
        elements.set(position2, temp);
    }
}
