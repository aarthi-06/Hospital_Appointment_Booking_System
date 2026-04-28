import java.util.*;

public class Doctor {
    
    String doctorId;
    String name;
    String speaciality;
    ArrayList<Appointment> schedule;
    ArrayList<Integer> availableSlots;


    public Doctor(String doctorId,String name, String speaciality, 
        ArrayList<Appointment> appts,ArrayList<Integer> slots){

            this.doctorId=doctorId;
            this.name=name;
            this.speaciality=speaciality;
            this.schedule=appts;
            this.availableSlots=slots;

    }




}
