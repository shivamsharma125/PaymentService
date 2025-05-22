package com.shivam.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpay;

    public RazorpayPaymentGateway(RazorpayClient razorpay){
        this.razorpay = razorpay;
    }

    @Override
    public String createPaymentLink(Long amount,
                                    Long orderId,
                                    String phoneNumber,
                                    String name,
                                    String email) {
        try{
            // we need to call razorpay api to create the link for this orderId
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",true);
            paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",1744788381); // epoch time
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description","Payment for order id: " + orderId);
            JSONObject customer = new JSONObject();
            customer.put("name", phoneNumber); // +919000090000
            customer.put("contact", name);
            customer.put("email", email);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name","Testing Payment Gateway");
            paymentLinkRequest.put("notes",notes);
            paymentLinkRequest.put("callback_url","https://google.com/");
            paymentLinkRequest.put("callback_method","get");

            PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);

            return paymentLink.get("short_url").toString();

        } catch (RazorpayException ex){
            throw new RuntimeException("Error while generating payment link");
        }

    }
}
