import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Menu {
    private static final String FIRST_CHOICE = "File (1) or console (2)? (write number)";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String TRY_AGAIN = "Try again";
    private static final String ENTER_STRING = "Enter your string: ";
    private static final String PNG = "src/main/resources/woman.png";
    private static final String TXT = "src/main/resources/text.txt";
    private static final String MP4 = "src/main/resources/котик.mp4";
    private static final String EXE = "src/main/resources/pip3.9.exe";

    public static void showMenu() throws IOException {
        System.out.println(FIRST_CHOICE);
        Scanner sc = new Scanner(System.in);
        String string;
        while (true) {
            String number = sc.nextLine();
            if(number.equals(TWO)) {
                System.out.print(ENTER_STRING);
                string = sc.nextLine();
                getResults(string, true);
                break;
            } else if(number.equals(ONE)) {
                System.out.println("Choose format (1 - PNG, 2 - EXE, 3 - MP4, 4 - TEXT): ");
                String string1 = chooseFile();
                getResults(new String(readFile(string1)), false);
                break;
            } else
                System.out.println(TRY_AGAIN);
        }
    }

    private static String chooseFile(){
        String string = "";
        boolean isEnd2 = false;
        Scanner sc = new Scanner(System.in);
        while (!isEnd2) {
            String variant = sc.nextLine();
            switch (variant) {
                case ONE ->{
                    string = PNG;
                    isEnd2 = true;
                }
                case TWO -> {
                    string = EXE;
                    isEnd2 = true;
                }
                case THREE ->{
                    string = MP4;
                    isEnd2 = true;
                }
                case FOUR -> {
                    string = TXT;
                    isEnd2 = true;
                }
                default -> System.out.println(TRY_AGAIN);
            }
        }
        return string;
    }
    private static void getResults(String string, boolean isConsole) {
        HuffmanCode huffmanCode = new HuffmanCode(string);
        huffmanCode.buildTree();
        huffmanCode.encode();
        if(isConsole) {
            System.out.println(huffmanCode.getCodedString());
            System.out.println(huffmanCode.decode());
        }
        System.out.println(huffmanCode.compressionRatio());
    }

    private static byte[] readFile(String file) {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Path.of(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}
