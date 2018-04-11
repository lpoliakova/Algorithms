package structures.tree;

class TreeNode<T extends Comparable<T>> {

    private T element;
    private TreeNode<T> parent;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private int nodeSize = 1;

    TreeNode(T element) {
        this.element = element;
    }

    TreeNode(T element, TreeNode<T> parent) {
        this.element = element;
        this.parent = parent;
    }

    T getElement() {
        return element;
    }

    void setElement(T element) {
        this.element = element;
    }

    TreeNode<T> getParent() {
        return parent;
    }

    void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    boolean hasParent() {
        return parent != null;
    }

    TreeNode<T> getLeftChild() {
        return leftChild;
    }

    void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    boolean hasLeftChild() {
        return leftChild != null;
    }

    TreeNode<T> getRightChild() {
        return rightChild;
    }

    void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    boolean hasRightChild() {
        return rightChild != null;
    }

    int getNodeSize() {
        return nodeSize;
    }

    void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }

    void incrementNodeSize() {
        nodeSize++;
    }

    void decrementNodeSize() {
        nodeSize--;
    }
}
