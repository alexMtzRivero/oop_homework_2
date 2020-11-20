package space.harbour.java.hw9;

public  interface MyObservable {

    void addObserver(MyObserver myObserver);

    void removeObresver(MyObserver myObserver);

    void notifyObserber(int denomination, int quantity);

    void reciveMoney(int denomination, int quantity);
}
