package bank.controller;

import bank.model.Account;
import bank.model.AccountDTO;
import bank.model.OverdraftException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 * A controller. All calls to the model that are executed because of an action taken by
 * the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CashierFacade {
    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    //INITIALIZE 4 CURRENCIES
    public void initialize() {
        
        Account newAcct;
        String name;
        float rate;
        name = "SEKEUR";rate = (float) 0.1073;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "SEKGBP";rate = (float) 0.0774;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "SEKUSD";rate = (float) 0.1174;
        newAcct = new Account(rate, name);em.persist(newAcct);
        
        name = "EURSEK";rate = (float) 9.3179;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "EURUSD";rate = (float) 1.0937;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "EURGBP";rate = (float) 0.7215;
        newAcct = new Account(rate, name);em.persist(newAcct);
        
        name = "USDSEK";rate = (float) 8.5197;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "USDEUR";rate = (float) 0.9143;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "USDGBP";rate = (float) 0.6596;
        newAcct = new Account(rate, name);em.persist(newAcct);
        
        name = "GBPSEK";rate = (float) 12.9147;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "GBPEUR";rate = (float) 1.3860;
        newAcct = new Account(rate, name);em.persist(newAcct);
        name = "GBPUSD";rate = (float) 1.5158;
        newAcct = new Account(rate, name);em.persist(newAcct);        
                        
    }    

    //Create new conversion rate
    public AccountDTO createAccount(String currName, float value) {
        Account newAcct = new Account(value, currName);
        em.persist(newAcct);
        return newAcct;
    }

    //Find conversion rate
    public AccountDTO findAccount(String currName) {
        AccountDTO found =  em.find(Account.class, currName);
        if (found == null) {
            throw new EntityNotFoundException("No conversion info for " + currName);
        }
        return found;
    }


}
