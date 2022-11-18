package no.hiof.groupproject.models.payment_methods;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.GetAutoIncrementId;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.interfaces.StrLengthCheck;
import no.hiof.groupproject.tools.db.InsertPaymentDB;

public abstract class Payment implements Serialise, StrLengthCheck, ExistsInDb, GetAutoIncrementId {

    private int id;
    private String paymentType;

    public Payment() {
        //blank constructor
    }

    @Override
    public void serialise() {
        InsertPaymentDB.insert(this);
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
