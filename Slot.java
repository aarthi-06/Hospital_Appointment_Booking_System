public class Slot {

    int slotId;
    int startTime;
    int endTime;
    boolean isBooked=false;
    Doctor docobj;

    
    public Slot(int slotId,int st,int et,Doctor docobj){

        this.slotId=slotId;
        this.startTime=st;
        this.endTime=et;
        this.docobj=docobj;
    }


}
