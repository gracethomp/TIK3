import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        try {
            Menu.showMenu();
        } catch (IOException e) {
            System.err.println("here should be logger");
        }
    }
}
