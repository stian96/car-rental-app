package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrievePaymentDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
//returns a Payment based on a specific payment id
public interface DeserialisePayment {

    static Payment deserialise(int id) {
        return RetrievePaymentDB.retrieve(id);
    }


}
