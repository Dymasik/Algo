public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node{
        private Node left;
        private Node right;
        private Key key;
        private Value value;
        private int size;

        public Node(Key key, Value value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BST() {
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

    public void insert(Key key, Value value){
        root = insert(root, key, value);
    }

    private Node insert(Node node, Key key, Value value){
        if(node == null)return new Node(key, value, 1);

        int cmp = key.compareTo(node.key);
        if(cmp < 0) node.left = insert(node.left, key, value);
        else if(cmp > 0) node.right = insert(node.right, key, value);
        else node.value = value;

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node node, Key key){
        if(node == null)return null;
        if(key == node.key)return node.value;

        int cmp = key.compareTo(node.key);
        if(cmp > 0)return get(node.right, key);
        else return get(node.left, key);
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public static void main(String[] args){
        BST<String, Integer> tree = new BST<>();
        tree.insert("lol", 5);
        tree.insert("kek", 3);
        System.out.println(tree.contains("kek"));
        System.out.println(tree.get("lol"));
    }
}
