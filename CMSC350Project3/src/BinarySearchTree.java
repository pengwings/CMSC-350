/**
 * BinarySearchTree.java
 * Brian Yu
 * 4/26/2020
 * This class defines a generic Binary Search Tree object that can store nodes of any object that implements the comparable interface
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    //default constructor for the binary search tree object, creates empty binary search tree
    public BinarySearchTree() {
        this.root = null;
    }
    //getter for private root instance variable
    public Node<T> getRoot() {
        return this.root;
    }
    //populates binary search tree with values from input array
    public void createTree(T[] valueArray) {
        for(int i=0; i<valueArray.length; i++) {
            insert(valueArray[i]);
        }
    }
    //method that calls recursive insert method
    public void insert(T value) {
        this.root = recursiveInsert(this.root, value);
    }
    //recursive method that checks for root node, and then inserts values to the left node if they are smaller than root
    //or to the right node if they are larger than root
    public Node<T> recursiveInsert(Node<T> root, T value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if(value.compareTo(root.value) < 0) {
            root.left = recursiveInsert(root.left, value);
        } else if(value.compareTo(root.value) >= 0){
            root.right = recursiveInsert(root.right, value);
        }
        return root;
    }
    //recursive method that traverses binary search tree inorder to return String of sorted list by ascending value
    public String ascendingSort(Node<T> node) {
        StringBuilder ascendingTree = new StringBuilder();
        if(node != null) {
            ascendingTree.append(ascendingSort(node.left));
            ascendingTree.append(node.value);
            ascendingTree.append(" ");
            ascendingTree.append(ascendingSort(node.right));
        }
        return ascendingTree.toString();
    }
    //recursive method that traverses binary search tree reverse inorder to return String of sorted list by descending value
    public String descendingSort(Node<T> node) {
        StringBuilder descendingTree = new StringBuilder();
        if(node != null) {
            descendingTree.append(descendingSort(node.right));
            descendingTree.append(node.value);
            descendingTree.append(" ");
            descendingTree.append(descendingSort(node.left));
        }
        return descendingTree.toString();
    }
    //inner class defining Node object to store in binary search tree
    class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left, right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
