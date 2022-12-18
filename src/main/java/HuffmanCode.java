import java.util.*;

public class HuffmanCode {

    private String inputString;
    private final StringBuilder codedString = new StringBuilder();
    private PriorityQueue<Node> priorityQueue;
    private HashMap<Character, String> hashMap;
    //private List<Node> list;
    private Node root = new Node();

    public HuffmanCode(){}
    public HuffmanCode(String string){
        this.inputString = string;
        priorityQueue = new PriorityQueue<>(string.length(), new Node.MyComparator());
        hashMap = new HashMap<>();
    }

    public void buildTree() {
        fillQueue();
        while (priorityQueue.size() > 1) {
            Node x = priorityQueue.poll();
            Node y = priorityQueue.poll();
            Node f = new Node();
            assert y != null;
            f.setFreq(x.getFreq() + y.getFreq());
            f.setValue('-');
            f.setLeftChild(x);
            f.setRightChild(y);
            root = f;
            priorityQueue.add(f);
        }

        setCodeToNodes(root, "");
    }

    public void encode(){
        for (int i = 0; i < inputString.length(); i++) {
            codedString.append(hashMap.get(inputString.charAt(i)));
        }
    }

    public String decode() {
        StringBuilder decoded = new StringBuilder();
        Node node = priorityQueue.poll();
        Node node1 = node;
        int i = 0;
        for (char c : codedString.toString().toCharArray()) {
            assert node != null;
            if(node.getValue() != '-') {
                decoded.append(node.getValue());
                node = node1;
            }
            if(c == '0')
                node = node.getLeftChild();
            if(c == '1')
                node = node.getRightChild();
            i++;
            if(i == codedString.toString().length())
                decoded.append(node.getValue());
        }
        return String.valueOf(decoded);
    }
    private static HashMap<Character, Double> getEntropyByLetter(String input) {
        HashMap<Character, Double> symbols = new HashMap<>();
        for (Map.Entry<Character, Double> e : getEachSymbol(input).entrySet()) {
            symbols.put(e.getKey(), - (Math.log(e.getValue())/Math.log(2))*e.getValue());
        }
        return symbols;
    }

    public static double getEntropy(String input) {
        return getEntropyByLetter(input).values().stream()
                .mapToDouble(Double::doubleValue).sum();
    }
    private static HashMap<Character, Double> getEachSymbol(String string) {
        HashMap<Character, Double> symbols = new HashMap<>();
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (symbols.containsKey(chars[i])) {
                continue;
            }
            final int j = i;
            symbols.put(chars[j], (double) (string.chars()
                    .filter(e -> e == chars[j]).count())/string.length());
        }
        return symbols;
    }
    public double compressionRatio() {
        return (double) (inputString.length() * 8)/codedString.length();
    }

    private void setCodeToNodes(Node root, String s) {
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            root.setCode(s);
            hashMap.put(root.getValue(), s);
            //System.out.println(root.getValue() + " : " + s);
            return;
        }
        root.getLeftChild().setCode(s + "0");
        root.getRightChild().setCode(s + "1");
        setCodeToNodes(root.getLeftChild(), root.getLeftChild().getCode());
        setCodeToNodes(root.getRightChild(), root.getRightChild().getCode());
    }

    private void fillQueue() {
        Set<Character> characterSet = new HashSet<>();
        for(int i = 0; i < inputString.length(); i++)
            characterSet.add(inputString.charAt(i));
        for (Character c : characterSet) {
            Node node = new Node();
            node.setValue(c);
            node.setFreq(findCount(c, inputString));
            node.setLeftChild(null);
            node.setRightChild(null);
            priorityQueue.add(node);
        }
    }

    private int findCount(char symbol, String inputString) {
        int count = 0;
        for (int i = 0; i < inputString.length(); i++)
            if(inputString.charAt(i) == symbol)
                count++;
        return count;
    }

    public StringBuilder getCodedString() {
        return codedString;
    }
}
