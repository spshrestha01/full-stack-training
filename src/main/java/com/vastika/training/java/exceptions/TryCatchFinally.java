package com.vastika.training.java.exceptions;

public class TryCatchFinally {
    public static void main(String[] args) {
        TryCatchFinally obj = new TryCatchFinally();
        int result = obj.division(12, 10);
        System.out.println(result);

        result = obj.division(12, 0);
        System.out.println(result);
    }

    private int division(int dividend, int divisor) {
        try{
            int result = dividend / divisor;
            return result;
        }catch (ArithmeticException ae){
            System.out.println(ae.getMessage());
        }finally {
            System.out.println("Done Division");
        }
        return 0;

    }
}
