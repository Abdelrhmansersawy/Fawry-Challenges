package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StoreImplTest {
    AccountManager mockAccountManager = mock(AccountManager.class);
    Product product = new Product();
    StoreImpl store = new StoreImpl(mockAccountManager);
    Customer customer = new Customer();

    @Test
    void givenProductInStock_whenCustomerBuysProduct_thenQuantityDecreasesByOne(){
        // Arrange
        product.setQuantity(10);
        when(mockAccountManager.withdraw(customer, product.getPrice())).thenAnswer( (Answer) -> "success");

        // Act
        store.buy(product,customer);

        // Assert
        assertThat(product.getQuantity()).isEqualTo(9);
    }

    @Test
    void givenProductOutOfStock_whenCustomerBuysProduct_thenRuntimeExceptionIsThrown(){
        // Arrange
        product.setQuantity(0);

        // Act & Assert
        assertThrows(RuntimeException.class , () -> store.buy(product,customer));
    }

    @Test
    void givenFailurePayment_whenCustomerBuysProduct_thenRuntimeExceptionIsThrown(){
        // Arrange
        product.setQuantity(10);
        when(mockAccountManager.withdraw(customer, product.getPrice())).thenAnswer( (Answer) -> "maximum credit exceeded");

        // Act & Assert
        assertThrows(RuntimeException.class , () -> store.buy(product,customer));
    }
}
