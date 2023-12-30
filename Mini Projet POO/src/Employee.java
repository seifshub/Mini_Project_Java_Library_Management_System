import java.util.*;


//this class represents an employee in the library
public class Employee {
    private static long i=0;
    private long badgeID;
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private static Map<String,Employee> staff= new HashMap<>(); //this is used to store all the employees in the library
    public Employee(String name, String username, String password, String email, String address)
    {
        i++;
        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.address=address;
        this.badgeID=i;
        staff.put(username,this);
    }

    //checks if a username exists in the staff database
    public static boolean UsernameExists(String username)
    {
        return staff.containsKey(username);
    }
    public String getName() {
        return name;
    }

    public static Map<String, Employee> getStaff() {
        return staff;
    }

    public String getPassword() {
        return password;
    }
    public double getBadgeID() {
        return badgeID;
    }

    //display all the employees in the library
    public static void printStaff()
    {
        if(staff.isEmpty())
        {
            throw new NullPointerException();
        }
        for(Employee i:staff.values())
        {
            System.out.println("---->"+i);

        }
    }

    //returns a string representation of the employee
    @Override
    public String toString() {
        return  "badgeID=" + badgeID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'';
    }

    //authenticate an employee by his username and password and returns the employee instance if the authentication is successful
    public static Employee authenticateEmployee(String username, String password)
    {
        if(!staff.containsKey(username))
        {
            System.out.println("Username not found. Please try again"); //if the username is not found
            return null;
        }
        else if(!staff.get(username).getPassword().equals(password))
        {
            System.out.println("Incorrect password. Please try again"); //if the password is incorrect
            return null;
        }
        return staff.get(username); //if the username and password are correct
    }
}
