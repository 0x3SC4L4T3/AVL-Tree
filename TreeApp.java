
public class TreeApp {

    public static void main(String[] args) {

        System.out.println("AVL Tree App");

        StudentTree myTree = new StudentTree();

        myTree.insert(new StudentNode("Sarah", 10));
        myTree.insert(new StudentNode("Bob", 5));
        myTree.insert(new StudentNode("Sam", 2));
        myTree.insert(new StudentNode("Joe", 7));
        myTree.insert(new StudentNode("a", 17));
        myTree.insert(new StudentNode("b", 12));
        myTree.insert(new StudentNode("c", 6));

        myTree.printInOrder();

    }

}
