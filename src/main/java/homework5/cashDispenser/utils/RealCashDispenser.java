package homework5.cashDispenser.utils;

import homework5.addMoney.utils.PossibleAddMoney;
import homework5.wasteMoney.utils.PossibleWasteMoney;

/**
 * Atm implementation of the <tt>CashDispenser</tt> abstract class.  Implements
 * all optional  operations.
 */

public class RealCashDispenser extends CashDispenser {

    public RealCashDispenser() {
        addMoneyBehavior = new PossibleAddMoney();
        wasteMoneyBehavior = new PossibleWasteMoney();
    }

    /** Returns the cash account after replenishment.
     * @param money - the value that should not be null and should be more or equals to 0
     */
    @Override
    public synchronized void performAddMoney(double money) {
        double result = customerAccount.getMoney() + addMoneyBehavior.addMoney(money);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerAccount.setMoney(result);
        System.out.println("You added: " + money);
        showBalance();
    }

    /** Returns the cash account after cash withdrawal.
     * @param money - the value that should not be null and should be more or equals to 0
     */
    @Override
    public synchronized void performWasteMoney(double money) {
        if (customerAccount.getMoney() >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double result = customerAccount.getMoney() - wasteMoneyBehavior.removeMoney(money);
            customerAccount.setMoney(result);
            System.out.println("You wasted: " + money);
            showBalance();
        } else {
            System.out.println("Not enough money :(");
        }
    }

    /**
     * Display information about ATM
     */
    @Override
    public void display() {
        System.out.println("Iam real ATM");
    }
}
