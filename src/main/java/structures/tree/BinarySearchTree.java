package structures.tree;

public interface BinarySearchTree<T extends Comparable<T>> {
    void insert(T element);

    void delete(T element);

    void delete(int position);

    boolean contains(T element);

    T getElementFromTree(T element);

    T getElementAtPosition(int position);

    int getPositionOfElement(T element);

    T getMin();

    T getMax();

    T getPredecessor(T element);

    T getSuccessor(T element);

    String print(boolean isAscending);

    int size();

    boolean isEmpty();
}
