import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsoleUI {
    Scanner input = new Scanner(System.in);
    
    
    // Handles all the I/O
    public ConsoleUI(){
        
    }

    public int employeeMenu() {
        String employeeChoice;
        System.out.println("What do you wish to perform?");
        System.out.println("1) Manage members");
        System.out.println("2) See gym inventory");
        System.out.println("3) Financial situation");

        while (true) {
            System.out.print("Enter a choice: ");
            employeeChoice = this.input.nextLine();

            if (employeeChoice.equals("1") || employeeChoice.equals("2") || employeeChoice.equals("3")) {
                break;
            }
            System.out.println("Kindly enter an integer value (1, 2, or 3)");
            this.input.nextLine(); 
        }
        return Integer.parseInt(employeeChoice);
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
                this.input.nextLine();
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

    public void menuForManagingMembers(DataManager DM){
        System.out.println("Managing menu\n1) Search by ID\n2) Filter by name\n3) Filter by subscription type\n4) Filter by fees due\n5) Filter by paid status\n6) Filter by calories burnt\n7) Filter by minutes spent this week\n8) Delete Member");
        String managingMemberChoice;
        while(true){
            managingMemberChoice = this.input.nextLine();
            if(managingMemberChoice.equals("1") || managingMemberChoice.equals("2") || managingMemberChoice.equals("3") || managingMemberChoice.equals("4") || managingMemberChoice.equals("5") || managingMemberChoice.equals("6") || managingMemberChoice.equals("7") || managingMemberChoice.equals("8")){
                break;
            }
            System.out.println("Enter a valid input please!");
        }
        List<Member> filterList = new ArrayList<>();
        switch(managingMemberChoice.charAt(0)){
            case '1' -> {
                System.out.println("Enter the ID you wish to search:");
                int memberSearchID;
                while (true) { 
                    try {
                        memberSearchID = this.input.nextInt();
                        this.input.nextLine();
                        break;
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                        
                    }
                    
                }

                filterList = DM.filterMembers(managingMemberChoice.charAt(0), String.valueOf(memberSearchID), null, null, null, null, null, null);
                printTable(filterList);
            }
            case '2' -> {
                System.out.println("Enter the name you wish to filter by:");
                String membersearchName = this.input.nextLine();
                filterList = DM.filterMembers(managingMemberChoice.charAt(0), null, membersearchName, null, null, null, null, null);
                printTable(filterList);
            }
            case '3' -> {
                System.out.println("Enter the subscription type you wish to filter by:1) Standard\t2) Premium");
                String memberSubscriptionType;
                while (true) { 
                    memberSubscriptionType = this.input.nextLine();
                    if(memberSubscriptionType.equals("1") || memberSubscriptionType.equals("2")){
                        break;
                    }
                    System.out.println("Invalid input");
                }
                filterList = DM.filterMembers(managingMemberChoice.charAt(0), null, null, memberSubscriptionType, null, null, null, null);
                printTable(filterList);
            }
            case '4' -> {
                System.out.println("Enter the value for due fees, above which you wish to show users");
                int feesDue;
                while (true) { 
                    try {
                        feesDue = this.input.nextInt();
                        this.input.nextLine();
                        break;
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                        
                    }
                    
                }
            }
        }

    }
    public void printTable(List<Member> allMembers){
        int idWidth = 5;
        int nameWidth = 20;
        int typeWidth = 15;
        int feesWidth = 10;
        int paidWidth = 8;
        int caloriesWidth = 12;
        int hoursWidth = 10;

        // Print table header
        String headerFormat = "| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + typeWidth + "s | %" + feesWidth + "s | %-" + paidWidth + "s | %" + caloriesWidth + "s | %" + hoursWidth + "s |%n";
        String separator = "+-------+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(typeWidth + 2) + "+" + "-".repeat(feesWidth + 2) + "+" + "-".repeat(paidWidth + 2) + "+" + "-".repeat(caloriesWidth + 2) + "+" + "-".repeat(hoursWidth + 2) + "+%n";

        System.out.printf(separator);
        System.out.printf(headerFormat, "ID", "Name", "Type", "Fees Due", "Paid?", "Calories", "Minutes");
        System.out.printf(separator);

        // Print each member's data
        for (Member member : allMembers) {
            System.out.printf(
                headerFormat,
                String.valueOf(member.getMemberID()),
                truncateString(member.getMemberName(), nameWidth),
                truncateString(member.getMembershipType(), typeWidth),
                String.valueOf(member.getMemberFeesDue()),
                (member.getMemberHasPaid() ? "Yes" : "No"), // Display "Yes" or "No"
                String.valueOf(member.getMemberCaloriesBurnt()),
                String.valueOf(member.getMemberHoursSpent())
            );
        }
        System.out.printf(separator);
    }

    public void displayAllMembersTable(DataManager DM){
        List<Member> allMembers = DM.members;
        if(allMembers.isEmpty()){
            System.out.println("No members to display");
        }
        printTable(allMembers);
        return;
        
    }
    private String truncateString(String text, int maxWidth) {
        if (text == null) {
            return "";
        }
        if (text.length() > maxWidth) {
            return text.substring(0, maxWidth - 3) + "..."; // Truncate and add ellipsis
        }
        return text;
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
