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
    String memberFilePath = "C:\\Users\\Abdullah\\Documents\\Project\\GymManagementSystem\\Data\\MemberDeets.csv";
    File memberFile = new File(memberFilePath);
    int nextMemberID;
    // Constructor
    public DataManager(){ 
        this.members = new ArrayList<>(); // Creates a new array list
        nextMemberID = 0;
    }

    // To Read a specific user from the array list
    public Member findMember(String userID, String userName){
        String searchId = (userID != null) ? userID.trim() : "";
        String searchName = (userName != null) ? userName.trim() : "";
        for(Member member : members){
            if(member.getMemberName().contains(searchName) || Integer.toString(member.getMemberID()).equals(searchId)){
                return member;
            }
        }
        return null;
    }
    // To Load Members into the array list
    public void loadMembers(){
        try{
            BufferedReader BF = new BufferedReader(new FileReader(memberFile));
            String line;
            while ((line = BF.readLine()) != null){
                Member newMember = Member.fromCsvString(line);
                if(newMember!=null){
                    members.add(Member.fromCsvString(line));
                }
            }
            BF.close();
        }
        catch(IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    
    }
     

    public void saveMember(String memberData){
        try {
            nextMemberID++;
            BufferedReader BF = new BufferedReader(new FileReader(memberFile));
            String temporaryFileLocation = "C:\\\\Users\\\\Abdullah\\\\Documents\\\\Project\\\\GymManagementSystem\\\\Data\\\\MemberDeetsTemp.csv";
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
    
    
    public List<Member> returnMemberList(){
        return this.members;
    }
}
