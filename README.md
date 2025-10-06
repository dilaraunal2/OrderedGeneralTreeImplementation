# OrderedGeneralTree Implementation

A Java implementation of an ordered general tree data structure where each node can have multiple children. This implementation uses a custom binary representation with parent, first-child, and next-sibling references for efficient tree traversal and manipulation.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Descriptions](#class-descriptions)
- [Usage](#usage)
- [API Reference](#api-reference)
- [Example Output](#example-output)
- [Time Complexity](#time-complexity)
- [Requirements](#requirements)
- [Installation](#installation)
- [License](#license)

## Overview

This project implements a general tree structure where:
- Each node can have an arbitrary number of children
- Children are maintained in order
- The tree supports standard operations like adding, removing, and traversing nodes
- Nodes are connected via parent, first-child, and next-sibling pointers

## Features

- **Multiple Children per Node**: Unlike binary trees, each node can have any number of children
- **Ordered Structure**: Children are maintained in the order they were added
- **Flexible Traversal**: Supports preorder, postorder, and breadth-first traversal
- **Dynamic Operations**: Add and remove nodes dynamically
- **Type Safety**: Generic implementation supporting any type
- **Visual Display**: Built-in method to display tree structure with indentation

## Project Structure

```
homework3/
├── AbstractTree.java          # Abstract base class with common tree operations
├── OrderedGeneralTree.java    # Main tree implementation
├── Tree.java                  # Tree interface
├── Position.java              # Position interface for node access
├── Queue.java                 # Queue interface
├── LinkedQueue.java           # Queue implementation for traversal
├── SinglyLinkedList.java      # Underlying list structure
└── Driver.java                # Test/demo program
```

## Class Descriptions

### OrderedGeneralTree<E>

The main tree implementation that extends `AbstractTree<E>`.

**Key Features:**
- Maintains ordered children using first-child/next-sibling representation
- Supports adding root, adding children, and removing nodes
- Provides tree visualization

**Internal Node Structure:**
```java
private static class Node<E> implements Position<E> {
    private E element;
    private Node<E> parent;
    private Node<E> firstChild;
    private Node<E> nextSibling;
}
```

### AbstractTree<E>

Provides default implementations for common tree operations:
- Checking if a position is internal/external/root
- Counting children and total size
- Computing depth and height
- Tree traversal algorithms (preorder, postorder, breadth-first)

### Interfaces

- **Tree<E>**: Defines the contract for tree operations
- **Position<E>**: Represents a position in the tree
- **Queue<E>**: Queue interface for breadth-first traversal

## Usage

### Creating a Tree

```java
OrderedGeneralTree<String> tree = new OrderedGeneralTree<>();
```

### Adding Nodes

```java
// Add root
Position<String> root = tree.addRoot("Asia");

// Add children
Position<String> india = tree.addChild(root, "India");
Position<String> china = tree.addChild(root, "China");
Position<String> japan = tree.addChild(root, "Japan");

// Add nested children
tree.addChild(india, "New Delhi");
tree.addChild(china, "Beijing");
tree.addChild(china, "Shanghai");
```

### Displaying the Tree

```java
tree.displayTree();
```

Output:
```
Asia
. India
. . New Delhi
. China
. . Beijing
. . Shanghai
. Japan
```

### Removing Nodes

```java
// Remove a leaf node
tree.remove(beijing);

// Remove an internal node (children are re-attached to parent)
tree.remove(china);
```

### Traversing the Tree

```java
// Get children of a node
for (Position<String> child : tree.children(asia)) {
    System.out.println(child.getElement());
}

// Preorder traversal
for (Position<String> pos : tree.preorder()) {
    System.out.println(pos.getElement());
}

// Breadth-first traversal
for (Position<String> pos : tree.breadthfirst()) {
    System.out.println(pos.getElement());
}
```

## API Reference

### OrderedGeneralTree Methods

| Method | Description | Return Type |
|--------|-------------|-------------|
| `addRoot(E e)` | Adds a root node to the tree | `Position<E>` |
| `addChild(Position<E> p, E e)` | Adds a child to the given parent | `Position<E>` |
| `remove(Position<E> p)` | Removes a node (cannot remove root) | `E` |
| `displayTree()` | Prints tree structure with indentation | `void` |
| `root()` | Returns the root position | `Position<E>` |
| `parent(Position<E> p)` | Returns the parent of a position | `Position<E>` |
| `children(Position<E> p)` | Returns iterable of children | `Iterable<Position<E>>` |
| `numChildren(Position<E> p)` | Returns number of children | `int` |
| `size()` | Returns total number of nodes | `int` |
| `isEmpty()` | Checks if tree is empty | `boolean` |

### AbstractTree Methods (Inherited)

| Method | Description | Return Type |
|--------|-------------|-------------|
| `isInternal(Position<E> p)` | Checks if position has children | `boolean` |
| `isExternal(Position<E> p)` | Checks if position is a leaf | `boolean` |
| `isRoot(Position<E> p)` | Checks if position is root | `boolean` |
| `depth(Position<E> p)` | Returns depth of position | `int` |
| `height(Position<E> p)` | Returns height of subtree | `int` |
| `preorder()` | Returns preorder traversal | `Iterable<Position<E>>` |
| `postorder()` | Returns postorder traversal | `Iterable<Position<E>>` |
| `breadthfirst()` | Returns breadth-first traversal | `Iterable<Position<E>>` |

## Example Output

Running the `Driver.java` program produces:

```
1. Adding root node (Continent):
Asia

2. Adding countries to 'Asia':
Asia
. India
. China
. Japan

3. Adding cities to 'India' and 'China':
Asia
. India
. . New Delhi
. China
. . Beijing
. . Shanghai
. Japan

4. Number of countries in 'Asia': 3

5. Countries in 'Asia':
India
China
Japan

6. Removing 'Beijing' from the tree:
Removed node: Beijing
Asia
. India
. . New Delhi
. China
. . Shanghai
. Japan

7. Removing 'China' from the tree:
Removed node: China
Asia
. India
. . New Delhi
. Shanghai
. Japan

8. Trying to remove the root node (Asia):
Cannot remove the root node. Returning null.
```

## Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `addRoot(E e)` | O(1) | Constant time |
| `addChild(Position<E> p, E e)` | O(k) | k = number of existing children |
| `remove(Position<E> p)` | O(k) | k = number of children + siblings |
| `numChildren(Position<E> p)` | O(k) | k = number of children |
| `children(Position<E> p)` | O(k) | k = number of children |
| `size()` | O(1) | Maintained as instance variable |
| `depth(Position<E> p)` | O(d) | d = depth of node |
| `height(Position<E> p)` | O(n) | n = size of subtree |

## Requirements

- Java 8 or higher
- No external dependencies

## Installation

### Clone the Repository

```bash
git clone https://github.com/yourusername/ordered-general-tree.git
cd ordered-general-tree
```

### Compile

```bash
javac homework3/*.java
```

### Run

```bash
java homework3.Driver
```

## Project Features

### Node Representation

The tree uses a space-efficient representation:
- **Parent pointer**: Quick access to parent node
- **First child pointer**: Access to first child
- **Next sibling pointer**: Links siblings together

This structure allows:
- O(1) access to parent
- O(1) access to first child
- O(k) traversal of all k children

### Remove Operation Behavior

When removing a node:
1. **Root node**: Cannot be removed (returns null)
2. **Internal node**: Children are promoted to grandchildren
3. **Leaf node**: Simply removed from parent's child list

Example:
```
Before removing "China":        After removing "China":
Asia                           Asia
├── India                      ├── India
├── China                      ├── Beijing
│   ├── Beijing                ├── Shanghai
│   └── Shanghai               └── Japan
└── Japan
```

## Common Use Cases

- **File System Hierarchy**: Representing directories and files
- **Organizational Charts**: Company structure with departments
- **XML/HTML DOM**: Document object model representation
- **Game Trees**: Decision trees in game AI
- **Taxonomies**: Classification hierarchies

## Known Limitations

- Cannot remove the root node
- `addChild` operation is O(k) where k is number of existing children
- No balancing mechanism (height can grow arbitrarily)

## Future Enhancements

- [ ] Support for removing root with promotion of children
- [ ] Iterator for in-order traversal
- [ ] Serialization/deserialization support
- [ ] Clone operation
- [ ] Tree comparison and equality checking
- [ ] Export to DOT format for visualization

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## Acknowledgments

- Data structure design based on standard algorithms textbooks
- Implementation follows best practices for generic Java collections
- Tree traversal algorithms follow classic computer science approaches

---

Made with ☕ by [Your Name]
