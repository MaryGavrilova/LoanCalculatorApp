package ru.netology;

import org.junit.jupiter.api.*;

import static ru.netology.CalculatorApp.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CalculatorAppTest {
    @BeforeAll
    public static void initSuite() {
        System.out.println("Running tests");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Tests complete");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");

    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete");
    }

    @Test
    public void calculateMonthlyPayment_validArgument_success() {
        String argumentCreditAmount = "20000";
        String argumentPercent = "12";
        String argumentTerm = "36";
        double expected = 664.29;
        double result = calculateMonthlyPayment(argumentCreditAmount, argumentPercent, argumentTerm);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void calculateMonthlyPayment_notNumberArgument_doesNotThrowsException() {
        String argumentCreditAmount = "петя";
        String argumentPercent = "12";
        String argumentTerm = "36";
        assertDoesNotThrow(() -> {
            calculateMonthlyPayment(argumentCreditAmount, argumentPercent, argumentTerm);
        });
    }

    @Test
    public void calculateMonthlyPayment_notPositiveNumber() {
        String argumentCreditAmount = "-20000";
        String argumentPercent = "12";
        String argumentTerm = "36";
        double result = calculateMonthlyPayment(argumentCreditAmount, argumentPercent, argumentTerm);
        Assertions.assertEquals(0.0, result);
    }

    @Test
    public void calculateOverpayment_validArgument_success() {
        String argumentCreditAmount = "20000";
        String argumentPercent = "12";
        String argumentTerm = "36";
        double expected = 3914.44;
        double result = calculateOverpayment(argumentCreditAmount, argumentPercent, argumentTerm);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void calculateOverpayment_notNumberArgument_doesNotThrowsException() {
        String argumentCreditAmount = "петя";
        String argumentPercent = "12";
        String argumentTerm = "36";
        assertDoesNotThrow(() -> {
            calculateOverpayment(argumentCreditAmount, argumentPercent, argumentTerm);
        });
    }

    @Test
    public void calculateOverpayment_notPositiveNumber() {
        String argumentCreditAmount = "-20000";
        String argumentPercent = "12";
        String argumentTerm = "36";
        double result = calculateOverpayment(argumentCreditAmount, argumentPercent, argumentTerm);
        Assertions.assertEquals(0.0, result);
    }

    @Test
    public void calculateTotalRefundAmount_validArgument_success() {
        String argumentCreditAmount = "20000";
        String argumentPercent = "12";
        String argumentTerm = "36";
        double expected = 23914.44;
        double result = calculateTotalRefundAmount(argumentCreditAmount, argumentPercent, argumentTerm);
        Assertions.assertEquals(expected, result);
    }

    public void calculateTotalRefundAmount_notNumberArgument_doesNotThrowsException() {
        String argumentCreditAmount = "петя";
        String argumentPercent = "12";
        String argumentTerm = "36";
        assertDoesNotThrow(() -> {
            calculateTotalRefundAmount(argumentCreditAmount, argumentPercent, argumentTerm);
        });
    }

    @Test
    public void calculateTotalRefundAmount_notPositiveNumber() {
        String argumentCreditAmount = "-20000";
        String argumentPercent = "12";
        String argumentTerm = "36";
        double result = calculateTotalRefundAmount(argumentCreditAmount, argumentPercent, argumentTerm);
        Assertions.assertEquals(0.0, result);
    }

    @Test
    public void addToString_validArgument_success() {
        double argumentMonthlyPayment = 664.29;
        double argumentOverpayment = 3914.44;
        double argumentTotalRefundAmount = 23914.44;
        String expected = "Результаты расчетов кредитного калькулятора: \n" +
                "Ежемесячный платеж: 664.29 р. Переплата за весь период: 3914.44 р. Общая сумма к возврату в банк: 23914.44 р.";
        String result = addToString(argumentMonthlyPayment, argumentOverpayment, argumentTotalRefundAmount);
        Assertions.assertEquals(expected, result);
    }


}
