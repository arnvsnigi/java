import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ContactManager {
    private Map<String, String> contactList;
    private List<String> missedCalls;

    public ContactManager() {
        contactList = new HashMap<>();
        missedCalls = new ArrayList<>();
    }

    public void addContact(String number, String name) {
        contactList.put(number, name);
    }

    public void addMissedCall(String number) {
        if (contactList.containsKey(number)) {
            missedCalls.add(contactList.get(number));
        } else {
            missedCalls.add("Private Caller (" + number + ")");
        }
    }

    public void displayMissedCalls() {
        if (missedCalls.isEmpty()) {
            System.out.println("No missed calls.");
        } else {
            System.out.println("Missed Calls:");
            for (String caller : missedCalls) {
                System.out.println(caller);
            }
        }
    }

    public void deleteNumber(String number) {
        missedCalls.removeIf(caller -> caller.contains(number));
    }
}

public class pgm1 {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();

        // Add some contacts
        contactManager.addContact("1234567890", "John Doe");
        contactManager.addContact("9876543210", "Jane Smith");

        // Add missed calls
        contactManager.addMissedCall("1234567890");
        contactManager.addMissedCall("9876543210");
        contactManager.addMissedCall("5555555555"); // Unknown number

        // Display missed calls
        contactManager.displayMissedCalls();

        // Delete a number
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number to delete: ");
        String numberToDelete = scanner.nextLine();
        contactManager.deleteNumber(numberToDelete);

        // Display missed calls after deletion
        System.out.println("\nMissed Calls after deletion:");
        contactManager.displayMissedCalls();
    }
}
