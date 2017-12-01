import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class FileAnalizerTests {

    private final String smallFile;
    private final String largeFile;
    private final String imageFile;

    private FileAnalizer smallAnalizer;

    public FileAnalizerTests() {

        this.smallFile = "C:\\Alex\\Working\\Java\\Projects\\Lab4.FileAnalyzer\\Tests\\small-file.txt";
        this.largeFile = "C:\\Alex\\Working\\Java\\Projects\\Lab4.FileAnalyzer\\Tests\\large-file.txt";
        this.imageFile = "C:\\Alex\\Working\\Java\\Projects\\Lab4.FileAnalyzer\\Tests\\image-file.png";
    }

    @Test
    public void checkContructor() {

        FileAnalizer small = new FileAnalizer(smallFile);
        Assertions.assertEquals(smallFile, small.getPath());

        FileAnalizer large = new FileAnalizer(largeFile);
        Assertions.assertEquals(largeFile, large.getPath());

        try {
            FileAnalizer image = new FileAnalizer(imageFile);
            Assertions.fail("Init FileAnalizer for image file.");
        }
        catch (IllegalArgumentException exception) {}
    }

    @BeforeEach
    public void init() {
        smallAnalizer = new FileAnalizer(smallFile);
    }

    @Test
    public void checkMethods() {

        Assertions.assertEquals(22, smallAnalizer.countLetters());
        Assertions.assertEquals(19, smallAnalizer.countWords());
        Assertions.assertEquals(11, smallAnalizer.countLines());

        HashMap<Character, Integer> statistic = new HashMap<Character, Integer>();
        statistic.put('1', 9);
        statistic.put('2', 6);
        statistic.put('3', 5);
        statistic.put('4', 3);
        statistic.put('5', 1);
        statistic.put('a', 2);
        statistic.put('d', 6);
        statistic.put('s', 7);
        statistic.put('e', 3);
        statistic.put('y', 4);
        statistic.put('\n', 11);
        statistic.put(' ', 11);

        Assertions.assertEquals(statistic, smallAnalizer.countFrequencyCharacteristic());
    }

    @Test
    public void usingLargeFile() {

        for(int i = 0; i < 100; i++) {

            Assertions.assertNotEquals(-1, new FileAnalizer(largeFile).countLines());
        }
    }
    @Test
    public void checkCaching(){

        System.out.println(System.getProperty("user.dir"));
        String path = "../test.txt";
        clearFile(path);
        for(int i = 0; i < 10000; i++) {

            if (0 == i % 5){
                clearFile(path);
            }
            else {
                setOneLine(path);
            }

            Assertions.assertEquals(i % 5,  new FileAnalizer(path).countLines());
        }
    }

    private void clearFile(String path) {
        setContent(path, "", false);
    }
    private void setOneLine(String path) {
        setContent(path, "One line\n", true);
    }
    private void setContent(String path, String content, boolean append) {

        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file, append);
            writer.write(content);
            writer.close();
        }
        catch (Exception exception) {}
    }
}