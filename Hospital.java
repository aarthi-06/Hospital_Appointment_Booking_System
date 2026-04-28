import java.util.*;

public class Hospital {

    public static final String name="XYZ Hospital";

    public static final ArrayList<String> departments=new ArrayList<>(Arrays.asList("Ortho","Cardio","ENT"));

    public static ArrayList<Doctor> doctors=initializeDoctors();

    public static ArrayList<Slot> slots=initializeSlots();

    public ArrayList<Appointment> appointments=new ArrayList<>();

    public static ArrayList<Patient> patients=new ArrayList<>();

    

    public static void main(String[] args){

        Scanner obj=new Scanner(System.in);

        System.out.println("Welcome to Hospital "+name);
        System.out.println("Available Departments: Ortho, Cardio, ENT");

        System.out.println("New Patient yes/no");

        String pc=obj.next();

        if(pc.equals("yes")){

            System.out.println("Enter name");
            String name=obj.next();

            System.out.println("Enter age");
            int age=obj.nextInt();

            System.out.println("Enter phone no");
            int phoneno=obj.nextInt();


            int patid=(patients.isEmpty()) ? 101:patients.get(patients.size()-1).patientId+1;

            patients.add(new Patient(patid, name, age, phoneno));

        }


        else{
            System.out.print("Enter your id");
            int id=obj.nextInt();
        }

       

        while (true) {

            System.out.println("Available choices 1: book, 2:quit");
            int ch=obj.nextInt();

            if(ch==2) break;

            if(ch==1){
                System.out.print("Enter type of speacialist you want to consult");

                String s=obj.next();

                ArrayList<Doctor> docs=searchDoctor(s);

               

                System.out.println("Available doctors are : ");

                for(Doctor j:docs){
                    System.out.println(j.doctorId+" "+j.name);

                }

                System.out.println("Enter the doctor Id of whom you want to consult");


                String docId=obj.next();

                ArrayList<Slot> aslots=getAvailableSlots(docId);

                if(aslots.isEmpty()) System.out.print("No free slots, try again");
                
                
                else{
                    System.out.print("Available Slots are : ");
                    for(Slot ss:aslots){
                        System.out.println("Slot id "+ss.slotId+" StartTime " +ss.startTime+" :00"+"EndTime "+ " :00"+ss.endTime);
                        
                    }

                    System.out.println("Enter any slot id that you wish to proceed/ otherwise enter no");

                    String proconfirm=obj.next();

                    if(proconfirm.equals("no")) continue;

                    else{
                        int getid=Integer.parseInt(proconfirm);

                        for(int o=0;o<slots.size();o++){

                            Slot ms=slots.get(o);
                            ms.isBooked=false;
                            if(ms.slotId==getid){
                               slots.set(o, ms);
                               break;
                            }
                        }


                        for(int d=0;d<docs.size();d++){
                            Doctor md=docs.get(d);
                            int delindex=-1;

                            if(md.doctorId.equals(docId)){
                                for(int k=0;k<md.availableSlots.size();k++){
                                    if(md.availableSlots.get(k)==getid) delindex=k;
                                }

                                md.availableSlots.remove(delindex);

                                docs.set(d,md);
                                break;
                            }
                        }
                    }

                    System.out.println("Your Booking is confirmed");


                }
                
                
            }
            
        }

    }


    public static  ArrayList<Slot> getAvailableSlots(String docid){

        ArrayList<Integer> sl=new ArrayList<>();

        for(Doctor i:doctors){
            if(i.doctorId.equals(docid)){
                sl=i.availableSlots;
                break;
            }
        }

        ArrayList<Slot> ans=new ArrayList<>();

        for(Slot s:slots){

            if(sl.contains(s.slotId) && s.isBooked==false) ans.add(s);
            
        }

        return ans;


    }


    public static ArrayList<Doctor> searchDoctor(String spname){

        ArrayList<Doctor> ans=new ArrayList<>();

        for(Doctor i:doctors){
            if(i.speaciality.equals(spname)) ans.add(i);
        }

        return ans;
    }



    private static ArrayList<Doctor> initializeDoctors(){

        ArrayList<Doctor> arr=new ArrayList<>();
        ArrayList<String> docnames=new ArrayList<>(Arrays.asList("A","B","C","D","E"));
        ArrayList<String> specialities=new ArrayList<>(Arrays.asList("Ortho","ENT","Cardio","Cardio","Ortho"));

        ArrayList<Appointment> appts=new ArrayList<>();

        arr.add(new Doctor("D101",docnames.get(0),specialities.get(0),appts,new ArrayList<>(Arrays.asList(1,3,5))));

        arr.add(new Doctor("D102",docnames.get(1),specialities.get(1),appts,new ArrayList<>(Arrays.asList(2,4,5))));

        arr.add(new Doctor("D103",docnames.get(2),specialities.get(2),appts,new ArrayList<>(Arrays.asList(1,4,5))));

        arr.add(new Doctor("D104",docnames.get(3),specialities.get(3),appts,new ArrayList<>(Arrays.asList(1,2))));
        
        arr.add(new Doctor("D105",docnames.get(4),specialities.get(4),appts,new ArrayList<>(Arrays.asList(4,5))));
        
        return arr;

    }



    private static ArrayList<Slot> initializeSlots(){


        int st=9;
        int et=10;

        ArrayList<Slot> slots=new ArrayList<>();
        Doctor doc=null;

        for(int i=1;i<=5;i++){

            
            slots.add(new Slot(i,st,et,doc));
            st=st+2;
            et=st+1;

        }

        return slots;

    }

    
}