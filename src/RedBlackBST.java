public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;

    private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private boolean color;
        private int size;

        public Node(Key key, Value value, int size, boolean color){
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    public boolean isEmpty(){
        return size(root) == 0;
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node == null)return 0;
        return node.size;
    }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node node, Key key){
        if(node == null) return null;

        int cmp = key.compareTo(node.key);
        if(cmp > 0) return get(node.right, key);
        else if(cmp < 0) return get(node.left, key);
        else return node.value;
    }

    public void insert(Key key, Value value){
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node node, Key key, Value value){
        if(node == null) return new Node(key, value, 1, RED);

        int cmp = key.compareTo(node.key);
        if(cmp < 0) node.left = insert(node.left, key, value);
        else if(cmp > 0) node.right = insert(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left))      node = rotateLeft(node);
        if (isRed(node.left)  &&  isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left)  &&  isRed(node.right))     flipColors(node);
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private void flipColors(Node node){
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = temp.left.color;
        temp.left.color = RED;
        temp.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;

        return temp;
    }

    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        temp.right.color = RED;
        temp.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;

        return temp;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
}
