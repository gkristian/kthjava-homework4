package bank.view;

import bank.controller.CashierFacade;
import bank.model.AccountDTO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("acctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CashierFacade cashierFacade;
    private AccountDTO currentAcct;
    private String newConversionName;

    private float newConversionValue;

    private Exception transactionFailure;
    private String option = "NOCURR";
    private String inputCurr1 = "Currency 1";
    private String inputCurr2 = "Currency 2";
    private float amount = 0;
    private float newAmount = 0;
    private float multiplier = 1;

    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.
     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }

    
    public String createAccount() {
        try {
            startConversation();
            transactionFailure = null;
            currentAcct = cashierFacade.createAccount(newConversionName, newConversionValue);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    public String initialize() {
        try {
            startConversation();
            transactionFailure = null;
            cashierFacade.initialize();
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    //==========================================================================
    public String getCurrency() {
        try {
            startConversation();
            transactionFailure = null;
            option = inputCurr1 + inputCurr2;
            currentAcct = cashierFacade.findAccount(option); //Take and save to currentAcct
            option = currentAcct.getCurrName();
            multiplier = currentAcct.getValue();
            newAmount = amount*multiplier;
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }      
    
    //==========================================================================
    public void setOption(String option) {       
        this.option = option;
    }
    public String getOption() {        
        return option;
    }
    public void setAmount(float amount) {        
        this.amount = amount;        
    }
    public float getAmount() {       
        return amount;
    }
    public void setNewAmount(float newAmount) {        
        this.newAmount = newAmount;        
    }
    public float getNewAmount() {       
        return newAmount;
    }    
    public void setInputCurr1(String inputCurr1) {       
        this.inputCurr1 = inputCurr1;
    }
    public String getInputCurr1() {        
        return inputCurr1;
    } 
    public void setInputCurr2(String inputCurr2) {       
        this.inputCurr2 = inputCurr2;
    }
    public String getInputCurr2() {        
        return inputCurr2;
    }        
    //==========================================================================
    /**
     * Set the value of newConversionValue
     *
     * @param newConversionValue new value of newConversionValue
     */
    public void setNewConversionValue(float newConversionValue) {
        this.newConversionValue = newConversionValue;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public float getNewConversionValue() {
        return newConversionValue;
    }

    //------------------------------------------------
    /**
     * Set the value of newConversionName
     *
     * @param newConversionName new value of newConversionName
     */
    public void setNewConversionName(String newConversionName) {
        this.newConversionName = newConversionName;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getNewConversionName() {
        return null;
    }
    //------------------------------------------------
    /**
     * Get the value of currentAcct
     *
     * @return the value of currentAcct
     */
    public AccountDTO getCurrentAcct() {
        return currentAcct;
    }
}
