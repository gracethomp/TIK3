public class Main {
    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode("Babii Olena Olexiivna 17.05.2003 Khmelnyzkyi");
        huffmanCode.buildTree();
        System.out.println("\n");
        huffmanCode.encode();
        System.out.println(huffmanCode.getCodedString());
        System.out.println(huffmanCode.decode());
    }
}
