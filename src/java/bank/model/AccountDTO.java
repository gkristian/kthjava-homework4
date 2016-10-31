package bank.model;

/**
 * The views read-only view of an Account.
 */
public interface AccountDTO {
    /**
     * Gets the balance of this Account.
     *
     * @return this accounts balance.
     */
    float getValue();

    /**
     * Gets the first name of the holder of this Account.
     *
     * @return the first name of the holder of this Account.
     */
    String getCurrName();

}
