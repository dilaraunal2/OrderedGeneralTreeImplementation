
package homework3;

public class Driver {
/**
 * Driver program to test the functionality of the OrderedGeneralTree class
 * with a continent and country hierarchy.
 * 
 * This program creates a tree structure with continents as the root,
 * countries as children, and cities/regions as grandchildren. It also
 * demonstrates adding, removing, and displaying nodes in the tree.
 */

    public static void main(String[] args) {
         // Create an instance of the OrderedGeneralTree to store String elements (continent and country names)
        OrderedGeneralTree<String> tree = new OrderedGeneralTree<>();

        // 1. Test adding a root node (a continent)
        System.out.println("1. Adding root node (Continent):");
        Position<String> asia = tree.addRoot("Asia");
        tree.displayTree();  // Should display only Asia

        // 2. Test adding countries to the continent 'Asia'
        System.out.println("\n2. Adding countries to 'Asia':");
        Position<String> india = tree.addChild(asia, "India");
        Position<String> china = tree.addChild(asia, "China");
        Position<String> japan = tree.addChild(asia, "Japan");
        tree.displayTree();  // Should display Asia with 3 countries

        // 3. Test adding cities as countries' children
        System.out.println("\n3. Adding cities to 'India' and 'China':");
        tree.addChild(india, "New Delhi");
        tree.addChild(china, "Beijing");
        tree.addChild(china, "Shanghai");
        tree.displayTree();  // Should display 'India' with city 'New Delhi', and 'China' with cities

        // 4. Test getting the number of children of a continent
        System.out.println("\n4. Number of countries in 'Asia': " + tree.numChildren(asia));  // Should be 3
       
        // 5. Test getting all countries of the continent
        System.out.println("\n5. Countries in 'Asia':");
        for (Position<String> p : tree.children(asia)) {
            System.out.println(p.getElement());
        }

        // 6. Test removing a city (leaf node)
        System.out.println("\n6. Removing 'Beijing' from the tree:");
        Position<String> beijing = tree.children(china).iterator().next();
        System.out.println("Removed node: " + beijing.getElement());
        tree.remove(beijing);  // Removing 'Beijing'
        tree.displayTree();  // Should display the tree without 'Beijing'

        // 7. Test removing a country (internal node)
        System.out.println("\n7. Removing 'China' from the tree:");
        System.out.println("Removed node: " + china.getElement());
        tree.remove(china);  // Removing 'China' and its children
        tree.displayTree();  // Should display the tree without 'China'

        // 8. Test removing the root node (Continent)
        System.out.println("\n8. Trying to remove the root node (Asia):");
        try {
            tree.remove(asia);  // Should not allow removal of the root
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}


