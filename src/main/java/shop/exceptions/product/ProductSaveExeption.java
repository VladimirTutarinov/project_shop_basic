package shop.exceptions.product;

public class ProductSaveExeption extends RuntimeException{
    public ProductSaveExeption(String message) {
        super(message);
    }
}
