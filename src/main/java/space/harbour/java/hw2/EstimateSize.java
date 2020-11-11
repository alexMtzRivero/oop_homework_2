package space.harbour.java.hw2;

public class EstimateSize {

    // gradle check to check , build
    public static void main(String[] args) {
        int[] trash = new int [10_000_000];
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        long used = totalMemory - freeMemory;
        System.out.println("Used before GC :" + used);
        System.out.println("memory of int :" + (used / 10_000_000));
        trash = new int[1];
        System.gc();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            // handle exception
        }

        freeMemory = Runtime.getRuntime().freeMemory();
        used = totalMemory - freeMemory;


        System.out.println("Used after GC :" + used);

    }
}
