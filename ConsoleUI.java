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

    public void memberLogin(){
        System.out.println("We'll need you to enter your credentials for this!");
        System.out.println("What is your username?");
        String userName = input.nextLine();

    }



    public int memberChoice(){
        int memberChoice;
        System.out.println("What do you wish to perform?\n1) Register\n2) View your stats\n3) Exit to previous screen");
        while (true) { 
            memberChoice = this.input.nextInt();
            this.input.nextLine();
            if(memberChoice==1 || memberChoice ==2 || memberChoice ==3){
                break;
            }
            System.out.println("Kindly select a valid option!");
        }
        return memberChoice;
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



    /*String memberName 0; 
    int memberContactNumber 1;
    String memberEmailID 2 ;
    String membershipType 3;
    int memberFeesDue 4;
    boolean memberHasPaid 5;
    int memberCaloriesBurnt 6;
    int memberHoursSpent 7; */

}
