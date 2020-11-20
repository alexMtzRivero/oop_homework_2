package space.harbour.java.hw9;

import junit.framework.TestCase;
import org.junit.Assert;

public class BankTest extends TestCase {

    public void testColonesAreDiferent() {

        int numberOfAtms = 10;
        AtmClonable masterAtm = AtmClonable.getNewDefaultAtm();
        Bank santander = new Bank();

        for (int i = 0; i < numberOfAtms; i++) {
            try {
                santander.atms.add(masterAtm.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            santander.atms.get(i).addObserver(santander);
        }


        santander.atms.get(0).getMoney(100);
        Assert.assertNotEquals(santander.atms.get(0).getBalance(),
                               santander.atms.get(1).getBalance());
    }

    public void testfillContainer() {
        int numberOfAtms = 10;
        AtmClonable masterAtm = AtmClonable.getNewDefaultAtm();
        Bank santander = new Bank();

        for (int i = 0; i < numberOfAtms; i++) {
            try {
                santander.atms.add(masterAtm.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            santander.atms.get(i).addObserver(santander);
        }


        int before = santander.atms.get(0).getBalance();
        Assert.assertEquals(before, santander.atms.get(0).getBalance());
    }

    public void testFillOnlyWhenNeeded() {
        int numberOfAtms = 10;
        AtmClonable masterAtm = AtmClonable.getNewDefaultAtm();
        Bank santander = new Bank();

        for (int i = 0; i < numberOfAtms; i++) {
            try {
                santander.atms.add(masterAtm.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            santander.atms.get(i).addObserver(santander);
        }


        System.out.println("   ");
        int before = santander.atms.get(0).getBalance();
        for (int i = 0; i < 10; i++) {
            // if we take bils the balance should decrement
            Assert.assertEquals(before - (50 * i), santander.atms.get(0).getBalance());
            santander.atms.get(0).getMoney(50);
        }
        // once we take the last bill the container should be refiled
        santander.atms.get(0).getMoney(5);
        Assert.assertEquals(before, santander.atms.get(0).getBalance());
    }


}