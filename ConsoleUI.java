import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsoleUI {
    Scanner input = new Scanner(System.in);
    
    
    // Handles all the I/O
    public ConsoleUI(){
        
    }
    public void simulateMembers(DataManager DM, SimulationManager SM){
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.PURPLE, "\n--- WELCOME TO MEMBER SIMULATION ---"));
        
        int selectionChoice;
        while (true) { 
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Press (1) to start the simulation with one-hour time-steps\nPress (2) to stop the simulation\nPress (3) to exit"));
            try {
                selectionChoice = this.input.nextInt();
                this.input.nextLine();
                if(selectionChoice==1){
                    SM.advanceTime(DM);
                    System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Advancing Time.........."));
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                        this.input.nextLine();
                    }
                    printTable(DM.members);
                }
                if(selectionChoice==2){
                    System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "\n--- EXITING SIMULATION ---"));
                    SM.stopSimulation(DM);
                    DM.updateMembers();
                    return;
                }
                if(selectionChoice==3){
                    return;
                }
                System.out.println("Kindly select a valid option!");
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid input. Please enter a number (1,2 or 3)."));
                this.input.nextLine();
            }
        }
        


    }
    public int employeeMenu() {
        
        String employeeChoice;
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.PURPLE, "\n--- EMPLOYEE MAIN MENU ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "What do you wish to perform?"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  1) Manage Members"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  2) Simulation"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_RED, "  0) Exit Application")); // Added exit option for clarity
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.PURPLE, "--------------------------"));

        while (true) {
            System.out.print(ConsoleColors.applyColor(ConsoleColors.YELLOW, "Enter your choice (1-2 or 0 to Exit): "));
            employeeChoice = this.input.nextLine();

            if (employeeChoice.equals("1") || employeeChoice.equals("2") || employeeChoice.equals("0")) {
                break;
            }
            System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid choice. Please enter a number from the menu (1, 2, or 0)."));
            this.input.nextLine(); 
        }
        return Integer.parseInt(employeeChoice);
    }

    
    public int userChoice(){
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.CYAN, "\n--- LOGIN SELECTION ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "What do you wish to interact as?"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.GREEN, "  1) Member"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BLUE, "  2) Employee"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "  3) Exit the program"));
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.CYAN, "-----------------------"));
        int selectionChoice;
        while (true) { 
            try {
                selectionChoice = this.input.nextInt();
                this.input.nextLine();
                if(selectionChoice==1 || selectionChoice==2 || selectionChoice == 3){
                    break;
                }
                System.out.println("Kindly select a valid option!");
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid input. Please enter a number (1, 2 or 3)."));
                this.input.nextLine();
            }
            
        }
        return selectionChoice;
    } 

    public void memberLogin(DataManager DM){
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "\n--- MEMBER LOGIN ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "We'll need you to enter your credentials for this!"));

        System.out.print(ConsoleColors.applyColor(ConsoleColors.CYAN, "What is your username? "));
        String userName = input.nextLine();
        System.out.print(ConsoleColors.applyColor(ConsoleColors.CYAN, "What is your User ID? "));
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
            System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid User ID. Please enter a valid number (digits only)."));
            System.out.print(ConsoleColors.applyColor(ConsoleColors.CYAN, "Re-enter your User ID: ")); // Re-prompt inside loop
        }
        Member requiredMember = DM.findMember(userID, userName);
        if(requiredMember==null){
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.RED, "The requested user cannot be found with the provided credentials."));
        }else {
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "\nUser Found! Welcome, " + requiredMember.getMemberName() + "!"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "------------------------------------"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "Name: " + requiredMember.getMemberName()));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "Contact Number: " + requiredMember.getMemberContactNumber()));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "Email ID: " + requiredMember.getMemberEmailID()));
            String memberShipType = "";
            if(requiredMember.getMembershipType().equals("1")){
                memberShipType = "Standard";
            }else {
                memberShipType = "Premium";
            }
            System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_CYAN, "Membership Type: " + memberShipType));
            String feesDueMessage = "Fees Due: " + requiredMember.getMemberFeesDue() + " Rupees";
             if (requiredMember.getMemberFeesDue() > 0) {
                System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.RED, feesDueMessage));
            } else {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.GREEN, feesDueMessage));
            }
        
            String paidStatusMessage = "Have you paid your fees: " + (requiredMember.getMemberHasPaid() ? "Yes" : "No");
            if (requiredMember.getMemberHasPaid()) {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.GREEN, paidStatusMessage));
            } else {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.YELLOW, paidStatusMessage));
            }
        
            System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_MAGENTA, "Calories burnt this week: " + requiredMember.getMemberCaloriesBurnt() + " calories"));
            int totalMinutesSpent = requiredMember.getMemberHoursSpent(); // Assuming this is total minutes now
            int memberHoursSpent = totalMinutesSpent / 60;
            int memberMinutesSpent = totalMinutesSpent % 60;
            System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_MAGENTA, "Time spent at the gym this week: " + memberHoursSpent + " hours " + memberMinutesSpent + " minutes"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "------------------------------------"));
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
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "\n--- MEMBER MENU ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "What do you wish to perform?"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  1) Register (New Member)")); // Clarified
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  2) View Your Stats"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  3) Record a Workout")); // Clarified
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_RED, "  4) Exit to Previous Screen")); // Clarified exit option
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "-------------------"));
        while (true) { 
            try {
                memberChoice = this.input.nextInt();
                this.input.nextLine();
                if(memberChoice==1 || memberChoice ==2 || memberChoice ==3 || memberChoice == 4){
                    break;
                }else{
                    System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid choice. Please enter a number between 1 and 4."));
                }
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid input. Please enter a number (1-4)."));
                this.input.nextLine();
            }
            
        }
        return memberChoice;
    }
    
    public boolean employeeLogin(DataManager DM){
        System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Enter \"12345\" if you don't wish to login."));

        int employeeID;
        String employeePassword;
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "\n--- EMPLOYEE LOGIN ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "Please enter your employee credentials."));
        while(true){
            try {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Enter your Employee ID (number):"));
                employeeID = this.input.nextInt();
                this.input.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Kindly enter an integer!"));
                this.input.nextLine();
            }
            
        }
        if(employeeID==12345){
            return false;
        }
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Enter your Employee Password:"));
        employeePassword = this.input.nextLine();
        Employee requiredEmployee = DM.findEmployee(Integer.toString(employeeID), employeePassword);
        if(requiredEmployee==null){
            System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid Employee ID or Password."));
            return false;   
        }else {
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "Login successful! Welcome, " + requiredEmployee.getEmployeeName() + "!"));

        }
        return true;    
        
        
    }

    public void menuForManagingMembers(DataManager DM){
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "--- MEMBER MANAGEMENT MENU ---")); 
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  1) Search by ID"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  2) Filter by Name"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  3) Filter by Subscription Type"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  4) Filter by Fees Due"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  5) Filter by Paid Status"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  6) Filter by Calories Burnt"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "  7) Filter by Minutes Spent This Week"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_RED, "  8) Delete Member")); // Highlight delete in red
        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_BLACK, "  0) Back to Main Menu")); // Highlight exit in a dimmer color
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "------------------------------")); // Separator line

        String managingMemberChoice;
        while(true){
            managingMemberChoice = this.input.nextLine();
            if( managingMemberChoice.equals("0")|| managingMemberChoice.equals("1") || managingMemberChoice.equals("2") || managingMemberChoice.equals("3") || managingMemberChoice.equals("4") || managingMemberChoice.equals("5") || managingMemberChoice.equals("6") || managingMemberChoice.equals("7") || managingMemberChoice.equals("8")){
                break;
            }
            System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Enter a valid input please"));
        }
        List<Member> filterList = new ArrayList<>();
        switch(managingMemberChoice.charAt(0)){
            case '0' -> {
                return;
            }
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
                        this.input.nextLine();
                        
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
                        this.input.nextLine();
                    }
                    
                }
                filterList = DM.filterMembers(managingMemberChoice.charAt(0), null, null, null, String.valueOf(feesDue), null, null, null);
                printTable(filterList);
            }
            case '5' -> {
                System.out.println("What members do you wish to view\n1) Paid\t2) Unpaid");
                String memberPaidType;
                while (true) { 
                    memberPaidType = this.input.nextLine();
                    if(memberPaidType.equals("1") || memberPaidType.equals("2")){
                        break;
                    }
                    System.out.println("Invalid input");
                    this.input.nextLine();
                }
                filterList = DM.filterMembers(managingMemberChoice.charAt(0), null, null, null, null, memberPaidType, null, null);
                printTable(filterList);
            }
            case '6' -> {
                System.out.println("Enter the calorie count, above which you wish to show users");
                int calorieCount;
                while (true) { 
                    try {
                        calorieCount = this.input.nextInt();
                        this.input.nextLine();
                        break;
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                        this.input.nextLine();
                    }
                    
                }
                filterList = DM.filterMembers(managingMemberChoice.charAt(0), null, null, null, null, null, String.valueOf(calorieCount), null);
                printTable(filterList);
            }
            case '7' -> {
            System.out.println("Enter the minutes count, above which you wish to show users");
            int minutesCount;
                while (true) { 
                    try {
                        minutesCount = this.input.nextInt();
                        this.input.nextLine();
                        break;
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                        this.input.nextLine();
                    }
                    
                }
                filterList = DM.filterMembers(managingMemberChoice.charAt(0), null, null, null, null, null, null, String.valueOf(minutesCount));
                printTable(filterList);
            }
            case '8' -> {
                System.out.println("Enter the User ID of the member you wish to remove:");
                int userID;
                while (true) { 
                    try {
                        userID = this.input.nextInt();
                        this.input.nextLine();
                        break;
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                        this.input.nextLine();
                    }
                    
                }
                boolean memberDeleted = DM.deleteMember(userID);
                if(memberDeleted==true){
                    System.out.println("The requested user has succesfully been removed");
                }else{
                    System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "The requested user cannot be found"));
                }
                
                printTable(DM.members);

            }
        }

    }
    
    public void printTable(List<Member> allMembers){
        if(allMembers.isEmpty()){
            System.out.print(ConsoleColors.applyColor(ConsoleColors.RED, "The requested user(s) cannot be found"));
        }else{
            int idWidth = 5;
            int nameWidth = 20;
            int typeWidth = 15;
            int feesWidth = 10;
            int paidWidth = 8;
            int caloriesWidth = 12;
            int hoursWidth = 10;
            int activeStatus = 7;
            // Print table header
            String headerFormat = "| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + typeWidth + "s | %" + feesWidth + "s | %-" + paidWidth + "s | %" + caloriesWidth + "s | %" + hoursWidth + "s |%" + activeStatus + "s | %n";
            String separator = "+-------+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(typeWidth + 2) + "+" + "-".repeat(feesWidth + 2) + "+" + "-".repeat(paidWidth + 2) + "+" + "-".repeat(caloriesWidth + 2) + "+" + "-".repeat(hoursWidth + 2) + "+" + "-".repeat(activeStatus+2) + "+%n";

            System.out.printf(separator);
            System.out.printf(headerFormat, "ID", "Name", "Type", "Fees Due", "Paid?", "Calories", "Minutes","Active");
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
                    String.valueOf(member.getMemberHoursSpent()),
                    String.valueOf(member.getActiveStatus())
                );
            }
            System.out.printf(separator);

        }
        
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
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "\n--- RECORD YOUR WORKOUT ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "We'll need you to confirm your identity for this!"));
        System.out.print(ConsoleColors.applyColor(ConsoleColors.CYAN, "What is your username? "));
        String userName = input.nextLine();
        
        String userID;
        System.out.print(ConsoleColors.applyColor(ConsoleColors.CYAN, "What is your User ID? "));
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
             System.out.print(ConsoleColors.applyColor(ConsoleColors.RED, "Enter a valid input please"));
        }
        Member member = DM.findMember(userID, userName);
        if(member!=null){
            String workoutChoice;
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "\nWelcome, " + member.getMemberName() + "! Let's record your workout."));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "\nKindly select your workout type:"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  1) Cardio"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  2) Strength Training"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  3) Yoga"));
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "------------------------"));
            while (true) { 
                workoutChoice = this.input.nextLine();
                if(workoutChoice.equals("1") || workoutChoice.equals("2") || workoutChoice.equals("3")){
                    break;
                }
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid workout type. Please enter 1, 2, 3, or 4."));
            }
            System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "\nKindly select the intensity:"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  1) Low"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  2) Medium"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  3) High"));
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "----------------------"));
            String workoutIntensity;
            while (true) { 
                workoutIntensity = this.input.nextLine();
                if(workoutIntensity.equals("1") || workoutIntensity.equals("2") || workoutIntensity.equals("3")){
                    break;
                }
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid intensity. Please enter 1, 2, or 3."));
            }
            int workoutDuration;
            System.out.println("How long do you wish to workout for in minutes?\n");
            while (true) { 
                try {
                    workoutDuration = this.input.nextInt();
                    this.input.nextLine();
                    if(workoutDuration>200){
                         System.out.println(ConsoleColors.applyColor(ConsoleColors.YELLOW, "While exercising is important, it's essential you don't overdo it."));
                    }
                    if(workoutDuration>400 || workoutDuration <0){
                        System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Kindly enter a valid duration between 1 and 500 minutes."));
                        continue;
                    }
                    break;
                }catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Kindly enter an integer."));
                    this.input.nextLine();
                }
            }
            float caloriesBurnt = DM.recordWorkout(member,workoutChoice,workoutDuration,workoutIntensity);
            System.out.println(ConsoleColors.applyColor(ConsoleColors.GREEN, "You've burnt " + caloriesBurnt + " calories in " + workoutDuration + " minutes."));
            member.increaseMemberCaloriesBurnt((int)caloriesBurnt);
            member.increaseMemberHoursSpent(workoutDuration);
            DM.updateMembers();
        }else{
            System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.RED, "The user with the provided credentials cannot be found."));
        }
    }

    // Method that receives the sequential userID from Main, takes user input, and returns a new Member type object
    public Member getMemberDetails(int memberID){
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.BLUE, "\n--- NEW MEMBER REGISTRATION ---"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "Please provide the following details for the new member"));
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Enter Member's Name:"));
        String memberName = this.input.nextLine();
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Enter Contact Number (e.g., 03XX-XXXXXXX or +923XX-XXXXXXX):"));
        String memberContactNumber;
        while (true) { 
            memberContactNumber = this.input.nextLine();
            String PHONE_REGEX = "03\\d{2}[-.\\s]?\\d{7}";
            Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
            Matcher phoneMatcher = PHONE_PATTERN.matcher(memberContactNumber);
            if(memberContactNumber!=null && phoneMatcher.matches() == true){
                break;
            }
            System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid phone number format. Please enter a valid 11-digit Pakistani number (e.g., 03XX-XXXXXXX)."));
        }
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Enter your email address"));
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
            System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Invalid email address format. Please try again."));
        }
        String membershipType;
        while (true) { 
            System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_WHITE, "\nSelect the Membership Type:"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  1) Standard"));
            System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  2) Premium"));
            membershipType = this.input.nextLine();
            if(!(membershipType).equals("1") && !membershipType.equals("2")){
                System.out.println(ConsoleColors.applyColor(ConsoleColors.RED, "Enter a correct type please."));
                continue;
            }
            break;
        }
        String payTime;
        System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "Would you like to pay now or later?\nPress 1 to pay now (" + Integer.parseInt(membershipType)*500 + "PKR )\tPress 2 to pay later"));
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
        System.out.println(ConsoleColors.applyBoldColor(ConsoleColors.GREEN, "Successfully registered " + newMember.getMemberName() + " with ID: " + newMember.getMemberID() + "!"));
        return newMember;
    }



   
}
