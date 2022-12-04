import java.util.Comparator;
import java.util.Objects;

public class Node {
    private char value;
    private int freq;
    private String code;
    private Node leftChild;
    private Node rightChild;

    public Node(){}
    public Node(char value, int weight, Node leftChild, Node rightChild) {
        this.value = value;
        this.freq = weight;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getFreq() {
        return freq;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public char getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, freq, leftChild, rightChild);
    }

    @Override
    public String toString() {
        return "Node " +
                "value '" + value + '\'' +
                ", frequency " + freq +
                ", leftChild " + leftChild +
                ", rightChild " + rightChild + '\n';
    }

    static class MyComparator implements Comparator<Node> {
        public int compare(Node x, Node y) {
            return x.freq - y.freq;
        }
    }
}
