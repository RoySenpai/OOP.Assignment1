package observer;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {
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
    void register() {
        GroupAdmin tester = new GroupAdmin();
        tester.register(new ConcreteMember("Zili"));
        tester.register(new ConcreteMember("Gili"));
        tester.register(new ConcreteMember("Tili"));
        tester.register(new ConcreteMember("Mili"));
        tester.append("123");
        assertEquals("Members:\nName: Zili; String: 123\nName: Gili; String: 123\nName: Tili; String: 123\nName: Mili; String: 123\n", tester.toString());
    }

    @Test
    void unregister() {
        GroupAdmin tester = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember("Zili");
        ConcreteMember m2 = new ConcreteMember("Zili");
        ConcreteMember m3 = new ConcreteMember("Gili");
        ConcreteMember m4 = new ConcreteMember("Tili");
        ConcreteMember m5 = new ConcreteMember("Mili");
        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);
        tester.register(m5);
        tester.append("123");
        assertEquals("Members:\nName: Zili; String: 123\nName: Zili; String: 123\nName: Gili; String: 123\nName: Tili; String: 123\nName: Mili; String: 123\n", tester.toString());

        tester.unregister(m1);
        tester.unregister(m2);
        tester.unregister(m3);
        tester.unregister(m4);
        tester.unregister(m5);
        assertEquals("Members:\n", tester.toString());

        tester.unregister(m1);
        tester.unregister(m2);
        tester.unregister(m3);
        tester.unregister(m4);
        tester.unregister(m5);
        assertEquals("Members:\n", tester.toString());
    }

    @Test
    void insert() {
        GroupAdmin tester = new GroupAdmin();
        tester.register(new ConcreteMember("Zili"));
        tester.register(new ConcreteMember("Gili"));
        tester.register(new ConcreteMember("Tili"));
        tester.register(new ConcreteMember("Mili"));

        // Normal strings
        tester.insert(0,"test");
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        tester.insert(3,"hello");
        assertEquals("Members:\nName: Zili; String: teshellot\nName: Gili; String: teshellot\nName: Tili; String: teshellot\nName: Mili; String: teshellot\n", tester.toString());
        // Special characters
        tester.insert(9,emptystring);
        assertEquals("Members:\nName: Zili; String: teshellot\nName: Gili; String: teshellot\nName: Tili; String: teshellot\nName: Mili; String: teshellot\n", tester.toString());
        tester.insert(9,newline);
        assertEquals("Members:\nName: Zili; String: teshellot\n\nName: Gili; String: teshellot\n\nName: Tili; String: teshellot\n\nName: Mili; String: teshellot\n\n", tester.toString());
        tester.insert(10,nullstring);
        assertEquals("Members:\nName: Zili; String: teshellot\nnull\nName: Gili; String: teshellot\nnull\nName: Tili; String: teshellot\nnull\nName: Mili; String: teshellot\nnull\n", tester.toString());

        // Offset cases
        tester.insert(-1,"outofbounds");
        assertEquals("Members:\nName: Zili; String: teshellot\nnull\nName: Gili; String: teshellot\nnull\nName: Tili; String: teshellot\nnull\nName: Mili; String: teshellot\nnull\n", tester.toString());
    }

    @Test
    void append() {
        GroupAdmin tester = new GroupAdmin();
        tester.register(new ConcreteMember("Zili"));
        tester.register(new ConcreteMember("Gili"));
        tester.register(new ConcreteMember("Tili"));
        tester.register(new ConcreteMember("Mili"));

        // Normal strings
        tester.append("test");
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        tester.append("hello");
        assertEquals("Members:\nName: Zili; String: testhello\nName: Gili; String: testhello\nName: Tili; String: testhello\nName: Mili; String: testhello\n", tester.toString());

        // Special characters append
        tester.append(emptystring);
        assertEquals("Members:\nName: Zili; String: testhello\nName: Gili; String: testhello\nName: Tili; String: testhello\nName: Mili; String: testhello\n", tester.toString());
        tester.append(newline);
        assertEquals("Members:\nName: Zili; String: testhello\n\nName: Gili; String: testhello\n\nName: Tili; String: testhello\n\nName: Mili; String: testhello\n\n", tester.toString());
        tester.append(nullstring);
        assertEquals("Members:\nName: Zili; String: testhello\nnull\nName: Gili; String: testhello\nnull\nName: Tili; String: testhello\nnull\nName: Mili; String: testhello\nnull\n", tester.toString());
    }

    @Test
    void delete() {
        GroupAdmin tester = new GroupAdmin();
        tester.register(new ConcreteMember("Zili"));
        tester.register(new ConcreteMember("Gili"));
        tester.register(new ConcreteMember("Tili"));
        tester.register(new ConcreteMember("Mili"));

        // Offset cases
        tester.delete(0,1);
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());
        tester.delete(5,-1);
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());

        // Normal strings
        tester.append("test");
        tester.append("this is a test!");
        tester.delete(0,5);
        assertEquals("Members:\nName: Zili; String: his is a test!\nName: Gili; String: his is a test!\nName: Tili; String: his is a test!\nName: Mili; String: his is a test!\n", tester.toString());
        tester.delete(2,3);
        assertEquals("Members:\nName: Zili; String: hi is a test!\nName: Gili; String: hi is a test!\nName: Tili; String: hi is a test!\nName: Mili; String: hi is a test!\n", tester.toString());
    }

    @Test
    void undo() {
        GroupAdmin tester = new GroupAdmin();
        tester.register(new ConcreteMember("Zili"));
        tester.register(new ConcreteMember("Gili"));
        tester.register(new ConcreteMember("Tili"));
        tester.register(new ConcreteMember("Mili"));

        tester.append("test");
        tester.undo();
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());
        tester.undo();
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());

        tester.insert(0,"test");
        tester.insert(4,"test2");
        tester.undo();
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        tester.delete(0,4);
        tester.undo();
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
    }
}