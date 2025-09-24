package ca.bcit.comp2522.bank;

/**
 * Represents a client of the bank. A {@code BankClient} stores identifying
 * information such as the client’s name, dates of birth and death, signup date,
 * and a unique client ID.
 *
 * <p>Clients may be living (no death date) or deceased. Each client has a
 * unique identifier that must follow strict validation rules.</p>
 *
 * <p>Key features include:</p>
 * <ul>
 *     <li>Validation of client information during construction.</li>
 *     <li>Support for accessing key client details such as name, birth date,
 *     signup date, and client ID.</li>
 *     <li>Formatted detail reporting for summary output.</li>
 * </ul>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
class BankClient
{
    // --- Constants for client length ---
    private static final int MIN_CLIENT_ID_LENGTH = 6;
    private static final int MAX_CLIENT_ID_LENGTH = 7;

    // --- Constants for client status ---
    private static final String ALIVE_STRING = "alive";
    private static final String DEAD_STRING  = "not alive";

    private final Name   name;
    private final Date   birthDate;
    private final Date   deathDate;
    private final Date   signupDate;
    private final String clientID;

    /**
     * Constructs a {@code BankClient} with the given identifying information.
     *
     * @param name       the client’s full name; must not be {@code null}
     * @param birthDate  the client’s date of birth; must not be {@code null}
     * @param deathDate  the client’s date of death, or {@code null} if alive
     * @param signupDate the date when the client joined the bank; must not be {@code null}
     * @param clientID   the unique client ID; must be 6–7 characters long
     * @throws IllegalArgumentException if validation fails for any parameter
     */
    public BankClient(final Name name,
                      final Date birthDate,
                      final Date deathDate,
                      final Date signupDate,
                      final String clientID)
    {
        validateClientID(name, birthDate, signupDate, clientID);

        this.name       = name;
        this.birthDate  = birthDate;
        this.deathDate  = deathDate;
        this.signupDate = signupDate;
        this.clientID   = clientID;
    }

    /**
     * Validates required fields for a {@code BankClient}.
     *
     * @param name       the client’s name
     * @param birthDate  the client’s date of birth
     * @param signupDate the client’s signup date
     * @param clientID   the client’s identifier
     * @throws IllegalArgumentException if any field is invalid
     */
    private void validateClientID(final Name name,
                                  final Date birthDate,
                                  final Date signupDate,
                                  final String clientID)
    {
        Main.validateString(clientID);

        if (clientID.length() < MIN_CLIENT_ID_LENGTH || clientID.length() > MAX_CLIENT_ID_LENGTH)
        {
            throw new IllegalArgumentException("Client ID must be 6 or 7 characters");
        }

        if (name == null)
        {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (birthDate == null)
        {
            throw new IllegalArgumentException("Birth date cannot be null");
        }

        if (signupDate == null)
        {
            throw new IllegalArgumentException("Signup date cannot be null");
        }
    }

    /**
     * Retrieves the client’s full name.
     *
     * @return the {@link Name} object representing the client’s name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Retrieves the client’s birth date.
     *
     * @return the date of birth
     */
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Retrieves the client’s death date.
     *
     * @return the date of death, or {@code null} if the client is alive
     */
    public Date getDeathDate()
    {
        return deathDate;
    }

    /**
     * Retrieves the client’s signup date.
     *
     * @return the date when the client joined the bank
     */
    public Date getSignupDate()
    {
        return signupDate;
    }

    /**
     * Retrieves the client’s unique ID.
     *
     * @return the client ID
     */
    public String getClientID()
    {
        return clientID;
    }

    /**
     * Produces a formatted string summarizing the client’s details, including:
     * <ul>
     *     <li>Full name</li>
     *     <li>Unique client ID</li>
     *     <li>Alive/deceased status</li>
     *     <li>Signup date (day of week, month, day, and year)</li>
     * </ul>
     *
     * @return a human-readable string describing the client
     */
    public String getDetails()
    {
        final String signupDay;
        final String signupMonth;
        final String signupFormatted;
        final String status;
        final String detailsFormatted;

        signupDay       = signupDate.getDayOfTheWeek().toLowerCase();
        signupMonth     = signupDate.getMonthName().toLowerCase();
        signupFormatted = String.format("%s, %s %d,%d",
                                        signupDay,
                                        signupMonth,
                                        signupDate.getDay(),
                                        signupDate.getYear());

        if (deathDate == null)
        {
            status = ALIVE_STRING;
        }
        else
        {
            status = DEAD_STRING;
        }


        detailsFormatted = String.format("%s client #%s (%s) joined the bank on %s",
                                         name.getFullName(),
                                         clientID,
                                         status,
                                         signupFormatted);

        return detailsFormatted;
    }
}