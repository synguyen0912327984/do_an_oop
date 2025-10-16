import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Booklist bl = new Booklist();
        bl.readFile();
        bl.OutputList();
    }
}
