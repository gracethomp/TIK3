import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HuffmanCode {
    private String string;
    private PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Node.MyComparator());

    public HuffmanCode(){}
    public HuffmanCode(String string){
        this.string = string;
    }

    public void fillQueue() {
        Set<Character> characterSet = new HashSet<>();
        for(int i = 0; i < string.length(); i++)
            characterSet.add(string.charAt(i));
        for (Character c : characterSet) {
            Node node = new Node();
            node.setValue(c);
            node.setFreq(findCount(c, string));
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
}
