package arrays;

import java.math.BigDecimal;

public class Factorial {
    static void main() {
        var fac = new Factorial();

        IO.println(fac.factorialIterative(BigDecimal.valueOf(5)));
    }

    // This will throw stack overflow in case of really huge number
    public BigDecimal factorial(BigDecimal value) {
        if(value.signum() < 0)
            throw new IllegalArgumentException("value must be non-negative");
        if(BigDecimal.ZERO.equals(value))
            return BigDecimal.ONE;
        if(BigDecimal.ONE.equals(value))
            return value;
        return value.multiply(factorial(value.subtract(BigDecimal.ONE)));
    }


    // better way is to use for loop
    public BigDecimal factorialIterative(BigDecimal value) {
        if(value.signum() < 0)
            throw new IllegalArgumentException("value must be non-negative");
        if(BigDecimal.ZERO.equals(value))
            return BigDecimal.ONE;
        BigDecimal result = BigDecimal.ONE;
        for(var curr = BigDecimal.TWO; curr.compareTo(value) <= 0; curr = curr.add(BigDecimal.ONE)) {
            result = result.multiply(curr);
        }
        return result;
    }
}
