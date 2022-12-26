package observer;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

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
    void update() {
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
}