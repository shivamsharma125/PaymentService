package com.shivam.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.apikey}")
    private String stripeApiKey;


    @Override
    public String createPaymentLink(Long amount, Long orderId, String phoneNumber, String name, String email) {

        try {
            Stripe.apiKey = this.stripeApiKey;

            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(getPrice(amount).getId())
                                            .setQuantity(1L)
                                            .build()
                            ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder().
                                    setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT).
                                    setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().
                                            setUrl("https://www.google.com/").build())
                                    .build())
                            .build();
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        }
        catch (StripeException exception) {
            throw new RuntimeException("Error while generating payment link");
        }

    }

    private Price getPrice(Long amount) {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring( //Remove in case of one time payment
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();
            Price price = Price.create(params);
            return price;
        } catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
