package no.hiof.groupproject.models.payment_methods;

public abstract class Payment implements no.hiof.groupproject.interfaces.StrLengthCheck {

    private int id;
    private String paymentType;

    public Payment() {
        //blank constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
