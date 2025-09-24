package ca.bcit.comp2522.bank;

/**
 * Represents a bank account belonging to a {@link BankClient}. A bank account
 * tracks its unique account number, the client who owns it, the dates when the
 * account was opened and possibly closed, the current balance, and the account PIN.
 *
 * <p>Key features include:</p>
 * <ul>
 *     <li>Validation of account details on creation.</li>
 *     <li>Support for deposits and withdrawals (with optional PIN validation).</li>
 *     <li>Formatted detail reporting of account state.</li>
 * </ul>
 *
 * <p>Accounts may be open-ended (no closing date) or closed. Deposits and withdrawals
 * cannot be performed on closed accounts.</p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
class BankAccount
{
    private static final int MIN_ACCOUNT_NUMBER_LENGTH = 6;
    private static final int MAX_ACCOUNT_NUMBER_LENGTH = 7;

    private final BankClient client;
    private final String     accountNumber;
    private final Date       accountOpened;
    private final Date       accountClosed;
    private final int        pin;
    private       double     balance;

    /**
     * Constructs a {@code BankAccount} with the specified details.
     *
     * @param client         the client who owns this account; must not be {@code null}
     * @param accountNumber  the unique account number
     * @param accountOpened  the date the account was opened; must not be {@code null}
     * @param accountClosed  the date the account was closed, or {@code null} if still open
     * @param initialBalance the starting balance in USD; must not be negative
     * @param pin            the personal identification number for this account
     * @throws IllegalArgumentException if validation fails for any parameter
     */
    public BankAccount(final BankClient client,
                       final String accountNumber,
                       final Date accountOpened,
                       final Date accountClosed,
                       final double initialBalance,
                       final int pin)
    {
        validateAccount(client, accountNumber, accountOpened, initialBalance);

        this.client        = client;
        this.accountNumber = accountNumber;
        this.accountOpened = accountOpened;
        this.accountClosed = accountClosed;
        this.balance       = initialBalance;
        this.pin           = pin;
    }

    /**
     * Validates account details for construction.
     *
     * @param client         the client who owns this account
     * @param accountNumber  the proposed account number
     * @param accountOpened  the date the account was opened
     * @param initialBalance the proposed initial balance
     * @throws IllegalArgumentException if any validation rule is violated
     */
    private void validateAccount(final BankClient client,
                                 final String accountNumber,
                                 final Date accountOpened,
                                 final double initialBalance)
    {
        if (client == null)
        {
            throw new IllegalArgumentException("Client cannot be null");
        }

        if (accountNumber == null || accountNumber.trim().isEmpty())
        {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        else if (accountNumber.length() < MIN_ACCOUNT_NUMBER_LENGTH || accountNumber.length() > MAX_ACCOUNT_NUMBER_LENGTH)
        {
            throw new IllegalArgumentException("Account number must be " + MIN_ACCOUNT_NUMBER_LENGTH + " or " +
                                               MAX_ACCOUNT_NUMBER_LENGTH + " characters");
        }

        if (accountOpened == null)
        {
            throw new IllegalArgumentException("Account opened date cannot be null");
        }
        if (initialBalance < 0)
        {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }


    /**
     * Retrieves the client who owns this account.
     *
     * @return the owning client
     */
    public BankClient getClient()
    {
        return client;
    }

    /**
     * Retrieves the account number.
     *
     * @return the unique account number
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Retrieves the date when this account was opened.
     *
     * @return the opening date
     */
    public Date getAccountOpened()
    {
        return accountOpened;
    }

    /**
     * Retrieves the closing date of this account, if applicable.
     *
     * @return the closing date, or {@code null} if the account is still open
     */
    public Date getAccountClosed()
    {
        return accountClosed;
    }

    /**
     * Retrieves the current account balance.
     *
     * @return the balance in USD
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * Withdraws funds from this account if sufficient balance is available and the account is open.
     *
     * @param amountUsd the amount to withdraw in USD; must be positive
     * @throws IllegalArgumentException if amount is invalid, balance is insufficient,
     *                                  or the account is closed
     */
    public void withdraw(final double amountUsd)
    {
        if (amountUsd <= 0)
        {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (amountUsd > balance)
        {
            throw new IllegalArgumentException("Insufficient funds");
        }

        if (accountClosed != null)
        {
            throw new IllegalArgumentException("Cannot withdraw from a closed account");
        }

        balance -= amountUsd;
    }

    /**
     * Withdraws funds from this account if the correct PIN is provided.
     *
     * @param amountUsd  the amount to withdraw in USD; must be positive
     * @param pinToMatch the PIN entered for verification
     * @throws IllegalArgumentException if the PIN is invalid, the amount is invalid,
     *                                  the balance is insufficient, or the account is closed
     */
    public void withdraw(final double amountUsd,
                         final int pinToMatch)
    {
        if (pinToMatch != this.pin)
        {
            throw new IllegalArgumentException("Invalid PIN");
        }
        withdraw(amountUsd);
    }

    /**
     * Deposits funds into this account if the account is open.
     *
     * @param amountUsd the amount to deposit in USD; must be positive
     * @throws IllegalArgumentException if the amount is invalid or the account is closed
     */
    public void deposit(final double amountUsd)
    {
        if (amountUsd <= 0)
        {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (accountClosed != null)
        {
            throw new IllegalArgumentException("Cannot deposit to a closed account");
        }
        balance += amountUsd;
    }

    /**
     * Returns a formatted string describing this account's details, including:
     * <ul>
     *     <li>The client's full name</li>
     *     <li>The current balance</li>
     *     <li>The account number</li>
     *     <li>The account opening date</li>
     *     <li>The account closing date, if applicable</li>
     * </ul>
     *
     * @return a human-readable string representation of account details
     */
    public String getDetails()
    {
        final String openedDateFormatted;
        final String closedDateFormatted;
        final String detailsFormatted;

        openedDateFormatted = String.format("%s %s %d, %d",
                                            accountOpened.getDayOfTheWeek(),
                                            accountOpened.getMonthName(),
                                            accountOpened.getDay(),
                                            accountOpened.getYear()
                                           );

        String clientName = client.getName().getFullName();

        if (accountClosed != null)
        {
            closedDateFormatted = String.format("closed %s %s %d, %d",
                                                accountClosed.getDayOfTheWeek(),
                                                accountClosed.getMonthName(),
                                                accountClosed.getDay(),
                                                accountClosed.getYear()
                                               );
        }
        else
        {
            closedDateFormatted = "is still open.";
        }

        detailsFormatted = String.format("%s had $%.0f USD in account #%s which they opened on %s and %s.",
                                         clientName,
                                         balance,
                                         accountNumber,
                                         openedDateFormatted,
                                         closedDateFormatted
                                        );

        return detailsFormatted;
    }
}