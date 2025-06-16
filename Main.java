
import java.util.List;

// Our Semester Project

public class Main{
    public static void main(String[] args) {
        System.out.println("Welcome to Komplex Gym");
        DataManager DM = new DataManager();
        DM.loadMembers();
        DM.loadEmployees();
        SimulationManager SM = new SimulationManager();
        List<Member> memberList = DM.returnMemberList(); // Stores all existing members in a list
        ConsoleUI CUI = new ConsoleUI();

        loop: while (true) { // outerLoop is just used to reference to this part
            int userChoice = CUI.userChoice();
            if(userChoice==1){ // Member
                memberLoop: while (true) { 
                    int memberChoice = CUI.memberChoice();
                    switch (memberChoice) {
                    case 1 -> { 
                        Member member = CUI.getMemberDetails(DM.generateUserID()); // Passes the userID to Console UI to generate a new Member type object from user inputs
                        DM.saveMember(Member.fromStringCsv(member)); // Use the Data Manager's saveMember method to convert all properties of this member to CSV-style, and append it to the end of the CSV file
                    }
                    case 2 -> CUI.memberLogin(DM);
                    case 3 -> {
                        CUI.simulateWorkout(DM);
                    }
                    case 4 -> {
                        break memberLoop;
                    }
                }
            } 
            }else if(userChoice==2){ // Employee, in Progress
                boolean employeeLoginStatus = CUI.employeeLogin(DM); 
                if(employeeLoginStatus==true){
                    employeeLoop: while(true){
                        int employeeChoice = CUI.employeeMenu();
                        switch(employeeChoice){
                            case 0 -> {
                                break employeeLoop;
                            }
                            case 1 -> {
                                CUI.displayAllMembersTable(DM);
                                CUI.menuForManagingMembers(DM);
                            }
                            case 3 -> {
                                CUI.simulateMembers(DM,SM);
                            }

                        }
                    }
                    
                }
            }
            if(userChoice==3){
                break;
            }
        }
        
    }
}