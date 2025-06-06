package shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private boolean active;
    private final List<Product> cart =  new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Customer(Long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;

    }

    public Customer(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer costumer = (Customer) o;
        return active == costumer.active && Objects.equals(id, costumer.id) && Objects.equals(name, costumer.name) && Objects.equals(cart, costumer.cart);
    }

    public List<Product> getCart() {
        return cart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, cart);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\n");
        builder.append(String.format("Pokupatel: id - %d, imya - %s, aktiven - %s",
                id, name, active ? "da" : "net"));
        builder.append("Korsina");
        cart.forEach(builder::append);
        return builder.toString();

    }
}
