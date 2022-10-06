package no.hiof.groupproject.models.payment_methods;

import no.hiof.groupproject.models.payment_methods.Payment;

import java.time.LocalDate;

public class Credit_Debit extends Payment {

    private int card_details, ccv;
    private LocalDate valid_until;

}
