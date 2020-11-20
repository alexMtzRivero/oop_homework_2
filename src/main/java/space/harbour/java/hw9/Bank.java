package space.harbour.java.hw9;

import java.util.ArrayList;

public class Bank implements MyObserver {
    ArrayList<AtmClonable> atms;

    public static void main(String[] args) throws CloneNotSupportedException {
        int numberOfAtms = 10;
        AtmClonable masterAtm = AtmClonable.getNewDefaultAtm();
        Bank santander = new Bank();

        for (int i = 0; i < numberOfAtms; i++) {
            santander.atms.add(masterAtm.clone());
            santander.atms.get(i).addObserver(santander);
        }

        System.out.println(santander.atms.get(0).getMoney(7000));

        for (AtmClonable atm : santander.atms) {
            System.out.println(atm.getBalance());
        }


    }


    public Bank(ArrayList<AtmClonable> atms) {
        this.atms = atms;
        for (AtmClonable atm : this.atms) {
            atm.addObserver(this);
        }
    }

    public Bank() {
        this.atms = new ArrayList<AtmClonable>();
    }

    @Override
    public void sendMoney(MyObservable observable, int denomination, int quantity) {
        System.out.println("bank sending  10 bills of " + denomination);
        observable.reciveMoney(denomination, 10);
    }

}
