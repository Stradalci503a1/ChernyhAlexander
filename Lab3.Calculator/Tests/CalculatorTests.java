import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CalculatorTests {

    @Test
    public void nullExpression() {
        Assertions.assertEquals(Double.NaN, Calculator.calculate(null));
    }

    @Test
    public void incorrectExpression() {
        Assertions.assertEquals(Double.NaN, Calculator.calculate("2+*54"));
        Assertions.assertEquals(Double.NaN, Calculator.calculate("sdf"));
        Assertions.assertEquals(Double.NaN, Calculator.calculate("-543*43"));
        Assertions.assertEquals(Double.NaN, Calculator.calculate(""));
        Assertions.assertEquals(Double.NaN, Calculator.calculate("25-24*(21-1))"));
    }

    @Test
    public void divisionByZero() {
        Assertions.assertEquals(Double.NaN, Calculator.calculate("54/0"));
    }

    @Test
    public void correctExpression() {
        Assertions.assertEquals(56, Calculator.calculate("2+54"));
        Assertions.assertEquals(170, Calculator.calculate("(54+3)*3-1"));
        Assertions.assertEquals(23349, Calculator.calculate("543*43"));
        Assertions.assertEquals(31, Calculator.calculate("(9*5)-5-1*(5+4)"));
    }

}
