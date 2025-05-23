package shop.exceptions.product;

public class ProductUpdateExeption extends RuntimeException{
    public ProductUpdateExeption(String message) {
        super(message);
    }
}
