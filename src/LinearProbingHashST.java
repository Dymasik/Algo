import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LinearProbingHashST<Key, Value> {
    private static final int MIN_CAPACITY = 4;

    private Key[] keys;
    private Value[] values;
    private int n;
    private int m;

    public LinearProbingHashST(){
        this(MIN_CAPACITY);
    }

    public LinearProbingHashST(int capacity){
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    private int getHash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity){
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for(int i = 0; i < m; i++){
            if(keys[i] != null)
                temp.insert(keys[i], values[i]);
        }
        keys = temp.keys;
        values = temp.values;
        m = temp.m;
    }

    public void insert(Key key, Value value){
        if(n >= m / 2)
            resize(m * 2);

        int i;
        for(i = getHash(key); keys[i] != null; i++){
            if(keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key){
        int i;
        for(i = getHash(key); keys[i] != null; i++){
            if(key.equals(keys[i]))
                return values[i];
        }
        return null;
    }

    public void delete(Key key){
        int i;
        for(i = getHash(key); !keys[i].equals(key) || keys[i] != null; i++){

        }
        if(keys[i] == null)
            return;
        keys[i] = null;
        values[i] = null;
        for(++i; keys[i] != null; i++){
            Key newKey = keys[i];
            Value newValue = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            insert(newKey, newValue);
        }
        n--;

        if(n > 0 && n <= m / 8)
            resize(m / 2);
    }

    public Iterable<Key> keys() {
        Stack<Key> stack = new Stack<>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) stack.push(keys[i]);
        return stack;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        String current = scanner.next();
        for (int i = 0; !current.equals("end"); i++) {
            st.insert(current, i);
            current = scanner.next();
        }

        // print keys
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}
