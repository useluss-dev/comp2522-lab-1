package ca.bcit.comp2522.bank;


public class Main
{
    public static void main(final String[] args)
    {
        Date a = new Date(1977, 10, 31);
        Date b = new Date(2021, 3, 15);
        System.out.println(a.getDayOfTheWeek());
        System.out.println(b.getDayOfTheWeek());

        Name einsteinName = new Name("Albert", "Einstein");
        System.out.println("Initials: " + einsteinName.getInitials());
        System.out.println("Full Name: " + einsteinName.getFullName());
        System.out.println("Reversed Name: " + einsteinName.getReverseName());

        Date einsteinBirth = new Date(1879, 3, 14);
        Date einsteinDeath = new Date(1955, 4, 18);
        Date einsteinSignup = new Date(1900, 1, 1);
        Date einsteinAccountClosed = new Date(1950, 10, 14);

        BankClient einsteinClient = new BankClient(einsteinName, einsteinBirth, einsteinDeath, einsteinSignup, "abc123");
        System.out.println(einsteinClient.getDetails());

        BankAccount einsteinAccount = new BankAccount(einsteinClient, "abc123", einsteinSignup, einsteinAccountClosed, 1000, 3141);

//        try {
//            einsteinAccount.withdraw(100);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }

        System.out.println(einsteinAccount.getDetails());
        System.out.println();

        System.out.println("=== Nelson Mandela ===");
        Name mandelaName = new Name("Nelson", "Mandela");
        System.out.println("Initials: " + mandelaName.getInitials());
        System.out.println("Full Name: " + mandelaName.getFullName());
        System.out.println("Reversed Name: " + mandelaName.getReverseName());

        Date mandelaBirth = new Date(1918, 7, 18);
        Date mandelaDeath = new Date(2013, 12, 5);
        Date mandelaSignup = new Date(1994, 5, 10);

        BankClient mandelaClient = new BankClient(mandelaName, mandelaBirth, mandelaDeath, mandelaSignup, "654321");
        System.out.println(mandelaClient.getDetails());

        BankAccount mandelaAccount = new BankAccount(mandelaClient, "654321", mandelaSignup, null, 2000, 4664);
        mandelaAccount.withdraw(200);
        System.out.println(mandelaAccount.getDetails());
        System.out.println();

        // Frida Kahlo
        System.out.println("=== Frida Kahlo ===");
        Name kahloName = new Name("Frida", "Kahlo");
        System.out.println("Initials: " + kahloName.getInitials());
        System.out.println("Full Name: " + kahloName.getFullName());
        System.out.println("Reversed Name: " + kahloName.getReverseName());

        Date kahloBirth = new Date(1907, 7, 6);
        Date kahloDeath = new Date(1954, 7, 13);
        Date kahloSignup = new Date(1940, 1, 1);

        BankClient kahloClient = new BankClient(kahloName, kahloBirth, kahloDeath, kahloSignup, "frd123");
        System.out.println(kahloClient.getDetails());

        BankAccount kahloAccount = new BankAccount(kahloClient, "frd123", kahloSignup, kahloDeath, 500, 1907);
        // kahloAccount.withdraw(50);
        System.out.println(kahloAccount.getDetails());
        System.out.println();

        Name chanName = new Name("Jackie", "Chan");
        System.out.println("Initials: " + chanName.getInitials());
        System.out.println("Full Name: " + chanName.getFullName());
        System.out.println("Reversed Name: " + chanName.getReverseName());

        Date chanBirth = new Date(1954, 4, 7);
        Date chanSignup = new Date(1980, 10, 1);

        BankClient chanClient = new BankClient(chanName, chanBirth, null, chanSignup, "chan789");
        System.out.println(chanClient.getDetails());

        BankAccount chanAccount = new BankAccount(chanClient, "chan789", chanSignup, null, 3000, 1954);
        chanAccount.withdraw(500);
        System.out.println(chanAccount.getDetails());
    }

    /**
     * Validates that a string is neither {@code null} nor blank.
     *
     * <p>A string is considered invalid if it is:</p>
     * <ul>
     *     <li>{@code null}</li>
     *     <li>Empty ({@code ""})</li>
     *     <li>Only whitespace characters</li>
     * </ul>
     *
     * @param string the string to validate
     * @throws IllegalArgumentException if the string is {@code null}, empty, or blank
     */
    public static void validateString(final String string)
    {
        if (string == null || string.isBlank())
        {
            throw new IllegalArgumentException();
        }
    }
}
