package space.harbour.java.hw2;

import java.util.Scanner;

public class Pascal {

    public static void main(String[] args) {
        System.out.println("How many layer to your piramid?");

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        drawTriangle(num);
    }

    public static void drawTriangle(int n) {
        int[][] pascal =new int[n][n];

        for (int i = 0; i < n; i++)
        {

            printPading((n-i)/2);
            for (int j = 0; j <= i; j++)
            {
                if(j==0 || j==i){
                    pascal[i][j] = 1;
                }
                else{
                   pascal[i][j] = pascal[i-1][j] + pascal[i-1][j-1];
                }
                System.out.print(pascal[i][j]);
                System.out.print(" ");
            }
            System.out.println("");

        }
    }
    public static void printPading(int n){
        for (int i = 0; i < n; i++)
        {
            System.out.print(" ");
        }
    }

}
