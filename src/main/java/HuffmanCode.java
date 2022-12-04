import java.util.*;

public class HuffmanCode {
    private String inputString;
    private String codedString = "";
    private PriorityQueue<Node> priorityQueue;
    private HashMap<Character, String> hashMap;
    private List<Node> list;

    public HuffmanCode(){}
    public HuffmanCode(String string){
        this.inputString = string;
        priorityQueue = new PriorityQueue<>(string.length(), new Node.MyComparator());
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    public void buildTree() {
        fillQueue();
        Node root = null;
        while (priorityQueue.size() > 1) {
            Node x = priorityQueue.poll();
            Node y = priorityQueue.poll();
            if(x.getValue() != '-')
                list.add(x);
            if(y.getValue() != '-')
                list.add(y);
            Node f = new Node();

            f.setFreq(x.getFreq() + y.getFreq());
            f.setValue('-');
            f.setLeftChild(x);
            f.setRightChild(y);

            root = f;
            priorityQueue.add(f);
        }
        printCode(root, "");
    }

    public void encode(){
        for (int i = 0; i < inputString.length(); i++) {
            codedString = codedString + hashMap.get(inputString.charAt(i));
        }
    }

    public String decode() {
        StringBuilder decoded = new StringBuilder();
        StringBuilder cur = new StringBuilder();
        for (char c : codedString.toCharArray()) {
            cur.append(c);
            for(Node n : list) {
                if(n.getCode().equals(cur.toString())) {
                    decoded.append(n.getValue());
                    cur = new StringBuilder();
                }
            }
        }
        return String.valueOf(decoded);
    }

    private void printCode(Node root, String s) {
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            root.setCode(s);
            hashMap.put(root.getValue(), s);
            System.out.println(root.getValue() + " : " + s);
            return;
        }
        root.getLeftChild().setCode(s + "0");
        root.getRightChild().setCode(s + "1");
        printCode(root.getLeftChild(), root.getLeftChild().getCode());
        printCode(root.getRightChild(), root.getRightChild().getCode());
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
        for (int i = 0; i < inputString.length(); i++) {
            if(inputString.charAt(i) == symbol)
                count++;
        }
        return count;
    }

    public List<Node> getList() {
        return list;
    }

    public String getCodedString() {
        return codedString;
    }

    public HashMap<Character, String> getHashMap() {
        return hashMap;
    }
}
