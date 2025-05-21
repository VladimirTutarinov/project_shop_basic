package shop.domain;


//Klass Opisuet produkt,
//Etot Klass Yavlyaetsya chastiu domena - predmetnoi oblastiu nashego prilogeniya.
// Poetomu on nahoditsya v pakete domain.

import java.util.Objects;

public class Product {
    private Long id;
    private String title;
    private double price;
    private boolean active;

    public Product(Long id, String title, double price, boolean active) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("%nProdukt: id - %d, nazvanie - %s, cena - %2f, aktiven - %s%n",
                id, title, price, active ? "da"  :  "net");

    }
}