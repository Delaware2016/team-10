package com.example.eli.unitedwaydelaware;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stripe.android.*;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;

public class MainActivity extends AppCompatActivity {
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates button submit and sets the onclicklistener
        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCheck();
            }
        });
    }





    /*
    * Financially need the ability to:
    * - Charge a card
    * */

    protected Card customerInfo;

    public void saveCustomer(Card customerCard) {
        if (!customerCard.validateCard()) {
            message("Customer card is NOT valid");
        } else {
            // Save the user
            customerInfo = customerCard;
        }
    }

    public void chargeCard(Card card, double amount) throws AuthenticationException {
        if (card.validateCard()) {
            // Create stripe connection.
            Stripe stripe = new Stripe("sk_test_8GVkTOD7IeGUkpZ76UhO1CbO");
            stripe.createToken(
                    card, new TokenCallback() {
                        @Override
                        public void onError(Exception error) {

                        }

                        @Override
                        public void onSuccess(Token token) {

                        }
                    }
            );
        }
    }

    public void performCheck() {
        Card myCard = new Card("4430-4300-7311-8466", 10, 2017, "265");
        if (myCard.validateCard()) {
            message("DID work!");
        } else {
            message("Didn't work");
        }
    }

    public void message(String message) {
        Toast t = Toast.makeText(getApplicationContext(), "THIS DIDN'T WORK", Toast.LENGTH_SHORT);
        t.show();
    }
}
