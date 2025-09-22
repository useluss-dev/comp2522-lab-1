package ca.bcit.comp2522.bank;

/**
 * Represents a person's name, composed of a first and last name.
 *
 * <p>The {@code Name} class provides functionality for validating,
 * formatting, and representing names in various ways:</p>
 * <ul>
 *     <li>Ensures names meet length and content restrictions.</li>
 *     <li>Supports retrieval of the first name, last name, full name,
 *     initials, and reversed name.</li>
 *     <li>Automatically capitalizes names in full-name output.</li>
 * </ul>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Name
{
    static final int MAX_NAME_LENGTH = 45;
    static final String ILLEGAL_SUBSTRING = "admin";
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a {@code Name} object with the specified first and last names.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @throws IllegalArgumentException if validation fails for either name
     */
    public Name(final String firstName,
                final String lastName)
    {
        validateName(firstName);
        validateName(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Retrieves the first name.
     *
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Retrieves the last name.
     *
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Returns the full name with both first and last names capitalized
     * (first letter uppercase, remaining letters lowercase).
     *
     * @return the formatted full name
     */
    public String getFullName()
    {
        final StringBuilder builder;
        final String fullName;

        builder = new StringBuilder();

        builder.append(capitalize(firstName));
        builder.append(" ");
        builder.append(capitalize(lastName));

        fullName = builder.toString();

        return fullName;
    }

    /**
     * Returns the initials of the name in uppercase, separated by periods.
     * <p>For example: {@code John Smith -> "J.S."}</p>
     *
     * @return the formatted initials
     */
    public String getInitials()
    {
        final StringBuilder builder;
        final String initials;

        builder = new StringBuilder();

        builder.append(firstName.toUpperCase().charAt(0));
        builder.append(".");
        builder.append(lastName.toUpperCase().charAt(0));
        builder.append(".");

        initials = builder.toString();

        return initials;
    }

    /**
     * Returns the name in reversed character order.
     * <p>For example: {@code "John Smith" -> "htimS nhoJ"}.</p>
     *
     * @return the reversed name string
     */
    public String getReverseName()
    {
        final StringBuilder builder;
        final String reverseName;

        builder = new StringBuilder();

        builder.append(firstName);
        builder.append(" ");
        builder.append(lastName);
        builder.reverse();

        reverseName = builder.toString();

        return reverseName;
    }

    /**
     * Validates a single name string.
     *
     * <p>Rules:</p>
     * <ul>
     *     <li>Must not be {@code null} or blank (checked via {@link Main#validateString(String)}).</li>
     *     <li>Must not exceed {@link #MAX_NAME_LENGTH} characters.</li>
     *     <li>Must not contain {@link #ILLEGAL_SUBSTRING}, ignoring case.</li>
     * </ul>
     *
     * @param name the name string to validate
     * @throws IllegalArgumentException if the name is invalid
     */
    private static void validateName(final String name)
    {
        Main.validateString(name);

        if (name.length() > MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Name exceeds maximum length of " + MAX_NAME_LENGTH);
        }

        String lowercaseName;
        lowercaseName = name.toLowerCase();

        if (lowercaseName.contains(ILLEGAL_SUBSTRING.toLowerCase()))
        {
            throw new IllegalArgumentException("Name contains illegal substring " + ILLEGAL_SUBSTRING);
        }
    }

    /**
     * Capitalizes a string so that the first character is uppercase and all
     * remaining characters are lowercase.
     *
     * @param str the input string
     * @return the capitalized string
     */
    private static String capitalize(final String str)
    {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
