package space.harbour.java.hw8;

import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Assert;

public class AtmTest extends TestCase {

    public void testGetMoneySameDenomination() {
        Atm.Container firts = new Atm.Container(500, 1,
                                new Atm.Container(500, 1,
                                new Atm.Container(50, 10,
                                new Atm.Container(20, 10,
                                new Atm.Container(5, 10, null)))));
        Atm atm = new Atm(firts);

        int before = atm.getBalance();
        HashMap<Integer, Integer> result = atm.getMoney(1000);
        int after = atm.getBalance();

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2, (int) result.get(500));
        Assert.assertEquals(before, after + 1000);

    }

    public void testGetBalance() {
        Atm.Container firts = new Atm.Container(500, 10,
                new Atm.Container(200, 10,
                        new Atm.Container(50, 10,
                                new Atm.Container(20, 10,
                                        new Atm.Container(5, 10, null)))));
        Atm atm = new Atm(firts);


        int before = atm.getBalance();
        atm.getMoney(1340);
        int after = atm.getBalance();

        Assert.assertEquals(before, after + 1340);

    }

    public void testGetMoreThanYouCan() {
        Atm.Container firts = new Atm.Container(500, 10,
                new Atm.Container(200, 10,
                        new Atm.Container(50, 10,
                                new Atm.Container(20, 10,
                                        new Atm.Container(5, 10, null)))));
        Atm atm = new Atm(firts);


        int before = atm.getBalance();
        atm.getMoney(1340000);
        int after = atm.getBalance();

        Assert.assertEquals(before, after);

    }

    public void testGetWithNoContainers() {
        Atm.Container firts = null;
        Atm atm = new Atm(firts);

        int before = atm.getBalance();
        atm.getMoney(1340000);
        int after = atm.getBalance();

        Assert.assertEquals(before, after);

    }
}