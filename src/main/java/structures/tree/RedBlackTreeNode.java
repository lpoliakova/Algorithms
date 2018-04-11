package structures.tree;

class RedBlackTreeNode<T extends Comparable<T>> extends TreeNode<T> {

    private boolean isBlack;

    RedBlackTreeNode(T element) {
        super(element);
        isBlack = true;
    }

    RedBlackTreeNode(T element, TreeNode<T> parent, boolean isBlack) {
        super(element, parent);
        this.isBlack = isBlack;
    }

    boolean isBlack() {
        return isBlack;
    }

    boolean isRed() {
        return !isBlack;
    }

    void setBlack() {
        isBlack = true;
    }

    void setRed() {
        isBlack = false;
    }
}
