
package homework3;
import java.util.*;

/**
 * Implementation of OrderedGeneralTree using a custom binary tree representation.
 * 
 * This tree is specifically designed to maintain an ordered hierarchy where each node can have multiple children. 
 * Each node also has references to its parent, first child, and next sibling, which helps efficiently manage the 
 * relationships within the tree.
 *
 * @param <E> the type of element stored in the tree (e.g., String, Integer, etc.)
 */
public class OrderedGeneralTree<E> extends AbstractTree<E>{

    /**
     * Node class representing a position in the tree.
     * Each node holds references to its element, parent, first child, and next sibling.
     * 
     * The structure enables efficient traversal of the tree to add, remove, and iterate over nodes.
     */
    private static class Node<E> implements Position<E> {
        private E element; // The data stored in the node
        private Node<E> parent; // Reference to the parent node
        private Node<E> firstChild; // Reference to the first child node
        private Node<E> nextSibling; // Reference to the next sibling node

        /**
         * Constructor to initialize a node with its element, parent, first child, and next sibling.
         * 
         * @param element the element to store in the node
         * @param parent the parent of the node (null for the root)
         * @param firstChild the first child node (null if the node has no children)
         * @param nextSibling the next sibling node (null if the node has no sibling)
         */
        public Node(E element, Node<E> parent, Node<E> firstChild, Node<E> nextSibling) {
            this.element = element;
            this.parent = parent;
            this.firstChild = firstChild;
            this.nextSibling = nextSibling;
        }

        @Override
        public E getElement() {
            return element; // Returns the data stored in the node
        }

        public Node<E> getParent() {
            return parent; // Returns the parent node
        }

        public void setParent(Node<E> parent) {
            this.parent = parent; // Sets the parent of the node
        }

        public Node<E> getFirstChild() {
            return firstChild; // Returns the first child of the node
        }

        public void setFirstChild(Node<E> firstChild) {
            this.firstChild = firstChild; // Sets the first child of the node
        }

        public Node<E> getNextSibling() {
            return nextSibling; // Returns the next sibling of the node
        }

        public void setNextSibling(Node<E> nextSibling) {
            this.nextSibling = nextSibling; // Sets the next sibling of the node
        }
    }

    private Node<E> root = null; // Root node of the tree (initially null, meaning no tree)
    private int size = 0; // Size of the tree (number of nodes)

    @Override
    public Position<E> root() {
        return root; // Returns the root node of the tree
    }

    /**
     * Returns the parent of the given position.
     * 
     * This method is used to traverse upwards from a given node.
     * If the position is the root node, it returns null since the root has no parent.
     * 
     * @param p the position whose parent is to be returned
     * @return the parent position of the node at position p
     * @throws IllegalArgumentException if the position is invalid
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p); // Validate the position to ensure it belongs to this tree
        return node.getParent(); // Return the parent of the node
    }

    /**
     * Returns an iterable collection of children of the given position.
     * 
     * This method allows you to retrieve all the children of a node. Each child is connected 
     * through a linked list of sibling nodes, which allows for efficient traversal.
     * 
     * @param p the position whose children are to be returned
     * @return an iterable collection of children positions
     * @throws IllegalArgumentException if the position is invalid
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p); // Validate the position
        List<Position<E>> children = new ArrayList<>(); // List to store children nodes
        Node<E> child = node.getFirstChild(); // Start from the first child
        while (child != null) { 
            children.add(child); // Add the child to the list
            child = child.getNextSibling(); // Move to the next sibling
        }
        return children; // Return the list of children
    }

    /**
     * Returns the number of children of the given position.
     * 
     * This method counts how many children the node at the given position has.
     * 
     * @param p the position whose number of children is to be counted
     * @return the number of children of the position
     * @throws IllegalArgumentException if the position is invalid
     */
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p); // Validate the position
        int count = 0; // Initialize count
        Node<E> child = node.getFirstChild(); // Start from the first child
        while (child != null) {
            count++; // Increment count for each child
            child = child.getNextSibling(); // Move to the next sibling
        }
        return count; // Return the total number of children
    }

    /**
     * Adds a root node to the tree.
     * 
     * This method sets the root of the tree if the tree is empty.
     * It ensures that no more than one root exists in the tree.
     * 
     * @param e the element to store in the root node
     * @return the newly created root node
     */
    public Position<E> addRoot(E e) {
        if (!isEmpty()) return null; // If the tree is not empty, the root cannot be added
        root = new Node<>(e, null, null, null); // Create the root node
        size = 1; // The tree now contains one node
        return root; // Return the root node
    }

    /**
     * Adds a child node to the given position (parent node).
     * 
     * This method appends the new child node to the list of children of the parent node.
     * If the parent already has children, the new child becomes the next sibling of the last child.
     * 
     * @param p the parent position where the child is to be added
     * @param e the element to store in the new child node
     * @return the newly created child node
     */
    public Position<E> addChild(Position<E> p, E e) {
        Node<E> parent = validate(p); // Validate the parent position
        Node<E> child = new Node<>(e, parent, null, null); // Create a new child node

        // If the parent has no children, set the new child as the first child
        if (parent.getFirstChild() == null) {
            parent.setFirstChild(child);
        } else {
            // Otherwise, find the last sibling and append the new child
            Node<E> sibling = parent.getFirstChild();
            while (sibling.getNextSibling() != null) {
                sibling = sibling.getNextSibling(); // Traverse to the last sibling
            }
            sibling.setNextSibling(child); // Append the new child as the next sibling
        }

        size++; // Increase the size of the tree
        return child; // Return the newly created child node
    }

    /**
     * Removes a node from the tree.
     * 
     * This method removes a node and adjusts its parent's child list and siblings accordingly.
     * If the node has children, they are re-linked to the parent.
     * 
     * @param p the position of the node to be removed
     * @return the element stored in the removed node
     */
    public E remove(Position<E> p) {
        Node<E> node = validate(p); // Validate the node position
        if (node == root) return null; // Root node cannot be removed

        Node<E> parent = node.getParent(); // Get the parent of the node
        Node<E> child = node.getFirstChild(); // Get the first child of the node

        // If the node has children, re-link them to the parent
        if (child != null) {
            if (parent.getFirstChild() == node) {
                parent.setFirstChild(child); // If the node is the first child, update the parent
            } else {
                // If the node is a sibling, find the sibling and update the parent
                Node<E> sibling = parent.getFirstChild();
                while (sibling.getNextSibling() != node) {
                    sibling = sibling.getNextSibling(); // Traverse to find the node
                }
                sibling.setNextSibling(child); // Link the parent's previous sibling to the child
            }

            // Re-link the children to the parent and update their parent reference
            while (child.getNextSibling() != null) {
                child.setParent(parent);
                child = child.getNextSibling();
            }
            child.setParent(parent);
        } else {
            // If the node has no children, simply remove it from the parent's child list
            if (parent.getFirstChild() == node) {
                parent.setFirstChild(node.getNextSibling());
            } else {
                Node<E> sibling = parent.getFirstChild();
                while (sibling.getNextSibling() != node) {
                    sibling = sibling.getNextSibling(); // Traverse to find the node
                }
                sibling.setNextSibling(node.getNextSibling()); // Link the previous sibling to the next sibling
            }
        }

        size--; // Decrease the size of the tree after removal
        return node.getElement(); // Return the element of the removed node
    }

    /**
     * Displays the tree structure starting from the root.
     * 
     * This method prints the tree in a hierarchical format, where each level of the tree
     * is indented based on its depth, making the structure visually clear.
     */
    public void displayTree() {
        if (!isEmpty()) displayTree(root, 0); // Display the tree starting from the root
    }

    /**
     * Recursive helper method to display the tree structure.
     * 
     * This method uses recursion to print each node at the appropriate level of indentation.
     * 
     * @param node the current node to be displayed
     * @param depth the depth of the node in the tree (used for indentation)
     */
    private void displayTree(Node<E> node, int depth) {
        // Print the node's element with indentation based on its depth
        for (int i = 0; i < depth; i++) {
            System.out.print(". "); // Print indentation to represent the depth
        }
        System.out.println(node.getElement()); // Print the node's element
        Node<E> child = node.getFirstChild(); // Get the first child of the current node
        while (child != null) {
            displayTree(child, depth + 1); // Recursively display each child
            child = child.getNextSibling(); // Move to the next sibling
        }
    }

    /**
     * Validates that the position belongs to this tree and is not null.
     * 
     * This method ensures that a given position is valid before performing any operation on it.
     * 
     * @param p the position to be validated
     * @return the node corresponding to the position
     * @throws IllegalArgumentException if the position is invalid
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position type.");
        Node<E> node = (Node<E>) p;
        // Check if the position belongs to this tree
        if (node.getParent() == null && node != root) {
            throw new IllegalArgumentException("Position does not belong to this tree.");
        }
        return node; // Return the validated node
    }

    @Override
    public int size() {
        return size; // Return the number of nodes in the tree
    }
}

