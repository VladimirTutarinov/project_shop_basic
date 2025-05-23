package shop.exceptions.customer;

public class CustomerUpdateExeption extends RuntimeException{
    public CustomerUpdateExeption(String message) {
        super(message);
    }
}
