package space.harbour.java.hw8;

import java.util.HashMap;
import java.util.Iterator;

public class Atm implements Iterator<Atm.Container> {

    Container first;
    Container pointedContainer;

    public static void main(String[] args) {

    }

    public Atm(Container first) {
        this.first = first;
        this.pointedContainer = first;
    }

    @Override
    public boolean hasNext() {
        if (first != null) {
            return pointedContainer.lower != null;
        }
        return false;
    }

    @Override
    public Container next() {
        pointedContainer = pointedContainer.lower;
        return pointedContainer;
    }

    public static class Container {
        int denomination;
        int available;
        Container lower;

        public Container(int denomination, int available, Container lower) {
            this.lower = lower;
            this.available = available;
            this.denomination = denomination;
        }

        public HashMap<Integer, Integer> getMoney(int quantity, HashMap<Integer, Integer> current) {
            int bils =  quantity / denomination;

            // if we can give money we give
            if (available < bils) {
                bils = available;
            }
            if (bils > 0)  {
                int toPut = bils;
                if (current.containsKey(denomination)) {
                    toPut += current.get(denomination);
                }
                current.put(denomination, toPut);
                available -= bils;
                quantity -= (denomination * bils);
            }
            // if we completed all the money we return
            if (quantity == 0) {
                return current;
            }

            // if we have more money give we pass to the next container
            if (lower != null) {
                HashMap<Integer, Integer> asked  = lower.getMoney(quantity, current);

                if (asked == null) {
                    available += bils;
                } else {
                    return asked;
                }
            }

            return null;
        }

        public Integer getBalance() {
            return denomination * available;
        }
    }

    public HashMap<Integer, Integer> getMoney(int quantity) {
        if (first == null) {
            return null;
        }
        HashMap<Integer, Integer> reciepe = new HashMap<>();
        HashMap<Integer, Integer> result = first.getMoney(quantity, reciepe);
        if (result == null) {
            System.out.println("ERROR: NO more money");
        }
        return result;
    }

    public int getBalance() {
        pointedContainer = first;
        int total = 0;
        while (hasNext()) {
            total += pointedContainer.getBalance();
            next();
        }
        return total;
    }
}
