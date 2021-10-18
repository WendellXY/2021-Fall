import java.io.*;
import java.util.*;

class MyHashSet<E extends Comparable<E>> {
    private Set<E> set = new HashSet<>();

    MyHashSet(E[] objects) {
        this.set = new HashSet<>(Arrays.asList(objects));
    }

    boolean contains(E element) {
        return set.contains(element);
    }

    Object[] elements() {
        return set.toArray();
    }
}

class MyHashMap<E extends Comparable<E>, F extends Comparable<F>> {
    private Map<E, F> map = new HashMap<>();

    boolean containsKey(E key) {
        return map.containsKey((Object) key);
    }

    boolean containsValue(F value) {
        return map.containsValue(value);
    }

    void updateKeyValuePair(E key, F value) {
        map.replace(key, value);
    }

    void addKeyValuePair(E key, F value) {
        map.put(key, value);
    }

    F get(E key) {
        return map.get(key);
    }

    Set<Map.Entry<E, F>> entrySet() {
        return this.map.entrySet();
    }
}

class WordCounter {
    private MyHashMap<String, Integer> map;

    private static MyHashSet<Character> ignoredCharacters = new MyHashSet<>(toCharacterArray(";.,{}()/ "));

    private static Character[] toCharacterArray(String s) {
        if ( s == null )
          return null;

        int len = s.length();
        Character[] array = new Character[len];
        for (int i = 0; i < len ; i++)
           array[i] = s.charAt(i);

        return array;
     }

    private static String stringFormat(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            final char currentCharacter = str.charAt(i);

            if (!ignoredCharacters.contains(currentCharacter)) {
                stringBuilder.append(currentCharacter);
            } else {
                stringBuilder.append(" ");
            }
        }

        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

    WordCounter(String str) {
        MyHashMap<String, Integer> tMap = new MyHashMap<>();

        String[] data = stringFormat(str).split(" ");

        for (String element: data)
            if (tMap.containsKey(element))
                tMap.updateKeyValuePair(element, map.get(element) + 1);
            else
                tMap.addKeyValuePair(element, 1);

        this.map = tMap;
    }

    WordCounter(MyHashSet<String> preset) {
        this.map = new MyHashMap<>();

        for (Object element: preset.elements())
            this.map.addKeyValuePair((String) element, 0);
    }

    public MyHashMap<String, Integer> getMap() {
        return map;
    }

    public void process(String str) {
        String[] data = stringFormat(str).split(" ");

        for (String element: data)
            if (map.containsKey(element))
                map.updateKeyValuePair(element, map.get(element) + 1);
    }

    public void processFromFileOnPath(String path) {
        try {
            FileReader file = new FileReader(path);

            Scanner input = new Scanner(file);

            StringBuilder stringBuilder = new StringBuilder();

            while (input.hasNextLine())
                stringBuilder.append(input.nextLine());

            input.close();

            this.process(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printInTerminal() {
        System.out.println();

        for (Map.Entry<String, Integer> entry : this.map.entrySet()) {
            System.out.printf("%s: %d; ", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}

public class Playground211008 {
    public static void main(String[] args) {
        String string = "Hello World, \"this\" is Wendell.";

        WordCounter counter = new WordCounter(string);

        counter.printInTerminal();

        final String[] JavaKeywords = {
            "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"
        };

        MyHashSet<String> myHashedSet = new MyHashSet<>(JavaKeywords);

        WordCounter keywordCounter = new WordCounter(myHashedSet);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file name or path: ");

        String currentFilePath = scanner.nextLine();
        scanner.close();

        keywordCounter.processFromFileOnPath(currentFilePath);

        keywordCounter.printInTerminal();
    }
}