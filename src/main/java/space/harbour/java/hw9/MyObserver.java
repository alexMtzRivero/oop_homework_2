package space.harbour.java.hw9;

import space.harbour.java.hw9.AtmClonable;

public interface MyObserver {
    // Update with another name
    void sendMoney(MyObservable observable, int denomination, int quantity);
}
