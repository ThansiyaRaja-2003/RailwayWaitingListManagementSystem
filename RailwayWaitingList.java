
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

// Stores passenger details
class Passenger {
    int passengerId;
    String name;

    Passenger(int passengerId, String name) {
        this.passengerId = passengerId;
        this.name = name;
    }
}

public class RailwayWaitingList {

    static LinkedList<Passenger> waitingList =
            new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    // Read an integer safely
    static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Add passenger at the rear of the queue
    static void addPassenger() {
        int id = readInt("Enter Passenger ID: ");

        if (id <= 0) {
            System.out.println("ID must be positive.");
            return;
        }

        for (Passenger p : waitingList) {
            if (p.passengerId == id) {
                System.out.println("Passenger ID already exists.");
                return;
            }
        }

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        waitingList.addLast(new Passenger(id, name));
        System.out.println("Passenger added successfully!");
    }

    // Allocate seat to the first waiting passenger
    static void allocateSeat() {
        if (waitingList.isEmpty()) {
            System.out.println("No passengers are waiting.");
            return;
        }

        Passenger p = waitingList.removeFirst();

        System.out.println("Seat allocated successfully!");
        System.out.println("Passenger ID: " + p.passengerId);
        System.out.println("Passenger Name: " + p.name);
    }

    // Cancel a passenger's waiting-list request
    static void cancelRequest() {
        int id = readInt("Enter Passenger ID to cancel: ");

        Iterator<Passenger> iterator =
                waitingList.iterator();

        while (iterator.hasNext()) {
            Passenger p = iterator.next();

            if (p.passengerId == id) {
                iterator.remove();
                System.out.println("Request cancelled successfully.");
                return;
            }
        }

        System.out.println("Passenger not found in waiting list.");
    }

    // Display all waiting passengers
    static void displayWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }

        System.out.println("\n--- Railway Waiting List ---");

        for (Passenger p : waitingList) {
            System.out.println("ID: " + p.passengerId
                    + " | Name: " + p.name);
        }
    }

    // Display the front and rear passengers
    static void displayFrontAndRear() {
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }

        Passenger front = waitingList.getFirst();
        Passenger rear = waitingList.getLast();

        System.out.println("Front Passenger: "
                + front.name + " (ID: "
                + front.passengerId + ")");

        System.out.println("Rear Passenger: "
                + rear.name + " (ID: "
                + rear.passengerId + ")");
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" RAILWAY WAITING LIST MANAGEMENT");
            System.out.println("================================");
            System.out.println("1. Add Passenger");
            System.out.println("2. Allocate Available Seat");
            System.out.println("3. Cancel Waiting List Request");
            System.out.println("4. Display Waiting List");
            System.out.println("5. View Front and Rear Passengers");
            System.out.println("6. Exit");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addPassenger();
                    break;

                case 2:
                    allocateSeat();
                    break;

                case 3:
                    cancelRequest();
                    break;

                case 4:
                    displayWaitingList();
                    break;

                case 5:
                    displayFrontAndRear();
                    break;

                case 6:
                    System.out.println(
                        "Thank you for using Railway Waiting List Management!");
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Select 1 to 6.");
            }

        } while (choice != 6);

        sc.close();
    }
}

