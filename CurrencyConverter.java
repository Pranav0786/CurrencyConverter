package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JLabel resultLabel;

    private final double USD_TO_EUR = 0.83;
    private final double USD_TO_GBP = 0.73;
    private final double USD_TO_INR = 75.25;
    private final double EUR_TO_USD = 1.21;
    private final double EUR_TO_GBP = 0.88;
    private final double GBP_TO_USD = 1.37;
    private final double GBP_TO_EUR = 1.13;
    private final double GBP_TO_INR = 86.59;
    private final double EUR_TO_INR = 85.57;
    private final double INR_TO_USD = 0.013;
    private final double INR_TO_EUR = 0.012;
    private final double INR_TO_GBP = 0.012;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 250); // Increased size
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Added margin
        panel.setBackground(new Color(240, 240, 240)); // Background color

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Font size and style
        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fromCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"}); // Added INR
        fromCurrencyBox.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        toCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"}); // Added INR
        toCurrencyBox.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton exchangeButton = new JButton("Exchange"); // Added exchange button
        exchangeButton.setFont(new Font("Arial", Font.BOLD, 16));

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        convertButton.addActionListener(this);
        exchangeButton.addActionListener(this); // Added action listener for exchange button

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(fromLabel);
        panel.add(fromCurrencyBox);
        panel.add(toLabel);
        panel.add(toCurrencyBox);
        panel.add(convertButton);
        panel.add(exchangeButton); // Added exchange button
        panel.add(resultLabel);

        // Adding padding
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Adjusted padding

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            convertCurrencyAndDisplayResult();
        } else if (e.getActionCommand().equals("Exchange")) { // Handle exchange button click
            exchangeCurrencies();
        }
    }

    private void convertCurrencyAndDisplayResult() {
        String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
        String toCurrency = (String) toCurrencyBox.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());

        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
        resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
    }

    private void exchangeCurrencies() {
        // Exchange the selected currencies
        int fromIndex = fromCurrencyBox.getSelectedIndex();
        int toIndex = toCurrencyBox.getSelectedIndex();

        // Swap the currencies
        fromCurrencyBox.setSelectedIndex(toIndex);
        toCurrencyBox.setSelectedIndex(fromIndex);

        // Convert and display result after exchange
        convertCurrencyAndDisplayResult();
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double conversionRate = 1.0;

        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            conversionRate = USD_TO_EUR;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            conversionRate = USD_TO_GBP;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("INR")) { // USD to INR
            conversionRate = USD_TO_INR;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            conversionRate = EUR_TO_USD;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("GBP")) {
            conversionRate = EUR_TO_GBP;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("INR")) { // EUR to INR
            conversionRate = EUR_TO_INR;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            conversionRate = GBP_TO_USD;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("EUR")) {
            conversionRate = GBP_TO_EUR;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("INR")) { // GBP to INR
            conversionRate = GBP_TO_INR;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("USD")) { // INR to USD
            conversionRate = INR_TO_USD;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("EUR")) { // INR to EUR
            conversionRate = INR_TO_EUR;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("GBP")) { // INR to GBP
            conversionRate = INR_TO_GBP;
        }

        return amount * conversionRate;
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}