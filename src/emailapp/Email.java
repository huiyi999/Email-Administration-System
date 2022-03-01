package emailapp;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Email {
    public Scanner s = new Scanner(System.in);

    private String fname; // first name
    private String lname; // last name
    private String dept;
    private String email;
    private String password;
    private int mailCapacity = 500;
    private String alter_email;

    // ctor
    public Email(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;

        System.out.println("New employee: " + this.fname + " " + this.lname + "\n");
        this.dept = setDept();
        this.email = generateEmail();
        this.password = generatePassword(8);
    }

    private String generateEmail() {
        return this.fname.toLowerCase() + "." + this.lname.toLowerCase() + "@" + this.dept.toLowerCase() + ".company.com";
    }

    // set department
    private String setDept() {
        System.out.println(
                "Department codes:\n1 for Sales\n2 for Development\n3 for Accounting\n0 for None");
        boolean flag = false;
        do {
            System.out.print("Enter Department Code: ");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    return "Sales";
                case 2:
                    return "Development";
                case 3:
                    return "Accounting";
                case 0:
                    return "None";
                default:
                    System.out.println("**Invalid input**");
            }
        } while (!flag);
        return null;
    }

    // generate a random password
    private static final int MIN_NUMBER = 33;
    private static final int MAX_NUMBER = 126;
    private static final int BOUND = MAX_NUMBER - MIN_NUMBER + 1;
    // char[33,126]，means numbers, capital chars, small chars and special symbols

    private String generatePassword(int length) {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char value = (char) (random.nextInt(BOUND) + MIN_NUMBER);
            builder.append(value);
        }
        return builder.toString();

//        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
//        String numbers = "0123456789";
//        String symbols = "~!@#$%^&*()_+{}|<>?";
//        String values = Capital_chars + Small_chars + numbers + symbols;
//
//        String password = "";
//        for (int i = 0; i < length; i++) {
//            password = password + values.charAt(r.nextInt(values.length()));
//        }
//        return password;

    }

    // update the pwd
    public void setPassword(){
        boolean flag = false;
        do {
            System.out.print("Are you sure to update the password? (Y/N) : ");
            char choice = s.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                flag = true;
                System.out.print("Enter current password: ");
                String temp = s.next();
                if (temp.equals(this.password)) {
                    System.out.println("Enter new password: ");
                    this.password = s.next();
                    System.out.println("PASSWORD CHANGED SUCCESSFULLY!");
                } else {
                    System.out.println("You cannot enter the same password!");
                }
            } else if (choice == 'N' || choice == 'n') {
                flag = true;
                System.out.println("PASSWORD CHANGE CANCELED!");
            } else {
                System.out.println("***Invalid input***");
            }
        } while (!flag);
    }

    // Set the mailbox capacity
    public void setMailCap() {
        System.out.println("Current capacity = " + this.mailCapacity + "mb");
        System.out.print("Enter new capacity: ");
        this.mailCapacity = s.nextInt();
        System.out.println("MAILBOX CAPACITY CHANGED SUCCESSFULLY!");

    }

    // Set the alternate email
    public void alternate_email() {
        System.out.print("Enter new alternate email: ");
        this.alter_email = s.next();
        System.out.println("ALTERNATE EMAIL SET SUCCESSFULLY!");
    }

    // Displaying the employee's information
    public void getInfo() {
        System.out.println("Name: " + this.fname + " " + this.lname);
        System.out.println("Department: " + this.dept);
        System.out.println("Email: " + this.email);
        System.out.println("Password: " + this.password);
        System.out.println("Mailbox capacity: " + this.mailCapacity + "mb");
        System.out.println("Alter email: " + this.alter_email);
    }

    public void storeFile() {
        try {
            FileWriter in = new FileWriter("./Info.txt");
            in.write("First Name: " + this.fname+"\n");
            in.append("Last Name: " + this.lname+"\n");
            in.append("Email: " + this.email+"\n");
            in.append("Password: " + this.password+"\n");
            in.append("Capacity: " + this.mailCapacity+"\n");
            in.append("Alternate mail: " + this.alter_email+"\n");
            in.close();
            System.out.println("Stored..");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readFile() {
        try {
            FileReader f1 = new
                    FileReader("./Info.txt");
            int i;
            while ((i = f1.read()) != -1) {
                System.out.print((char) i);
            }
            f1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

    }
}
