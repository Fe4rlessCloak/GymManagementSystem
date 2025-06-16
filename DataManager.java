import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataManager {
    List<Member> members; // Declares a list of all members in memory
    List<Employee> employees; // Declares a list of all employees in memory

    String employeeFilePath = "Data/EmployeeAuth.csv";
    File employeeFile = new File(employeeFilePath);

    String memberFilePath = "Data/MemberDeets.csv";
    File memberFile = new File(memberFilePath);

    int nextemployeeID;
    int nextMemberID;
    // Constructor
    public DataManager(){ 
        this.members = new ArrayList<>(); // Creates a new array list
        nextMemberID = 0;

        this.employees = new ArrayList<>(); // Creates a new array list for employees
        nextemployeeID = 0;
    }

    // To Read a specific user from the array list
    public Member findMember(String userID, String userName){
        String searchId = (userID != null) ? userID.trim() : "";
        String searchName = (userName != null) ? userName.trim() : "";
        for(Member member : members){
            if(member.getMemberName().contains(searchName) && Integer.toString(member.getMemberID()).equals(searchId)){
                return member;
            }
        }
        return null;
    }




    public Employee findEmployee(String employeeID, String employeePassword){
        String searchId = (employeeID != null) ? employeeID.trim() : "";
        String searchPassword = (employeePassword != null) ? employeePassword.trim() : "";
        for(Employee employee : employees){
            if(Integer.toString(employee.getEmployeeID()).equals(searchId) && employee.getEmployeePassword().equals(searchPassword)){
                return employee;
            }
        }
        return null;
    }

    public void deleteMember(int userID){
        for(Member member : members){
            if(member.getMemberID()==(userID)){
                members.remove(member);
                break;
            }
            updateMembers();
        }
    }

    public List<Member> filterMembers(char filterType, String userID, String userName, String subscriptionType, String feesDue, String paidStatus, String caloriesBurnt, String minutesSpent){
        List<Member> filterList = new ArrayList<>();
        switch(filterType){
            case '1' -> {
                for(Member member : members){
                    if(member.getMemberID()==Integer.parseInt(userID)){
                        filterList.add(member);
                    }
                }
            }
            case '2' -> {
                for(Member member : members){
                    String lowerCaseUserName = member.getMemberName().toLowerCase();
                    String lowerCaseSearchName = userName.toLowerCase();
                    if(lowerCaseUserName.contains(lowerCaseSearchName)){
                        filterList.add(member);
                    }
                }
            }
            case '3' -> {
                for(Member member : members){
                    if(member.getMembershipType().equals(subscriptionType)){
                        filterList.add(member);
                    }
                }
            }
            case '4' -> {
                for(Member member : members){
                    if(member.getMemberFeesDue()>Integer.parseInt(feesDue)){
                        filterList.add(member);
                    }
                }
            }
            case '5' -> {
                for(Member member : members){
                    if(paidStatus.equals("1")){
                        if(member.getMemberHasPaid()==true){
                         filterList.add(member);
                        }
                    }else if(paidStatus.equals("2")){
                        if(member.getMemberHasPaid()==false){
                         filterList.add(member);
                        }
                    }
                    
                }
            }
            case '6' -> {
                for(Member member : members){
                    if(member.getMemberCaloriesBurnt()>Integer.parseInt(caloriesBurnt)){
                        filterList.add(member);
                    }
                }
            }
            case '7' -> {
                for(Member member : members){
                    if(member.getMemberHoursSpent()>Integer.parseInt(minutesSpent)){
                        filterList.add(member);
                    }
                }
            }
        }
        return filterList;
    }
    public void loadEmployees(){
        try {
            employees.clear();
            int maxEmployeeID = 0;
            BufferedReader BF = new BufferedReader(new FileReader(employeeFile));
            String line;
            while ((line = BF.readLine()) != null){
                Employee newEmployee = Employee.fromCsvString(line);
                if(newEmployee!=null){
                    employees.add(Employee.fromCsvString(line));
                }
                if(newEmployee.getEmployeeID()>maxEmployeeID){
                    maxEmployeeID = newEmployee.getEmployeeID();
                }
            }
            nextemployeeID = maxEmployeeID+1;
            BF.close();
        } catch(IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    // To Load Members into the array list
    public void loadMembers(){
        try{
            members.clear();
            int maxUserID = 0;
            BufferedReader BF = new BufferedReader(new FileReader(memberFile));
            String line;
            while ((line = BF.readLine()) != null){
                Member newMember = Member.fromCsvString(line);
                if(newMember!=null){
                    members.add(Member.fromCsvString(line));
                }
                if(newMember.getMemberID()>maxUserID){
                    maxUserID = newMember.getMemberID();
                }
            }
            nextMemberID = maxUserID+1;
            BF.close();
        }
        catch(IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    
    }

    

    public void saveMember(String memberData){  // Puts the new member in the array-list
        Member memberToAdd = Member.fromCsvString(memberData);
        members.add(memberToAdd);
        updateMembers();
    }
        /* 
        try {
            nextMemberID++;
            BufferedReader BF = new BufferedReader(new FileReader(memberFile));
            String temporaryFileLocation = "Data/MemberDeetsTemp.csv";
            File temporaryFile = new File(temporaryFileLocation);
            if(temporaryFile.exists()==false){
                temporaryFile.createNewFile();
            }
            BufferedWriter BR = new BufferedWriter(new FileWriter(temporaryFile));
            String line;
            while ((line = BF.readLine()) != null){
                BR.write(line);
                BR.write("\n");
            }
            BR.write(memberData);
            BR.close();
            BF.close();
            if(memberFile.exists()){
                memberFile.delete();
                temporaryFile.renameTo(memberFile);
            }
            
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        loadMembers();
        */
    public void updateMembers(){ // Updates the CSV Database with the updated in-memory Array List
        try {
            String temporaryFileLocation = "Data/MemberDeetsTemp.csv";
            File temporaryFile = new File(temporaryFileLocation);
            if(temporaryFile.exists()==false){
                temporaryFile.createNewFile();
            }
            BufferedWriter BR = new BufferedWriter(new FileWriter(temporaryFile));
            String line;
            for(Member memberToFind:members){
                BR.write(Member.fromStringCsv(memberToFind));
                BR.newLine();
            }
            BR.close();
            if(memberFile.exists()){
                memberFile.delete();
                temporaryFile.renameTo(memberFile);
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        
    }





    public int generateUserID(){
        return nextMemberID;
    }
    /*public int generateUserID(){
        int maxIDFound = 0;
        try {
            BufferedReader BF = new BufferedReader(new FileReader(memberFile)); 
            String[] userDetails;
            String line;
            while ((line = BF.readLine()) != null) {
                userDetails = line.split(",");
                if(Integer.parseInt(userDetails[0])>maxIDFound){
                    maxIDFound = Integer.parseInt(userDetails[0]);  
                } 
            }
        }
        catch (Exception e) {
            System.out.println("Unwanted Error");
            }
        return maxIDFound;
    } */
    
    public float recordWorkout(Member member, String workoutType, int duration, String intensity){
        int cardioCalories = 5;
        int strengthCalories = 4;
        int yogaCalories = 3;
        float caloriesBurnt = 0;
        char charWorkoutType = workoutType.charAt(0);
        switch(charWorkoutType){
            case '1'-> {caloriesBurnt = duration*cardioCalories;}
            case '2'-> {caloriesBurnt = duration*strengthCalories;}
            case '3'-> {caloriesBurnt = duration*yogaCalories;}
        }
        switch(intensity.charAt(0)){
            case '1'-> {caloriesBurnt= caloriesBurnt * 0.8f;}
            case '2'-> {caloriesBurnt= caloriesBurnt * 1.1f;}
            case '3'-> {caloriesBurnt= caloriesBurnt * 1.3f;}
        }
        return caloriesBurnt;
    }
    public List<Member> returnMemberList(){
        return this.members;
    }
}
