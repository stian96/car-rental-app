package no.hiof.groupproject.models.payment_methods;

public class GooglePay extends PaymentViaAccount {

    public GooglePay(String email, String pwd) {
        super(email, pwd);
        this.setPaymentType("googlepay");

        if (!existsInDb()) {
            serialise();
        }
        //the id is automatically incremented when inserted into the database
        //the autoincrement id is fetched and assigned to this instance
        this.setId(getAutoIncrementId());
    }


    public GooglePay() {

    }
}
