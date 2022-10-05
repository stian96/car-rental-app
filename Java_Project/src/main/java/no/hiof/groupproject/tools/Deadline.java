package no.hiof.groupproject.tools;
import java.time.*;
public class Deadline {
//class for deadline to accept/decline an offer....
        private LocalDateTime today;
        private LocalDateTime deadline;

        public Deadline(LocalDateTime today,LocalDateTime deadline)
        {
            this.today=today;
            this.deadline=deadline;
        }

        public LocalDateTime getToday(){
            return LocalDateTime.now();
        }

        public void setToday(LocalDateTime today){
            this.today=today;
        }

        public LocalDateTime getDeadline(){
            return getToday().plusDays(2);
        }

        public void setDeadline(LocalDateTime deadline){
            this.deadline=deadline;
        }

        public boolean compareDates(){
            if(getToday().compareTo(getDeadline())>0){
                return false;//dateisafterdeadline
            }else if(getToday().compareTo(getDeadline())<0){
                return true;//dateisbeforedeadline
            }
            else{
                return true;}//thisdateequaltodeadline
        }

}
