package options;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import sql.SQL;

public class Customer {
    
    /**
     * This variable is used as a key for which string means what in the
     * customer array value of the map.
     */
    public static final String[] CUSTOMER_ORDER_KEY = {"First Name",
            "Last Name", "Address", "Phone", "Email", "Start Date",
            "Warehouse Distance" };
            
     /**
     * This function simply prints out the options for the customer available to
     * the user.
     */
    public static void printOptionsCustomer() {
        System.out.println("1. Add new customer");
        System.out.println("2. Edit Customer");
        System.out.println("3. Search Customer");
        System.out.println("4. Delete Customer");
        System.out.println("Type anything else to terminate program");
        System.out
                .print("Please enter your selection as the number next to the option: ");
    }

    /**
     * This Method processes the users request for the customer category.
     *
     * @param option
     *            This is the option the user selected
     * @param readIn
     *            This is an input stream
     * @param map
     *            This is the map with all the user data for customer
     */
    public static void processCustomerRequest(int option, Scanner readIn,
            Map<String, String[]> map) {
        switch (option) {
            case 1:
                // This is for adding a customer
                // Create a values string for the values to be entered
                String[] values = new String[CUSTOMER_ORDER_KEY.length];
                for (int i = 0; i < values.length; i++) {
                    System.out.print(
                            "Please enter the value for " + CUSTOMER_ORDER_KEY[i] + ": ");
                    values[i] = readIn.nextLine();
                }

                addCustomer(values);
                break;

            case 2:
                // This option is for Editing a customers information;
                System.out.print(
                        "Please enter the customer ID for which you would like to edit: ");
                int editKey = readIn.nextInt();
                readIn.nextLine(); // Consume newline
        
                System.out.println("The fields available to edit are: ");
                for(int i = 1; i < CUSTOMER_ORDER_KEY.length + 1; i++) {
                    System.out.println(i + ". " + CUSTOMER_ORDER_KEY[i - 1]);
                }
                System.out.print(
                        "Please enter the number of the field for which you would like to edit (separated by spaces): ");
                String[] choices = readIn.nextLine().split(" ");

                // // Find what
                // int holder = 0;
                // while (holder < CUSTOMER_ORDER_KEY.length
                //         && !editField.equals(CUSTOMER_ORDER_KEY[holder])) {
                //     holder++;
                // }
                // // If holder == the length of the example array, the field entered
                // // is not one of the option, alert the user and exit
                // if (holder == CUSTOMER_ORDER_KEY.length) {
                //     System.out.println("The field entered does not exist: " + editField);
                //     break;
                // }

                // ADD FUNCTIONALITY TO DEAL WITH INVALID FIELD NUMS
                String [] newValues = new String[choices.length];
                for(int i = 0; i < choices.length; i++) {
                    int index = Integer.parseInt(choices[i]) - 1;
                    if(index > 0 || index <= CUSTOMER_ORDER_KEY.length) {
                        System.out.println("Please enter the new value for " + CUSTOMER_ORDER_KEY[index] + ": ");
                        newValues[i] = readIn.nextLine();
                    }
                }
                editCustomer(editKey, choices, newValues);
                break;

            case 3:
                // This function simply searches the values and returns the tuple related
                System.out.print(
                        "Please enter the customer ID for which you would like to search: ");
                int searchKey = readIn.nextInt();
                searchCustomerByID(searchKey);
                break;

            case 4:
                // This function simply searches removes the tuple being deleted
                System.out.print(
                        "Please enter the customer ID for which you would like to delete: ");
                int removeKey = readIn.nextInt();
                removeCustomerByID(removeKey);
                break;
            default:
                // Do not do anything
        }
    }

    /*
     * Method to search a customer by customer ID
     *
     *** Add functionality to print a messgae when it doesn't exist
     */
    public static void searchCustomerByID(int customerID) {
        if(customerID > 0) {
			String sql = "SELECT * FROM Customers WHERE customer_id = ?;"; 
			SQL.ps_SearchCustomer(sql, customerID);
        }
        
    }

    /*
     * Method to add a new customer to the database
     */
    public static void addCustomer(String [] customerData) {
        for (String data : customerData) {
            if (data == null || data.isEmpty()) {
                System.out.println("All fields must be filled to add a new customer.");
                return;
            }
        }
        String sql = "INSERT INTO Customers(first_name, last_name, address, phone, email, start_date, warehouse_distance, customer_id) VALUES(?,?,?,?,?,?,?,?);";
        SQL.ps_AddCustomer(sql, customerData);
        
    }

    /*
     * Method to remove a customer from the database by customer ID
     * 
    * **ADD FUNCTIONALITY TO PRINT A MESSAGE WHEN CUSTOMER ID DOESN'T EXIST*
     */
    public static void removeCustomerByID(int customerID) {
        if(customerID > 0) {
            String sql = "DELETE FROM Customers WHERE customer_id = ?;";
            SQL.ps_RemoveCustomer(sql, customerID);
        }
    }

     /*
     * Method to edit a customer's data 
     * 
    * **ADD FUNCTIONALITY TO PRINT A MESSAGE WHEN CUSTOMER ID DOESN'T EXIST*
     */
    public static void editCustomer(int customerID, String [] choices, String [] newValues) {
        if(customerID > 0) {
            String sql = "UPDATE Customers SET ";
            for(int i = 0; i < choices.length; i++) {
                int index = Integer.parseInt(choices[i]) - 1;
                sql += CUSTOMER_ORDER_KEY[index].toLowerCase().replace(" ", "_") + " = ?";
                if(i < choices.length - 1) {
                    sql += ", ";
                }
            }
            sql += " WHERE customer_id = ?;";
            SQL.ps_EditCustomer(sql, customerID, newValues);
        }
    }
}
