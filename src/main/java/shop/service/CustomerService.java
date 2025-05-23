package shop.service;

import shop.domain.Customer;
import shop.domain.Product;
import shop.exceptions.customer.CustomerNotFoundExeption;
import shop.exceptions.customer.CustomerRestoreExeption;
import shop.exceptions.customer.CusttomerSaveExeption;
import shop.repository.CustomerRepository;

import java.util.List;

public class CustomerService {











    private final CustomerRepository repository;
    private final ProductService productService;

    public CustomerService() {
        repository = new CustomerRepository();
        productService =ProductService.getInstance();
    }
 //Сохранить покупателя в базе данных (при сохранении покупатель автоматически считаетсяактивным).
    public Customer save(Customer customer) {
        if(customer == null) {
            throw new CusttomerSaveExeption("Sohranyaemii pokupatel ne moget bit null");

        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CusttomerSaveExeption("Imya sohranyaemogo pokupatelya ne moget bit pustim");
        }
        customer.setActive(true);
        return repository.save(customer);
    }
//    Вернуть всех покупателей из базы данных (активных).
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(Customer::isActive)
                .toList();
    }
//    Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
    public Customer getActiveCustomerById(Long id) {
        Customer customer = repository.findById(id);

        if (customer == null || !customer.isActive()){
            throw new CustomerNotFoundExeption(id);

        }
        return customer;
    }
//    Изменить одного покупателя в базе данных по его идентификатору.
    public void update(Long id,String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new CusttomerSaveExeption("Novoe Imya  pokupatelya ne moget bit pustim");
    }
        getActiveCustomerById(id);
        repository.update(id, newName);

    }    //Удалить покупателя из базы данных по его идентификатору.
    public  void deleteById(Long id) {
        getActiveCustomerById(id).setActive(false);
    }
//Удалить покупателя из базы данных по его имени.
    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }
    // Восстановить удалённого покупателя в базе данных по его идентификатору.
    // Customer customer = repository.findById();
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);

        if (customer == null) {
            throw new CustomerRestoreExeption(
                    String.format("Pokupatel s id %d ne sushestvuet", id)
            );
        }
        if (customer.isActive()){
            throw new CustomerRestoreExeption(
                    String.format("Pokupatel s id %d uge aktiven", id)
            );
        }
        customer.setActive(true);
    }//Вернуть общее количество покупателей в базе данных (активных).
    public int getAllActiveCustomersNumber() {
        return getAllActiveCustomers().size();

    }//Вернуть стоимость корзины покупателя по его идентификатору (если он активен).
    public double getCustomersCartTotalCost(Long customerId) {
        return getActiveCustomerById(customerId)
                .getCart()
                .stream()
                .filter(Product::isActive)
                .mapToDouble(Product::getPrice)
                .sum();
    }
    //Vernut sredniuu stoimost produkta v korzine po id
    public double getCustomersCartAveragePrice(Long customerId) {
        double cartTotalCost = getCustomersCartTotalCost(customerId);
        return getActiveCustomerById(customerId)
                .getCart()
                .stream()
                .filter(Product::isActive)
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    //Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
    public void addProductToCustomersCart(Long customerId,Long productId) {
        Customer customer = getActiveCustomerById(customerId);
        Product product = productService.getActiveProductById(productId);
        customer.getCart().add(product);
    }
    //Удалить товар из корзины покупателя по их идентификаторам
    public void removeProductFromCustomerCart(Long customerId, Long productId) {
        Customer customer = getActiveCustomerById(customerId);
        Product product = productService.getActiveProductById(productId);
        customer.getCart().remove(product);
    }
    //Полностью очистить корзину покупателя по его идентификатору (если он активен)
    public void clearCustomersCart(Long customerId) {
        getActiveCustomerById(customerId).getCart().clear();
    }
}

