package com.shivam.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component
@Primary
public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpay;

    public RazorpayPaymentGateway(RazorpayClient razorpay){
        this.razorpay = razorpay;
    }

    @Override
    public String createPaymentLink(Long orderId) {
        // we need to call razorpay api to create the link for this orderId
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1691097057);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for order id: " + orderId);
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","Shivam Sharma");
        customer.put("email","shivam.sharma@example.com");
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

        PaymentLink paymentLink = null;
        try{
            paymentLink = razorpay.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException ex){
            ex.printStackTrace();
        }

        return paymentLink == null ? "" : paymentLink.toString();
    }
}
