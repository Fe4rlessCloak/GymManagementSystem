


public class Member {
    // Different variables for our user
    int memberID;

    String memberName;
    String memberContactNumber;
    String memberEmailID;
    String membershipType;
    int memberFeesDue;
    boolean memberHasPaid;
    int memberCaloriesBurnt;
    int memberHoursSpent;


    boolean activeStatus;
    int lastActivityTimeStamp;
    // Constructor
    public Member(String memberName, String memberContactNumber, String memberEmailID, String membershipType){
        this.memberName = memberName;
        this.memberContactNumber = memberContactNumber;
        this.memberEmailID = memberEmailID;
        this.membershipType = membershipType;
        this.memberFeesDue = 0;
        this.memberCaloriesBurnt = 0;
        this.memberHoursSpent = 0;
        this.activeStatus = false;
        this.lastActivityTimeStamp = 0;
    }
    // Getters
    public boolean getActiveStatus(){
        return this.activeStatus;
    }
    public int getLastAcitivityTimestamp(){
        return this.lastActivityTimeStamp;
    }
    public int getMemberID(){
        return this.memberID;
    }
    public String getMemberName(){
        return this.memberName;
    }
    public String getMemberContactNumber(){
        return this.memberContactNumber;
    }
    public String getMemberEmailID(){
        return this.memberEmailID;
    }
    public String getMembershipType(){
        return this.membershipType;
    }
    public int getMemberFeesDue(){
        return this.memberFeesDue;
    }
    public boolean getMemberHasPaid(){
        return this.memberHasPaid;
    }
    public int getMemberCaloriesBurnt(){
        return this.memberCaloriesBurnt;
    }
    public int getMemberHoursSpent(){
        return this.memberHoursSpent;
    }

    // Setters
    public void setActive(boolean status){
        this.activeStatus = status;
    }
    public void setLastActivityTimestamp(int timeStamp){
        this.lastActivityTimeStamp = timeStamp;
    }
    public void setMemberID(int memberID){
        this.memberID = memberID;
    }
    public void setMemberFeesDue(int feesDue){
        this.memberFeesDue = feesDue; // Is used to set a member's due fees
    }
    public void setMemberHasPaid(boolean hasPaid){
        this.memberHasPaid = hasPaid; // Is used to set if a member has paid or not
    }
    public void setMemberCalories(int calories){
        this.memberCaloriesBurnt = calories; // Manually set calories (Not used manually)
    }
    public void setMemberHoursSpent(int hours){
        this.memberHoursSpent = hours; // Manually set calories (Not used)
    }
    public void increaseMemberCaloriesBurnt(int caloriesBurnt){
        this.memberCaloriesBurnt += caloriesBurnt; // Increases the number of calories you have burnt by variable 'caloriesBurnt'
    }
    public void increaseMemberHoursSpent(int hoursSpent){
        this.memberHoursSpent += hoursSpent; // Increases the number of hours you have spent by variable 'hoursSpent'
    }

    public static Member fromCsvString(String line){  // Static method that basically reads a CSV line and creates a member with those specific details and returns it to the calling method
        
        String[] memberDetails = line.split(",");
        if(line.length()<12){
            System.out.println("Error in the CSV Database");
            return null;
        }
        int memberID = Integer.parseInt(memberDetails[0]);
        String memberName  = memberDetails[1];
        String memberContactNumber = (memberDetails[2]);
        String memberEmailID = memberDetails[3];
        String membershipType = memberDetails[4];
        int memberFeesDue = Integer.parseInt(memberDetails[5]);
        boolean memberHasPaid = Boolean.parseBoolean(memberDetails[6]);
        int memberCaloriesBurnt = Integer.parseInt(memberDetails[7]);
        int memberHoursSpent = Integer.parseInt(memberDetails[8]);
        boolean memberActiveStatus = Boolean.getBoolean(memberDetails[9]);
        int lastActivityTimeStamp = Integer.parseInt(memberDetails[10]);

        Member member = new Member(memberName, memberContactNumber, memberEmailID, membershipType);
        member.setMemberID(memberID);
        member.setMemberFeesDue(memberFeesDue);
        member.setMemberHasPaid(memberHasPaid);
        member.setMemberCalories(memberCaloriesBurnt);
        member.setMemberHoursSpent(memberHoursSpent);

        member.setActive(memberActiveStatus);
        member.setLastActivityTimestamp(lastActivityTimeStamp);
        return member;
    }
    public static String fromStringCsv(Member member){ // Static method that receives a member type object and converts its properties to CSV
        if(member==null){
            System.out.println("Error in entered details!");
            return null;
        }
        return String.join(",", String.valueOf(member.getMemberID()),member.getMemberName(), String.valueOf(member.getMemberContactNumber()),member.getMemberEmailID(),member.getMembershipType(),String.valueOf(member.getMemberFeesDue()), String.valueOf(member.getMemberHasPaid()), String.valueOf(member.getMemberCaloriesBurnt()) , String.valueOf(member.getMemberHoursSpent()), String.valueOf(member.getActiveStatus()),String.valueOf(member.lastActivityTimeStamp) );

    }
    /*String memberName 0; 
    String memberContactNumber 1;
    String memberEmailID 2 ;
    String membershipType 3;
    int memberFeesDue 4;
    boolean memberHasPaid 5;
    int memberCaloriesBurnt 6;
    int memberHoursSpent 7; */
    

}
