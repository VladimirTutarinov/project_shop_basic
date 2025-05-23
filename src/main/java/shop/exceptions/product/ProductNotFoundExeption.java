package shop.exceptions.product;

public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption(Long id) {
        super(String.format("Produkt s id %d ne naiden", id));
    }
}
