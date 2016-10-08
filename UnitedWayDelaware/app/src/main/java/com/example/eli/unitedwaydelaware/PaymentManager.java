package com.example.eli.unitedwaydelaware;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.*;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


/**
 * Created by RodneyWells on 10/7/2016.
 */

public class PaymentManager {
    protected Card userCard;
    protected Token mytoken;
    private static Context context;
    protected int amountToCharge;

    public PaymentManager(String number, int expMonth, int expYear, String cardCVC) {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        final CountDownLatch doneSignal = new CountDownLatch(1);
        this.userCard = new Card(number, expMonth, expYear, cardCVC);
    }

    /**
     *
     * @param amount Dollar amount in cents of transaction.
     * @throws AuthenticationException
     * @throws APIException
     */
    public void chargeCard(final int amount) throws AuthenticationException, APIException {
        // Create a charge: this will charge the user's card

        try {
            message("Attempting to charge card!");

            final Stripe stripe = new Stripe("pk_test_M6TIKEnTLXCsit8vzSO7AwYe");

            // Wait
            if (this.userCard.validateCard()) {
                message("Here so far!");
                stripe.createToken(
                        this.userCard, new TokenCallback() {
                            @Override
                            public void onError(Exception error) {
                                message(error.getMessage());

//                                doneSignal.countDown();
                            }

                            @Override
                            public void onSuccess(Token token) {
                                mytoken = token;
                                message(mytoken.getId());
                                message("Token successfully obtained! " + mytoken.getCard());

                                Map<String, Object> chargeParams = new HashMap<String, Object>();
                                chargeParams.put("amount", amount); // Amount in cents
                                chargeParams.put("currency", "usd");
                                chargeParams.put("source", mytoken.getCard()); // TODO that may be wrong
                                chargeParams.put("description", "A generous donation to United Way.");

                                try {
                                    stripe.setDefaultPublishableKey("pk_test_M6TIKEnTLXCsit8vzSO7AwYe");
                                } catch (AuthenticationException e) {
                                    e.printStackTrace();
                                }


                                // Submit charge to user.
                                Charge charge;
                                try {
                                    message("About to charge");
                                     charge = Charge.create(chargeParams);
                                    message("Yo it's been charged!" + charge.getDescription());
                                } catch (InvalidRequestException e) {
                                    e.printStackTrace();
                                } catch (APIConnectionException e) {
                                    e.printStackTrace();
                                } catch (APIException e) {
                                    e.printStackTrace();
                                } catch (CardException e) {
                                    e.printStackTrace();
                                } catch (AuthenticationException e) {
                                    e.printStackTrace();
                                }




//                                doneSignal.countDown();
                            }
                        }
                );
            } else {
                message("Error validating card!");
            }
        } catch (Exception e) {
            // The card has been declined
            message("Card rejected?");
        }
    }

    public void message(String message) {
//        Toast t = Toast.makeText(this.context, "THIS DIDN'T WORK", Toast.LENGTH_SHORT);
//        t.show();
        Log.d("Parse", message);
    }
}
