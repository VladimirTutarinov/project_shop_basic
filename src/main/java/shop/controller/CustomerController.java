package shop.controller;

import shop.domain.Customer;
import shop.domain.Product;
import shop.exceptions.customer.CustomerNotFoundExeption;
import shop.exceptions.customer.CustomerRestoreExeption;
import shop.exceptions.customer.CusttomerSaveExeption;
import shop.service.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService service;
    public CustomerController(){
        service = new CustomerService();
    }

    public Customer save(String name) {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

    //    Вернуть всех покупателей из базы данных (активных).
    public List<Customer> getAllActiveCustomers() {
        return service.getAllActiveCustomers();

    }
    //    Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
    public Customer getActiveCustomerById(Long id) {
        return service.getActiveCustomerById(id);

    }
    //    Изменить одного покупателя в базе данных по его идентификатору.
    public void update(Long id,String newName) {
        service.update(id, newName);


    }    //Удалить покупателя из базы данных по его идентификатору.
    public  void deleteById(Long id) {
        service.deleteById(id);

    }
    //Удалить покупателя из базы данных по его имени.
    public void deleteByName(String name) {
        service.deleteByName(name);
    }
    // Восстановить удалённого покупателя в базе данных по его идентификатору.
    // Customer customer = repository.findById();
    public void restoreById(Long id) {
        service.restoreById(id);

    }//Вернуть общее количество покупателей в базе данных (активных).
    public int getAllActiveCustomersNumber() {
        return service.getAllActiveCustomersNumber();


    }//Вернуть стоимость корзины покупателя по его идентификатору (если он активен).
    public double getCustomersCartTotalCost(Long customerId) {
        return service.getCustomersCartTotalCost(customerId);

    }
    //Vernut sredniuu stoimost produkta v korzine po id
    public double getCustomersCartAveragePrice(Long customerId) {
        return service.getCustomersCartAveragePrice(customerId);

    }

    //Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
    public void addProductToCustomersCart(Long customerId,Long productId) {
        service.addProductToCustomersCart(customerId, productId);

    }
    //Удалить товар из корзины покупателя по их идентификаторам
    public void removeProductFromCustomerCart(Long customerId, Long productId) {
        service.removeProductFromCustomerCart(customerId, productId);

    }
    //Полностью очистить корзину покупателя по его идентификатору (если он активен)
    public void clearCustomersCart(Long customerId) {
        service.clearCustomersCart(customerId);

    }
}

