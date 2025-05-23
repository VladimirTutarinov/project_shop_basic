package shop.exceptions.customer;

public class CustomerNotFoundExeption extends RuntimeException{
    public CustomerNotFoundExeption(Long id) {
        super(String.format("Pokupatel s id %d ne naiden", id));
    }
}
