package no.hiof.groupproject.tools.verification;

import java.time.LocalDate;
public class Deadline {
    //Added class Deadline to accept/decline an offer..
    private LocalDate today;
    private LocalDate deadline;

    public Deadline(LocalDate today,LocalDate deadline)
    {
        this.today=today;
        this.deadline=deadline;
    }

    public LocalDate getToday(){
        return today;
    }

    public void setToday(LocalDate today){
        this.today=today;
    }

    public LocalDate getDeadline(){
        return deadline;
    }

    public void setDeadline(LocalDate deadline){
        this.deadline=deadline;
    }

    public boolean compareDates() {
        //dateisbeforedeadline
        if (getToday().isEqual(getDeadline())) {
            //if it's the same day
            return true;
        } else {
            return getToday().isBefore(getDeadline());
        }

    }
}
