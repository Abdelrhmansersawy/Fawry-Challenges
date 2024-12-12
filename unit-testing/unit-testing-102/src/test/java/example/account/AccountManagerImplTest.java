package example.account;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountManagerImplTest {
    // Arrange
    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();


    @Test
    void givenAccount_whenDepositPositiveAmount_thenBalanceIsUpdated(){
        // Act
        accountManager.deposit(customer , 20);

        // Assert
        assertThat(customer.getBalance()).isEqualTo(20);
    }

    @Test
    void givenAccount_whenDepositNegativeAmount_thenExceptionIsThrown(){
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> accountManager.deposit(customer, -20) );
    }

    @Test
    void givenAccount_whenValidWithdraw_thenBalancedIsUpdated(){
        // Arrange
        customer.setBalance(50);

        // Act
        String result = accountManager.withdraw(customer, 10);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(40);
    }

    @Test
    void givenAccount_WhenWithDrawNegativeAmount_thenExceptionIsThrown(){
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> accountManager.withdraw(customer, -20) );

    }

    @Test
    void givenUnCreditAllowedAccount_WhenInsufficientBalance_thenAlertIsGiven(){
        // Arrange
        customer.setCreditAllowed(false);
        customer.setBalance(20);

        // Act
        String result = accountManager.withdraw(customer , 21);

        // Assert
        assertThat(result).isEqualTo("insufficient account balance");
        assertThat(customer.getBalance()).isEqualTo(20); // the balance doesn't change
    }

    @Test
    void givenCreditAllowedAccountWhenInsufficientBalance_thenBalanceIsUpdated(){
        // Arrange
        customer.setCreditAllowed(true);
        customer.setBalance(20);

        // Act
        String result = accountManager.withdraw(customer , 40);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-20);
    }

    @Test
    void givenNonVipAccount_WhenWithdrawExceededMaximumCredit_thenAlertIsGiven(){
        // Arrange
        customer.setVip(false);
        customer.setBalance(10);
        customer.setCreditAllowed(true);

        // Act
        String result = accountManager.withdraw(customer, 1200);

        // Assert
        assertThat(result).isEqualTo("maximum credit exceeded");
        assertThat(customer.getBalance()).isEqualTo(10);
    }

    @Test
    void givenVipAccount_WhenWithdrawExceededMaximumCredit_thenAlertIsGiven(){
        // Arrange
        customer.setVip(true);
        customer.setBalance(10);
        customer.setCreditAllowed(true);

        // Act
        String result = accountManager.withdraw(customer, 1200);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-1190);
    }
}
