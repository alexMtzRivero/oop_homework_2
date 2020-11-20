package space.harbour.java.hw9;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

public class AtmClonable  implements Cloneable,
                                    Iterator<AtmClonable.Container>,
                                    MyObservable,
                                    MyObserver {

    Container first;
    Container pointedContainer;
    MyObserver bank;

    public static void main(String[] args) throws CloneNotSupportedException {

        AtmClonable atm = AtmClonable.getNewDefaultAtm();

        AtmClonable atm2 = atm.clone();

        System.out.println(atm.getBalance());
        System.out.println(atm2.getBalance());
        atm.getMoney(400);
        System.out.println(atm.getBalance());
        System.out.println(atm2.getBalance());

    }

    public static  AtmClonable getNewDefaultAtm() {
        AtmClonable.Container firts = new AtmClonable.Container(500, 10,
                        new AtmClonable.Container(200, 10,
                        new AtmClonable.Container(50, 10,
                        new AtmClonable.Container(20, 10,
                        new AtmClonable.Container(5, 10, null)))));
        return new AtmClonable(firts);
    }

    public AtmClonable(Container first) {
        this.first = first;
        this.pointedContainer = first;

        while (hasNext()) {
            pointedContainer.addObserver(this);
            next();
        }
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


    @Override
    public void addObserver(MyObserver myObserver) {
        bank = myObserver;
    }

    @Override
    public void removeObresver(MyObserver myObserver) {
        bank = null;
    }

    @Override
    public void notifyObserber(int denomination, int quantity) {
        if (bank != null) {
            System.out.println("atm asking bank for bills of " + denomination);
            bank.sendMoney(this, denomination, quantity);
        }
    }


    @Override
    public void reciveMoney(int denominaton, int quantity) {
        while (hasNext()) {
            if (pointedContainer.denomination == denominaton && pointedContainer.available == 0) {
                pointedContainer.available = quantity;
                break;
            }
            next();
        }
        pointedContainer = first;
    }

    @Override
    public void sendMoney(MyObservable observable, int denomination, int quantity) {
        notifyObserber(denomination, quantity);
        System.out.println("atm sending  10 bills of " + denomination);
        observable.reciveMoney(denomination, 10);
    }

    public static class Container implements Cloneable, MyObservable {
        int denomination;
        int available;
        Container lower;
        MyObserver atm;

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
            if (available == 0) {
                notifyObserber(this.denomination, -1);
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

        public Container clone() {
            Container newNext = null;
            if (lower != null) {
                newNext  = lower.clone();
            }
            return new Container(denomination, available, newNext);
        }

        @Override
        public void addObserver(MyObserver myObserver) {
            atm = myObserver;
        }

        @Override
        public void removeObresver(MyObserver myObserver)  {
            atm = null;
        }

        @Override
        public void notifyObserber(int denomination, int quantity) {
            if (atm != null) {
                System.out.println("container asking Atm  for bills of " + denomination);
                atm.sendMoney(this, this.denomination, -1);
            }
        }

        @Override
        public void reciveMoney(int denomination, int quantity) {
            System.out.println("acontainer reciving bills of " + denomination);
            this.available = quantity;
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

    public AtmClonable clone() throws CloneNotSupportedException {

        Container newFirst = first.clone();

        return  new AtmClonable(newFirst);
    }


}
