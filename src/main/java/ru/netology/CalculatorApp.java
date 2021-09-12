package ru.netology;

import java.util.Scanner;

public class CalculatorApp {

    static final int NUMBER_OF_MONTHS_IN_YEAR = 12;
    static final int NUMBER_OF_SIMBOLS_AFTER_COMMA = 2;

    public static void main(String[] args) {
        System.out.println("Кредитный калькулятор");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму кредита:");
        String inputCreditAmount = scanner.nextLine();

        System.out.println("Введите годовую процентную ставку:");
        String inputInterestRate = scanner.nextLine();

        System.out.println("Введите срок кредита в месяцах:");
        String inputTerm = scanner.nextLine();

        double monthlyPayment = calculateMonthlyPayment(inputCreditAmount, inputInterestRate, inputTerm);
        double overpayment = calculateOverpayment(inputCreditAmount, inputInterestRate, inputTerm);
        double totalRefundAmount = calculateTotalRefundAmount(inputCreditAmount, inputInterestRate, inputTerm);

        String allOperations = addToString(monthlyPayment, overpayment, totalRefundAmount);
        System.out.println(allOperations);
    }

    public static String addToString(double monthlyPayment, double overpayment, double totalRefundAmount) {
        return "Результаты расчетов кредитного калькулятора: \n" +
                "Ежемесячный платеж: " + monthlyPayment + " р. Переплата за весь период: " + overpayment + " р. " +
                "Общая сумма к возврату в банк: " + totalRefundAmount + " р.";
    }

    public static double calculateMonthlyPayment(String inputCreditAmount, String inputInterestRate, String inputTerm) {
        double monthlyPayment = 0;
        try {
            double creditAmount = Double.parseDouble(inputCreditAmount);
            double interestRate = Double.parseDouble(inputInterestRate);
            double term = Double.parseDouble(inputTerm);

            if (creditAmount <= 0 || interestRate <= 0 || term <= 0) {
                System.out.println("Сумма кредита или процентная ставка или срок кредита не может быть меньше, либо равен нулю.");
                return 0;
            }

            double monthlyInterestRate = interestRate / (NUMBER_OF_MONTHS_IN_YEAR * 100);
            double scale = Math.pow(10, NUMBER_OF_SIMBOLS_AFTER_COMMA);
            monthlyPayment = Math.ceil(scale * (creditAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -term)))) / scale;
            return monthlyPayment;
        } catch (NumberFormatException e) {
            System.out.println("Введено не число");
        }
        return monthlyPayment;
    }

    public static double calculateOverpayment(String inputCreditAmount, String inputInterestRate, String inputTerm) {
        double overpayment = 0;
        try {
            double creditAmount = Double.parseDouble(inputCreditAmount);
            double interestRate = Double.parseDouble(inputInterestRate);
            double term = Double.parseDouble(inputTerm);

            if (creditAmount <= 0 || interestRate <= 0 || term <= 0) {
                System.out.println("Сумма кредита или процентная ставка или срок кредита не может быть меньше, либо равен нулю.");
                return 0;
            }

            double monthlyPayment = calculateMonthlyPayment(inputCreditAmount, inputInterestRate, inputTerm);
            double scale = Math.pow(10, NUMBER_OF_SIMBOLS_AFTER_COMMA);
            overpayment = Math.ceil(scale * ((monthlyPayment * term) - creditAmount)) / scale;
            return overpayment;
        } catch (NumberFormatException e) {
            System.out.println("Введено не число");
        }
        return overpayment;
    }

    public static double calculateTotalRefundAmount(String inputCreditAmount, String inputInterestRate, String inputTerm) {
        double totalRefundAmount = 0;
        try {
            double creditAmount = Double.parseDouble(inputCreditAmount);
            double interestRate = Double.parseDouble(inputInterestRate);
            double term = Double.parseDouble(inputTerm);

            if (creditAmount <= 0 || interestRate <= 0 || term <= 0) {
                System.out.println("Сумма кредита или процентная ставка или срок кредита не может быть меньше, либо равен нулю.");
                return 0;
            }

            double overpayment = calculateOverpayment(inputCreditAmount, inputInterestRate, inputTerm);
            double scale = Math.pow(10, NUMBER_OF_SIMBOLS_AFTER_COMMA);
            totalRefundAmount = Math.ceil(scale * (creditAmount + overpayment)) / scale;
            return totalRefundAmount;
        } catch (NumberFormatException e) {
            System.out.println("Введено не число");
        }
        return totalRefundAmount;
    }
}
