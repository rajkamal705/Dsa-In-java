public class BST {
    public static void main(String[] args) {
        int values[] = {5, 1, 3, 4, 2, 7};
        Node<Integer> root = null;
        
        for(int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        inOrder(root);
        System.out.println();

        // if(search(root, 1)) {
        //     System.out.println("found");
        // } else {
        //     System.out.println("not found");
        // }

        // Node<Integer> newRoot = delete(root, 5);
        // inOrder(newRoot);

        printInRange(root, 1, 4);
    }

    public static void printInRange(Node<Integer> root, int x, int y) {
        if(root == null) {
            return;
        }

        if(x <= root.data && y >= root.data) {
            printInRange(root.left, x, y);
            System.out.print(root.data + " ");
            printInRange(root.right, x, y);
        } else if(x >= root.data) {
            printInRange(root.right, x, y);
        } else {
            printInRange(root.left, x, y);
        }
    }

    // delete a node in bst :

    public static Node<Integer> delete(Node<Integer> root, int val) {

        // part 1:
        if(val < root.data) {
            root.left = delete(root.left, val);
        } else if(val > root.data) {
            root.right = delete(root.right, val);
        } else {
            if(root.left == null && root.right == null) {
                return null;
            } else if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left; 
            } else {
                Node<Integer> Is = inorderSuccessor(root.right);
                root.data = Is.data;
                root.right = delete(root.right, Is.data);
            }
        }

        return root;


    }

    public static Node<Integer> inorderSuccessor(Node<Integer> root) {
        while(root.left != null) {
            root = root.left;
        }

        return root;
    }

    // search in bst: 
    public static boolean search(Node<Integer> root, int key) {
        if(root == null) {
            return false;
        }

        if(root.data == key) {
            return true;
        } else if(key < root.data) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    // inorder : 

    public static void inOrder(Node<Integer> root) {
        if(root == null) {
            return;
        }

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    // build bst : 

    public static Node<Integer> insert(Node<Integer> root, int val) {
        if(root == null) {
            root = new Node<>(val);
            return root;
        }

        if(val < root.data) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }
}

class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
