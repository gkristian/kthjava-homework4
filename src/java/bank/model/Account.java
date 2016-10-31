package bank.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A persistent representation of an account.
 */
@Entity
public class Account implements AccountDTO, Serializable {

    private static final long serialVersionUID = 16247164401L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String currName;
    private float value;

    /**
     * Creates a new instance of Account
     */
    public Account() {
    }
    //=====================================
    /**
     * Creates a new instance of Account
     */
    public Account(float value, String currName) {
        this.value = value;
        this.currName = currName;        
    }

    /**
     * Get the value of lastNAme
     *
     * @return the value of lastNAme
     */
    @Override
    public String getCurrName() {
        return currName;
    }

    /**
     * Get the value of balance
     *
     * @return the value of balance
     */
    @Override
    public float getValue() {
        return value;
    }
    //=====================================
    @Override
    public int hashCode() {
        int hash = 0;
        return new String(currName).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return this.currName == other.currName;
    }

    @Override
    public String toString() {
        return "bank.model.Account[id=" + currName + "]";
    }
}
