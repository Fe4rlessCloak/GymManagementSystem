import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsoleUI {
    Scanner input = new Scanner(System.in);
    
    
    // Handles all the I/O
    public ConsoleUI(){
        
    }


    
    public int userChoice(){
        System.out.println("What do you wish to interact as?\nMember (Press 1)\tEmployee (Press 2)");
        int selectionChoice;
        while (true) { 
            selectionChoice = this.input.nextInt();
            this.input.nextLine();
        if(selectionChoice==1 || selectionChoice==2){
            break;
            }
        System.out.println("Kindly select a valid option!");
        }
        return selectionChoice;
    } 

    public void memberLogin(DataManager DM){
        System.out.println("We'll need you to enter your credentials for this!");
        System.out.println("What is your username?");
        String userName = input.nextLine();
        System.out.println("What is your userID?");
        String userID;
        while(true){
            userID = this.input.nextLine();
            boolean isDigit = true;
            for(int i = 0; i < userID.length();i++){
                if(userID.charAt(i)< '0' && userID.charAt(i)>'9'){
                    isDigit = false;
                }
            }
            if(isDigit==true){
                break;
            }
            System.out.println("Enter a valid input please!");
        }
        Member requiredMember = DM.findMember(userID, userName);
        if(requiredMember==null){
            System.out.println("The requested user cannot be found");
        }else {
            System.out.println("User Found!");
            System.out.println("Name: " + requiredMember.getMemberName());
            System.out.println("Contact Number: " + requiredMember.getMemberContactNumber());
            System.out.println("Email ID: " + requiredMember.getMemberEmailID());
            System.out.println("Membership Type: " + requiredMember.getMembershipType());
            System.out.println("Fees Due: " +requiredMember.getMemberFeesDue() +" Rupees" );
            System.out.println("Have you paid your fees: " + requiredMember.getMemberHasPaid());
            System.out.println("Calories burnt this week: " + requiredMember.getMemberCaloriesBurnt() + " calories");
            int memberHoursSpent = requiredMember.getMemberHoursSpent() / 60;
            int memberMinutesSpent = (requiredMember.getMemberHoursSpent() / 60) % 60;
            System.out.println("Time spent at the gym this week: " + memberHoursSpent + " hours " + memberMinutesSpent + " minutes");
        }

        
    }


     /*String memberName 0; 
    int memberContactNumber 1;
    String memberEmailID 2 ;
    String membershipType 3;
    int memberFeesDue 4;
    boolean memberHasPaid 5;
    int memberCaloriesBurnt 6;
    int memberHoursSpent 7; */




    public int memberChoice(){
        int memberChoice;
        System.out.println("What do you wish to perform?\n1) Register\n2) View your stats\n3) Workout\n4) Exit to previous screen");
        while (true) { 
            memberChoice = this.input.nextInt();
            this.input.nextLine();
            if(memberChoice==1 || memberChoice ==2 || memberChoice ==3 || memberChoice == 4){
                break;
            }
            System.out.println("Kindly select a valid option!");
        }
        return memberChoice;
    }
    
    public boolean employeeLogin(DataManager DM){
        
        int employeeID;
        String employeePassword;
        System.out.println("Please enter your employee credentials!");
        System.out.println("Enter your employee ID");
        while(true){
            try {
                employeeID = this.input.nextInt();
                this.input.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Kindly enter an integer value");
            }
            
        }
        System.out.println("Enter your employee password");
        employeePassword = this.input.nextLine();
        Employee requiredEmployee = DM.findEmployee(Integer.toString(employeeID), employeePassword);
        if(requiredEmployee==null){
            System.out.println("Incorrect credentials!");
            return false;   
        }else {
            System.out.println("Welcome " + requiredEmployee.getEmployeeName());

        }
        return true;    
        
        
    }
    



    public void simulateWorkout(DataManager DM){
        System.out.println("We'll need you to enter your credentials for this!");
        System.out.println("What is your username?");
        String userName = input.nextLine();
        System.out.println("What is your userID?");
        String userID;
        while(true){
            userID = this.input.nextLine();
            boolean isDigit = true;
            for(int i = 0; i < userID.length();i++){
                if(userID.charAt(i)< '0' && userID.charAt(i)>'9'){
                    isDigit = false;
                }
            }
            if(isDigit==true){
                break;
            }
            System.out.println("Enter a valid input please!");
        }
        Member member = DM.findMember(userID, userName);
        if(member!=null){
            String workoutChoice;
            System.out.println("Welcome " + member.getMemberName());
            System.out.println("Kindly select your workout type:\n1) Cardio\n2) Strength\n3) Yoga");
            while (true) { 
                workoutChoice = this.input.nextLine();
                if(workoutChoice.equals("1") || workoutChoice.equals("2") || workoutChoice.equals("3")){
                    break;
                }
                System.out.println("Kindly enter a valid input!");
            }
            System.out.println("Kindly select the intensity\n1) Low\n2) Medium\n3) High");
            String workoutIntensity;
            while (true) { 
                workoutIntensity = this.input.nextLine();
                if(workoutIntensity.equals("1") || workoutIntensity.equals("2") || workoutIntensity.equals("3")){
                    break;
                }
                System.out.println("Kindly enter a valid input");
            }
            int workoutDuration;
            System.out.println("How long do you wish to workout for in minutes?\n");
            while (true) { 
                try {
                    workoutDuration = this.input.nextInt();
                    this.input.nextLine();
                    if(workoutDuration>200){
                        System.out.println("While exercising it's important, it's essential you don't overdo it.");
                    }
                    if(workoutDuration>400){
                        System.out.println("Kindly enter a valid entry between 1 and 500 (minutes)");
                        continue;
                    }
                    break;
                }catch (InputMismatchException e) {
                    System.out.println("Kindly enter an integer value only");
                }
            }
            float caloriesBurnt = DM.recordWorkout(member,workoutChoice,workoutDuration,workoutIntensity);
            System.out.println("You've succesfully burnt " + caloriesBurnt + " calories in " + workoutDuration + " minutes");
            member.increaseMemberCaloriesBurnt((int)caloriesBurnt);
            member.increaseMemberHoursSpent(workoutDuration);
            DM.updateMembers();
        }else{
            System.out.println("The required user cannot be found");
        }
    }

    // Method that receives the sequential userID from Main, takes user input, and returns a new Member type object
    public Member getMemberDetails(int memberID){
        System.out.println("Enter the user's name");
        String memberName = this.input.nextLine();
        System.out.println("Enter your contact number");
        String memberContactNumber;
        while (true) { 
            memberContactNumber = this.input.nextLine();
            String PHONE_REGEX = "03\\d{2}[-.\\s]?\\d{7}";
            Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
            Matcher phoneMatcher = PHONE_PATTERN.matcher(memberContactNumber);
            if(memberContactNumber!=null && phoneMatcher.matches() == true){
                break;
            }
            System.out.println("Enter a valid phone number please!");
        }
        System.out.println("Enter your email address");
        boolean isEmailValid = false;

        // Use regex to check if the entered email is correct or not
        String EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"; 
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX); 
        String memberEmailID;
        while(true){
            memberEmailID = this.input.nextLine();
            Matcher emailMatcher = EMAIL_PATTERN.matcher(memberEmailID);
            if(memberEmailID!=null && emailMatcher.matches()==true){
                break;
            }
            System.out.println("Enter a valid email address please!");
        }
        String membershipType;
        while (true) { 
            System.out.println("Select the membership type\nStandard (Enter 1) or Premium (Enter 2)");
            membershipType = this.input.nextLine();
            if(!(membershipType).equals("1") && !membershipType.equals("2")){
                System.out.println("Enter a correct type please!");
                continue;
            }
            break;
        }
        String payTime;
        System.out.println("Would you like to pay now or later?\nPress 1 to pay now (" + Integer.parseInt(membershipType)*500 + "PKR )\tPress 2 to pay later");
        while (true) { 
            payTime = input.nextLine();
            if(!"1".equals(payTime) && !"2".equals(payTime)){
                System.out.println("Enter a correct type please!");
                continue;
            }
            break;
        }
        boolean memberHasPaid;
        int memberFeesDue;
        if(payTime.equals("1")){
            memberHasPaid = true;
            memberFeesDue = 0;
        }else{
            memberHasPaid = false;
            memberFeesDue = Integer.parseInt(membershipType)*500;
        }
        Member newMember = new Member(memberName, memberContactNumber, memberEmailID, membershipType);
        newMember.setMemberFeesDue(memberFeesDue);
        newMember.setMemberHasPaid(memberHasPaid);
        newMember.setMemberID(memberID);
        return newMember;
    }



   
}
