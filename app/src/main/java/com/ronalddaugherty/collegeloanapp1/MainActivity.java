package com.ronalddaugherty.collegeloanapp1;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    // currency and percent formatters
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    public double loanAmount; // loan amount entered by the user


    private EditText loanAmountEditText; //user enters the loan amount

    private TextView fiveYearPaymentDisplay; //five year
    private TextView tenYearPaymentDisplay; //ten year
    private TextView fifteenYearPaymentDisplay; // fifteen year
    private TextView twentyYearPaymentDisplay; // twenty year
    private TextView twentyFiveYearPaymentDisplay; // twenty five year
    private TextView thirtyYearPaymentDisplay; // thirty year

    public int interestRate; // seekbar that show's loan length

    // called when the activity is first created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // inflates GUI




        // get references to the TextViews
        // that MainActivity interacts with programmatically



        fiveYearPaymentDisplay = (TextView) findViewById(R.id.fiveYearPaymentDisplay);
        tenYearPaymentDisplay = (TextView) findViewById(R.id.tenYearPaymentDisplay);
        fifteenYearPaymentDisplay = (TextView) findViewById(R.id.fifteenYearPaymentDisplay);
        twentyYearPaymentDisplay = (TextView) findViewById(R.id.twentyYearPaymentDisplay);
        twentyFiveYearPaymentDisplay = (TextView) findViewById(R.id.twentyFiveYearPaymentDisplay);
        thirtyYearPaymentDisplay = (TextView) findViewById(R.id.thirtyYearPaymentDisplay);






        //get EditText for loan amount
        loanAmountEditText = (EditText) findViewById(R.id.loanAmountEditText);



        // update GUI based on billAmount and customPercent
        loanAmountEditText.setText(
                currencyFormat.format(loanAmount));


        updateScreen();

        // set amountEditText's TextWatcher
        EditText loanAmountEditText =
                (EditText) findViewById(R.id.loanAmountEditText);
        loanAmountEditText.addTextChangedListener(loanAmountEditTextWatcher);

        // set loanLengthSeekBar's OnSeekBarChangeListener
        SeekBar interestRateSeekBar =
                (SeekBar) findViewById(R.id.loanLengthSeekBar);
        interestRateSeekBar.setOnSeekBarChangeListener(loanLengthSeekBarListener);


    } // end method onCreate




    //emi calculation handled here
    //p = principal
    //r = rate
    //n = number of years
    public Double calculateEmi(double p, double r, double n){
        double rate = (interestRate/12)/100;
        double emi = (loanAmount * rate * (Math.pow((1+rate), n)) / ((Math.pow((1+rate),n)) -1));
        return emi;
    }

    //update the monthly payment amounts for everything
    private void updateScreen() {




        //
        //five years
        //
        double fiveYearsMonthlyPayment = calculateEmi( loanAmount, interestRate, 5);
        fiveYearPaymentDisplay.setText(String.format("%.02f", fiveYearsMonthlyPayment));
        //

        //
        //ten years
        //
        double tenYearsMonthlyPayment = calculateEmi(loanAmount, interestRate, 10);
        tenYearPaymentDisplay.setText(String.format("%.02f", tenYearsMonthlyPayment));
        //

        //
        //fifteen years
        //
        double fifteenYearsMonthlyPayment = calculateEmi(loanAmount, interestRate, 15);
        fifteenYearPaymentDisplay.setText(String.format("%.02f", fifteenYearsMonthlyPayment));
        //

        //
        //twenty years
        //
        double twentyYearsMonthlyPayment = calculateEmi(loanAmount, interestRate, 20);
        twentyYearPaymentDisplay.setText(String.format("%.02f", twentyYearsMonthlyPayment));
        //

        //
        //twentyFive years
        //
        double twentyFiveYearsMonthlyPayment = calculateEmi(loanAmount, interestRate, 25);
        twentyFiveYearPaymentDisplay.setText(String.format("%.02f", twentyFiveYearsMonthlyPayment));
        //

        //
        //thirty years
        //
        double thirtyYearsMonthlyPayment = calculateEmi(loanAmount, interestRate, 30);
        thirtyYearPaymentDisplay.setText(String.format("%.02f", thirtyYearsMonthlyPayment));
        //
    }













    // called when the user changes the position of SeekBar
    private SeekBar.OnSeekBarChangeListener loanLengthSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                // update Interest Rate Percent, then call updateCustom
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    // sets customPercent to position of the SeekBar's thumb
                    interestRate = progress;
                    updateScreen(); // update the custom tip TextViews
                } // end method onProgressChanged

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                } // end method onStartTrackingTouch

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)

                {
                } // end method onStopTrackingTouch
            }; // end OnSeekBarChangeListener


    // event-handling object that responds to amountEditText's events
    private TextWatcher loanAmountEditTextWatcher = new TextWatcher() {
        // called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            // convert amountEditText's text to a double
            try {
                loanAmount = Double.parseDouble(s.toString()) ;
            } // end try
            catch (NumberFormatException e) {
                loanAmount = 0.0; // default if an exception occurs
            } // end catch





                // display currency formatted bill amount
                loanAmountEditText.setText(currencyFormat.format(loanAmount));
                updateScreen(); // update the 15% tip TextViews

            } // end method onTextChanged

            @Override
            public void afterTextChanged (Editable s)
            {
            } // end method afterTextChanged

            @Override
            public void beforeTextChanged (CharSequence s,int start, int count,
            int after)
            {
            } // end method beforeTextChanged
        }

        ; // end amountEditTextWatcher









    }

