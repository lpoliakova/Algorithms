package structures.tree;

public class UnbalancedBinarySearchTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {
    @Override
    public void insert(T element) {
        if (isEmpty()) {
            setRoot(new TreeNode<>(element));
            return;
        }

        TreeNode<T> parentForInsert = findInsertionNode(element);
        localInsertElement(parentForInsert, element);
    }

    @Override
    public void delete(T element) {
        TreeNode<T> nodeForDeletion = getNodeForElement(element);

        if (nodeForDeletion == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ELEMENT_MESSAGE);
        }

        nodeForDeletion = checkNodeForDeletionIfItHasTwoChildren(nodeForDeletion);
        deleteNode(nodeForDeletion);
    }

    @Override
    public void delete(int position) {
        TreeNode<T> nodeForDeletion = getNodeAtPosition(position);

        nodeForDeletion = checkNodeForDeletionIfItHasTwoChildren(nodeForDeletion);
        deleteNode(nodeForDeletion);
    }

    private void localInsertElement(TreeNode<T> parentForInsert, T element) {
        if (parentForInsert.getElement().compareTo(element) > 0) {
            parentForInsert.setLeftChild(new TreeNode<>(element, parentForInsert));
        } else if (parentForInsert.getElement().compareTo(element) < 0) {
            parentForInsert.setRightChild(new TreeNode<>(element, parentForInsert));
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
        }
        incrementNodeSizes(parentForInsert);
    }
}
