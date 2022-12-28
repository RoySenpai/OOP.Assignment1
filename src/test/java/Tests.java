import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        GroupAdmin tester = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember("Zili");
        ConcreteMember m2 = new ConcreteMember("Gili");
        ConcreteMember m3 = new ConcreteMember("Tili");

        System.out.println("Testing footprint & totalsize");

        logger.info(()->JvmUtilities.objectFootprint(tester, m1, m2, m3));
        logger.info(()->JvmUtilities.objectTotalSize(tester, m1, m2, m3));
        System.out.println();
        System.out.println("Registering members: ");
        tester.register(m1);  //added member
        tester.register(m2);  //added member
        tester.register(m3);  //added member

        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));

        tester.append("123"); //will notify Zili & Gili & Tili
        System.out.println();

        System.out.println("let's see what happen to the memory after we appended the string 123 ");
        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));
        System.out.println();

        System.out.println("Now let's unregister a members and see what's happen to the memory aloccate: ");
        tester.unregister(m2);
        System.out.println();
        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));
        tester.unregister(m3);
        System.out.println();
        logger.info(()->JvmUtilities.memoryStats(tester)); // return the objectfootprint & objectTotalSize together
        // logger.info(()->JvmUtilities.objectFootprint(tester));
        // logger.info(()->JvmUtilities.objectTotalSize(tester));

        System.out.println("Printing info: ");
        logger.info(() -> JvmUtilities.jvmInfo());
        System.out.println();

        System.out.println("insert an object and see the memory state: ");
        tester.insert(1,"avi"); //will notify

        System.out.println();


    }
}
