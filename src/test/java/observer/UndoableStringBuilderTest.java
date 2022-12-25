package observer;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UndoableStringBuilderTest {
    UndoableStringBuilder tester;

    // Special cases
    final String nullstring = null;
    final String emptystring = "";
    final String newline = "\n";

    @BeforeAll
    public static void runOnceBefore() {
        System.out.println("Starting testes...");
    }

    @AfterAll
    public static void runOnceAfter() {
        System.out.println("\nTestes ended!");
    }

    @BeforeEach
    public void runBeforeEach() {
        System.out.println("Running new test..");
    }

    @AfterEach
    public void runAfterEach() {
        System.out.println("Finished test.");
    }

    @Test
    void undo() {
        tester = new UndoableStringBuilder();

        tester.append("test");
        tester.undo();
        assertEquals("",tester.toString());
        tester.undo();
        assertEquals("",tester.toString());

        tester.insert(0,"test");
        tester.insert(4,"test2");
        tester.undo();
        assertEquals("test",tester.toString());
        tester.delete(0,4);
        tester.undo();
        assertEquals("test",tester.toString());

        tester.replace(0,1,"0");
        tester.undo();
        assertEquals("test", tester.toString());

        tester.reverse();
        tester.undo();
        assertEquals("test", tester.toString());
    }

    @Test
    void append() {
        tester = new UndoableStringBuilder();

        // Normal strings
        tester.append("test");
        assertEquals("test",tester.toString());
        tester.append("hello");
        assertEquals("testhello",tester.toString());

        // Special characters append
        tester.append(emptystring);
        assertEquals("testhello",tester.toString());
        tester.append(newline);
        assertEquals("testhello\n",tester.toString());
        tester.append(nullstring);
        assertEquals("testhello\nnull",tester.toString());
    }

    @Test
    void delete() {
        tester = new UndoableStringBuilder();

        // Offset cases
        tester.delete(0,1);
        assertEquals("",tester.toString());
        tester.delete(5,-1);
        assertEquals("",tester.toString());

        // Normal strings
        tester.append("test");
        tester.append("this is a test!");
        tester.delete(0,5);
        assertEquals("his is a test!",tester.toString());
        tester.delete(2,3);
        assertEquals("hi is a test!",tester.toString());
    }

    @Test
    void insert() {
        tester = new UndoableStringBuilder();

        // Normal strings
        tester.insert(0,"test");
        assertEquals("test",tester.toString());
        tester.insert(3,"hello");
        assertEquals("teshellot",tester.toString());

        // Special characters
        tester.insert(9,emptystring);
        assertEquals("teshellot",tester.toString());
        tester.insert(9,newline);
        assertEquals("teshellot\n",tester.toString());
        tester.insert(10,nullstring);
        assertEquals("teshellot\nnull",tester.toString());

        // Offset cases
        tester.insert(-1,"outofbounds");
        assertEquals("teshellot\nnull",tester.toString());
        tester.insert(tester.toString().length() + 1000,"tester");
        assertEquals("teshellot\nnull",tester.toString());
    }

    @Test
    void replace() {
        tester = new UndoableStringBuilder();

        tester.append("This is some good stuff!");

        // Offset cases
        tester.replace(-2,0,"haha!");
        assertEquals("This is some good stuff!",tester.toString());
        tester.replace(5,3,"haha!");
        assertEquals("This is some good stuff!",tester.toString());

        // Normal strings
        tester.replace(0,5,"haha!");
        assertEquals("haha!is some good stuff!",tester.toString());

        // Special cases
        tester.replace(0,1,nullstring);
        assertEquals("haha!is some good stuff!",tester.toString());
        tester.replace(0,2,emptystring);
        assertEquals("ha!is some good stuff!",tester.toString());
        tester.replace(0,1,"\n");
        assertEquals("\na!is some good stuff!",tester.toString());
    }

    @Test
    void reverse() {
        tester = new UndoableStringBuilder();

        // Empty string
        tester.reverse();
        assertEquals("",tester.toString());

        // Normal strings
        tester.append("test");
        tester.reverse();
        assertEquals("tset",tester.toString());
        tester.append("hello");
        tester.reverse();
        assertEquals("ollehtest",tester.toString());
    }
}