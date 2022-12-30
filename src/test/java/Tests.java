import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.JvmUtilities;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    static GroupAdmin tester;
    static ConcreteMember m1, m2, m3, m4;

    static final String nullstring = null, emptystring = "", newline = "\n";

    void printInfo() {
        System.out.println("Objects footprint:");
        System.out.println(JvmUtilities.objectFootprint(tester, m1, m2, m3, m4));

        System.out.println("Objects memory:");
        System.out.println("(*) GroupAdmin:");
        System.out.println(JvmUtilities.objectTotalSize(tester));

        System.out.println("(*) ConcreteMembers #1-4:");
        System.out.println(JvmUtilities.objectTotalSize(m1,m2,m3,m4));
    }

    @BeforeAll
    public static void runOnceBefore() {
        System.out.println("Starting testes...");

        System.out.println("JVM Info:");
        logger.info(JvmUtilities::jvmInfo);
    }

    @AfterAll
    public static void runOnceAfter() {
        System.out.println("\nTestes ended!");
    }

    @BeforeEach
    public void runBeforeEach() {
        System.out.println("----------------------------------------");
        System.out.println("Running new test...");

        System.out.println("Creating objects ...");

        tester = new GroupAdmin();

        m1 = new ConcreteMember("Zili");
        m2 = new ConcreteMember("Gili");
        m3 = new ConcreteMember("Tili");
        m4 = new ConcreteMember("Mili");

        printInfo();

        System.out.println("\nTesting function:");
    }

    @AfterEach
    public void runAfterEach() {
        System.out.println("Finished test.\n");
    }

    @Test
    void register() {
        System.out.println("GroupAdmin: Register");

        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);

        printInfo();

        tester.append("123");
        assertEquals("Members:\nName: Zili; String: 123\nName: Gili; String: 123\nName: Tili; String: 123\nName: Mili; String: 123\n", tester.toString());

        printInfo();
    }

    @Test
    void unregister() {
        System.out.println("GroupAdmin: Unregister");

        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);

        tester.append("123");
        assertEquals("Members:\nName: Zili; String: 123\nName: Gili; String: 123\nName: Tili; String: 123\nName: Mili; String: 123\n", tester.toString());
        printInfo();

        tester.unregister(m1);
        tester.unregister(m2);
        tester.unregister(m3);
        tester.unregister(m4);
        assertEquals("Members:\n", tester.toString());
        printInfo();

        tester.unregister(m1);
        tester.unregister(m2);
        tester.unregister(m3);
        tester.unregister(m4);
        assertEquals("Members:\n", tester.toString());
        printInfo();
    }

    @Test
    void insert() {
        System.out.println("GroupAdmin: Insert");

        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);

        // Normal strings
        tester.insert(0,"test");
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        printInfo();

        tester.insert(3,"hello");
        assertEquals("Members:\nName: Zili; String: teshellot\nName: Gili; String: teshellot\nName: Tili; String: teshellot\nName: Mili; String: teshellot\n", tester.toString());
        printInfo();

        // Special characters
        tester.insert(9,emptystring);
        assertEquals("Members:\nName: Zili; String: teshellot\nName: Gili; String: teshellot\nName: Tili; String: teshellot\nName: Mili; String: teshellot\n", tester.toString());
        printInfo();

        tester.insert(9,newline);
        assertEquals("Members:\nName: Zili; String: teshellot\n\nName: Gili; String: teshellot\n\nName: Tili; String: teshellot\n\nName: Mili; String: teshellot\n\n", tester.toString());
        printInfo();

        tester.insert(10,nullstring);
        assertEquals("Members:\nName: Zili; String: teshellot\nnull\nName: Gili; String: teshellot\nnull\nName: Tili; String: teshellot\nnull\nName: Mili; String: teshellot\nnull\n", tester.toString());
        printInfo();

        // Offset cases
        tester.insert(-1,"outofbounds");
        assertEquals("Members:\nName: Zili; String: teshellot\nnull\nName: Gili; String: teshellot\nnull\nName: Tili; String: teshellot\nnull\nName: Mili; String: teshellot\nnull\n", tester.toString());
        printInfo();
    }

    @Test
    void append() {
        System.out.println("GroupAdmin: Append");

        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);

        // Normal strings
        tester.append("test");
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        printInfo();

        tester.append("hello");
        assertEquals("Members:\nName: Zili; String: testhello\nName: Gili; String: testhello\nName: Tili; String: testhello\nName: Mili; String: testhello\n", tester.toString());
        printInfo();

        // Special characters append
        tester.append(emptystring);
        assertEquals("Members:\nName: Zili; String: testhello\nName: Gili; String: testhello\nName: Tili; String: testhello\nName: Mili; String: testhello\n", tester.toString());
        printInfo();

        tester.append(newline);
        assertEquals("Members:\nName: Zili; String: testhello\n\nName: Gili; String: testhello\n\nName: Tili; String: testhello\n\nName: Mili; String: testhello\n\n", tester.toString());
        printInfo();

        tester.append(nullstring);
        assertEquals("Members:\nName: Zili; String: testhello\nnull\nName: Gili; String: testhello\nnull\nName: Tili; String: testhello\nnull\nName: Mili; String: testhello\nnull\n", tester.toString());
        printInfo();
    }

    @Test
    void delete() {
        System.out.println("GroupAdmin: Delete");

        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);

        // Offset cases
        tester.delete(0,1);
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());
        printInfo();

        tester.delete(5,-1);
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());
        printInfo();

        // Normal strings
        tester.append("test");
        tester.append("this is a test!");
        tester.delete(0,5);
        assertEquals("Members:\nName: Zili; String: his is a test!\nName: Gili; String: his is a test!\nName: Tili; String: his is a test!\nName: Mili; String: his is a test!\n", tester.toString());
        printInfo();

        tester.delete(2,3);
        assertEquals("Members:\nName: Zili; String: hi is a test!\nName: Gili; String: hi is a test!\nName: Tili; String: hi is a test!\nName: Mili; String: hi is a test!\n", tester.toString());
        printInfo();
    }

    @Test
    void undo() {
        System.out.println("GroupAdmin: Undo");

        tester.register(m1);
        tester.register(m2);
        tester.register(m3);
        tester.register(m4);

        tester.append("test");
        tester.undo();
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());
        printInfo();

        tester.undo();
        assertEquals("Members:\nName: Zili; String: \nName: Gili; String: \nName: Tili; String: \nName: Mili; String: \n", tester.toString());
        printInfo();

        tester.insert(0,"test");
        tester.insert(4,"test2");
        tester.undo();
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        printInfo();

        tester.delete(0,4);
        tester.undo();
        assertEquals("Members:\nName: Zili; String: test\nName: Gili; String: test\nName: Tili; String: test\nName: Mili; String: test\n", tester.toString());
        printInfo();
    }

    @Test
    void update() {
        System.out.println("ConcreteMember: Update");

        GroupAdmin groupadmin = new GroupAdmin();
        ConcreteMember CM = new ConcreteMember("Zili");

        groupadmin.register(CM);
        groupadmin.append("testing");
        assertEquals("Name: Zili; String: testing", CM.toString());

        groupadmin.insert(1, "00");
        assertEquals("Name: Zili; String: t00esting", CM.toString());

        groupadmin.delete(0,5);
        assertEquals("Name: Zili; String: ting", CM.toString());

        groupadmin.undo();
        assertEquals("Name: Zili; String: t00esting", CM.toString());

        groupadmin.unregister(CM);
    }

    @Test
    public void generalTests(){
        GroupAdmin tester = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember("Zili");
        ConcreteMember m2 = new ConcreteMember("Gili");
        ConcreteMember m3 = new ConcreteMember("Tili");

        System.out.println("Testing footprint & totalsize");

        logger.info(()->JvmUtilities.objectFootprint(tester, m1, m2, m3));
        logger.info(()->JvmUtilities.objectTotalSize(tester, m1, m2, m3));

        System.out.println("Registering members: ");
        tester.register(m1);  //added member
        tester.register(m2);  //added member
        tester.register(m3);  //added member

        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));

        tester.append("123"); //will notify Zili & Gili & Tili


        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));

        System.out.println("Now let's unregister a members and see what's happen to the memory aloccate: ");
        tester.unregister(m2);
        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));
        tester.unregister(m3);
        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));

        System.out.println("Printing info: ");
        logger.info(JvmUtilities::jvmInfo);

        System.out.println("insert an object and see the memory state: ");
        tester.insert(1,"avi"); //will notify


        System.out.println("Testing memoryStats");  // prints all the optimal memory nedded for the all program.
        logger.info(()->JvmUtilities.memoryStats(tester));



    }
}
