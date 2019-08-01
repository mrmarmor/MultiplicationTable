package com.appstairs.multiplicationtable;

public class Utils {
    private static final Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public String convertToAnotherBase(int matrixBase, int number) {
        if (matrixBase == Base.BINARY) {
            return Integer.toBinaryString(number);
        } else if (matrixBase == Base.HEX) {
            return Integer.toHexString(number);
        }

        return number + "";
    }
}
