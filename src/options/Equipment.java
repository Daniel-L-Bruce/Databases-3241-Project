package options;

import java.util.Scanner;

import app.MainProgram;

public class Equipment {
    
    /**
     * This function simply prints out the options for the equipment available
     * to the user.
     */
    public static void printOptionsEquipment() {
        System.out.println("1. Rent Equipment");
        System.out.println("2. Return Equipment");
        System.out.println("3. Delivery of Equipment");
        System.out.println("4. Pickup of Equipment");
        System.out.println("Type anything else to terminate program");
        System.out
                .print("Please enter your selection as the number next to the option: ");
    }

     /**
     * This function processes the option chosen by user for equipment.
     *
     * @param option
     *            The option chosen by the user
     * @param readIn
     *            Scanner object where to read in the value
     */
    public static void processEquipmentRequest(int option, Scanner readIn) {
        int equipmentSerialNumber = 0;
        int droneSerialNumber = 0;
        switch (option) {
            case 1:
                // This option is for renting equipment;
                System.out.print(
                        "Please enter the serial number of the peice of equipment you wish to rent: ");
                // Grab the number entered as the serial number
                equipmentSerialNumber = MainProgram.parseNumber(readIn);
                System.out.println("You have selected to rent: " + equipmentSerialNumber);
                // More to come in future.
                break;
            case 2:
                // This option is for return equipment;
                System.out.print(
                        "Please enter the serial number of the peice of equipment you wish to return: ");
                // Grab the number entered as the serial number
                equipmentSerialNumber = MainProgram.parseNumber(readIn);
                System.out
                        .println("You have selected to return: " + equipmentSerialNumber);
                // More to come in future.
                break;
            case 3:
                // This option is for delivery of equipment;
                System.out.print(
                        "Please enter the serial number of the peice of equipment you wish to get delivered: ");
                // Grab the number entered as the serial number
                equipmentSerialNumber = MainProgram.parseNumber(readIn);
                System.out.print(
                        "Please enter the serial number of the drone you wish to do the delivery: ");
                // Grab the number entered as the serial number
                droneSerialNumber = MainProgram.parseNumber(readIn);
                System.out.println("You have selected to deliver the equipment: "
                        + equipmentSerialNumber);
                System.out.println("You have selected to have this drone deliver: "
                        + droneSerialNumber);
                // More to come in future.
                break;
            case 4:
                // This option is for pickup of equipment;
                System.out.print(
                        "Please enter the serial number of the peice of equipment you wish to get picked up: ");
                // Grab the number entered as the serial number
                equipmentSerialNumber = MainProgram.parseNumber(readIn);
                System.out.print(
                        "Please enter the serial number of the drone you wish to do the pickup the equipment: ");
                // Grab the number entered as the serial number
                droneSerialNumber = MainProgram.parseNumber(readIn);
                System.out.println("You have selected to pickup the equipment: "
                        + equipmentSerialNumber);
                System.out.println("You have selected to have this drone pickup: "
                        + droneSerialNumber);
                break;
            default:
                // Do not do anything
        }
    }
}
