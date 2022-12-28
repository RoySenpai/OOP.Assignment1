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

        System.out.println("Testing footprint & totalsize");

        logger.info(()->JvmUtilities.objectFootprint(tester, m1, m2));
        logger.info(()->JvmUtilities.objectTotalSize(tester, m1, m2));

        System.out.println("Registering members");

        tester.register(m1);
        tester.register(m2);

        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));

        tester.append("123");

        logger.info(()->JvmUtilities.objectFootprint(tester));
        logger.info(()->JvmUtilities.objectTotalSize(tester));

        System.out.println("Printing info");

        logger.info(() -> JvmUtilities.jvmInfo());
    }
}
