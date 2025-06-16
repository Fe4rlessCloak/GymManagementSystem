public class Employee {
    int employeeID;
    String employeeName;
    String employeePassword;
    String employeeRole;


    public Employee(int employeeID, String employeeName, String employeePassword, String employeeRole){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeRole = employeeRole;
    }

    // Getter
    public int getEmployeeID(){
        return this.employeeID;
    }
    public String getEmployeeName(){
        return this.employeeName;
    }
    public String getEmployeePassword(){
        return this.employeePassword;
    }
    public String getEmployeeRole(){
        return this.employeeRole;
    }
    // Setter
    public void setEmployeeID(int employeeID){
        this.employeeID = employeeID;
    }
    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }
    public void setEmployeePassword(String employeePassword){
        this.employeePassword = employeePassword;
    }


    public static Employee fromCsvString(String line){
        String[] employeeDetails = line.split(",");
        if(line.length()<4){
            System.out.println("Error in the CSV Database");
            return null;
        }
        int employeeID = Integer.parseInt(employeeDetails[0]);
        String employeeName = employeeDetails[1];
        String employeePassword = employeeDetails[2];
        String employeeRole = employeeDetails[3];
        Employee newEmployee = new Employee(employeeID, employeeName, employeePassword, employeeRole);
        return newEmployee;
    }

    public static String fromStringCsv(Employee newEmployee){ // Static method that receives a member type object and converts its properties to CSV
        if(newEmployee==null){
            System.out.println("Error in entered details!");
            return null;
        }
        return String.join(",", String.valueOf(newEmployee.getEmployeeID()),newEmployee.getEmployeePassword(),newEmployee.getEmployeeRole(),newEmployee.employeeRole );

    }
}
