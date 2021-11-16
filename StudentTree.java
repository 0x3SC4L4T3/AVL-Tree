
public class StudentTree {

    private StudentNode root;

    public StudentTree() {
        this.root = null;
    }

    int height(StudentNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.getHeight();
        }
    }

    int getBalance(StudentNode N) {
        if (N == null) {
            return 0;
        }

        return height(N.getLeft()) - height(N.getRight());
    }

    public void insert(StudentNode node) {
        root = insert(root, node);
    }

    public StudentNode insert(StudentNode pNode, StudentNode node) {
        if (pNode == null) {

            return node;
        }
        if (node.getId() < pNode.getId()) {
            pNode.setLeft(insert(pNode.getLeft(), node));
        } else if (node.getId() > pNode.getId()) {
            pNode.setRight(insert(pNode.getRight(), node));
        } else {
            return pNode;

        }

        pNode.setHeight(1 + max(height(pNode.getLeft()), height(pNode.getRight())));

        int balance = getBalance(pNode);

        if (balance > 1 && node.getId() < pNode.getLeft().getId()) {
            return rightRotate(pNode);
        }
        if (balance < -1 && node.getId() > pNode.getRight().getId()) {
            return leftRotate(pNode);
        }
        if (balance > 1 && node.getId() > pNode.getLeft().getId()) {
            pNode.setLeft(leftRotate(pNode.getLeft()));
            return rightRotate(pNode);
        }
        if (balance < -1 && node.getId() < pNode.getLeft().getId()) {
            pNode.setRight(rightRotate(pNode.getRight()));
            return leftRotate(pNode);
        }
        return pNode;

    }
    
    // Debugged //

    int max(int x, int y) {
       return (x > y) ? x : y; 
    }

    StudentNode rightRotate(StudentNode y) {
        StudentNode x = y.getLeft();
        StudentNode T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    StudentNode leftRotate(StudentNode x) {
        StudentNode y = x.getRight();
        StudentNode T2 = x.getLeft();

        
        // Debugged //
        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        // Update heights
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return y;
    }

    public void printPreOrder() {
        TraversePreOrder(getRoot());
    }

    public void printInOrder() {
        TraverseInOrder(getRoot());
    }

    public void printPostOrder() {
        TraversePostOrder(getRoot());
    }

    private void TraversePreOrder(StudentNode cursor) {    //Node, left subtree, right subtree

        if (cursor != null) {
            System.out.println(cursor);
            TraversePreOrder(cursor.getLeft());
            TraversePreOrder(cursor.getRight());
        }
    }

    private void TraverseInOrder(StudentNode cursor) {    //Nodes in ascending order

        if (cursor != null) {
            TraverseInOrder(cursor.getLeft());
            System.out.println(cursor);
            TraverseInOrder(cursor.getRight());
        }
    }

    private void TraversePostOrder(StudentNode cursor) {    //Left, right, node
        if (cursor != null) {

            TraversePostOrder(cursor.getLeft());
            TraversePostOrder(cursor.getRight());
            System.out.println(cursor);
        }
    }

    public StudentNode search(StudentNode node, int key) {

        if (node != null) {
            if (node.getId() == key) {
                return node;
            }
            search(node.getLeft(), key);
            search(node.getRight(), key);
        }
        return node;
    }

    StudentNode minValueNode(StudentNode node) {
        StudentNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public StudentNode deleteNode(StudentNode root, int key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null) {
            return root;
        }

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key < root.getId()) {
            root.setLeft(deleteNode(root.getLeft(), key));
        } // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (key > root.getId()) {
            root.setRight(deleteNode(root.getRight(), key));
        } // if key is same as root's key, then this is the node
        // to be deleted
        else {
            // node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                StudentNode temp = null;
                if (temp == root.getLeft()) {
                    temp = root.getRight();
                } else {
                    temp = root.getLeft();
                }
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                {
                    root = temp; // Copy the contents of
                }                                // the non-empty child
            } else {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                StudentNode temp = minValueNode(root.getRight());

                // Copy the inorder successor's data to this node
                root.setId(temp.getId());

                // Delete the inorder successor
                root.setRight(deleteNode(root.getRight(), temp.getId()));
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }

    public StudentNode getRoot() {
        return root;
    }

    public void setRoot(StudentNode root) {
        this.root = root;
    }
}
