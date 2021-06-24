package leetcode.easy;

public class Stock {

    public static void main(String[] args)
    {
        c();
    }

    public static void c(){
        System.out.println((33.58+33.86+35.02+35.09+34.63)/5);
    }

    public static void a(){
        int years = 20;
        double incomeRate = 0.12;
        double income = 13000 * 12;

        double total = 0;

        for (int i = 0; i < years; i++)
        {
            total += income;
            total += total*incomeRate;
        }

        System.out.println(years*income/10000);

        System.out.println(total/10000);
    }
}
