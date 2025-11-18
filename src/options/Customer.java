package options;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    
    /**
     * This variable is used as a key for which string means what in the
     * customer array value of the map.
     */
    public static final String[] CUSTOMER_ORDER_KEY = { "Customer ID", "First Name",
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
                            "Please enter the value for " + CUSTOMER_ORDER_KEY[i] + ":");
                    values[i] = readIn.nextLine();
                }
                String key = values[0];
                if (!map.containsKey(key)) {
                    map.put(key, values);
                }
                break;
            case 2:
                // This option is for Editing a customers information;
                System.out.print(
                        "Please enter the customer ID for which you would like to edit: ");
                String editKey = readIn.nextLine();
                // If the key is not contained, alert user, and break
                if (!map.containsKey(editKey)) {
                    System.out.println("No customer has the id: " + editKey);
                    break;
                }
                System.out.println(
                        "Avaiable fields: " + Arrays.toString(CUSTOMER_ORDER_KEY));
                System.out.print(
                        "Please enter the field for which you would like to edit: ");
                String editField = readIn.nextLine();
                // Find what
                int holder = 0;
                while (holder < CUSTOMER_ORDER_KEY.length
                        && !editField.equals(CUSTOMER_ORDER_KEY[holder])) {
                    holder++;
                }
                // If holder == the length of the example array, the field entered
                // is not one of the option, alert the user and exit
                if (holder == CUSTOMER_ORDER_KEY.length) {
                    System.out.println("The field entered does not exist: " + editField);
                    break;
                }
                System.out.print("Please enter the new value: ");
                String newValue = readIn.nextLine();
                String[] editValues = map.get(editKey);
                editValues[holder] = newValue;
                System.out.println("Value updated");
                break;
            case 3:
                // This function simply searches the values and returns the tuple related
                System.out.print(
                        "Please enter the customer ID for which you would like to search: ");
                String searchKey = readIn.nextLine();
                // If the key is not contained, alert user, and break
                if (!map.containsKey(searchKey)) {
                    System.out.println("No customer has the id: " + searchKey);
                    break;
                } else {
                    System.out.println("The values associated are: "
                            + Arrays.toString(map.get(searchKey)));
                }

                break;
            case 4:
                // This function simply searches removes the tuple being deleted
                System.out.print(
                        "Please enter the customer ID for which you would like to delete: ");
                String removeKey = readIn.nextLine();
                // If the key is not contained, alert user, and break
                if (!map.containsKey(removeKey)) {
                    System.out.println("No customer has the id: " + removeKey);
                    break;
                } else {
                    map.remove(removeKey);
                }

                break;
            default:
                // Do not do anything
        }
    }
}
