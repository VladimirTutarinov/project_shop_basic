package shop.repository;

import shop.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private List<Customer> database;
    private long currentId;

/*
/ Заполняем базу данных тестовыми объектами, чтобы нам было с чем работать
// при старте приложения. В реальной боевой базе данных таких тестовых
 // объектов создаваться не будет.
 */
    public CustomerRepository(){
        database = new ArrayList<>();
        database.add(new Customer(1L,"Vasya",true));
        database.add(new Customer(2L,"Petya",true));
        database.add(new Customer(3L,"Vasya",true));
        currentId = 3;

    }
    public Customer save(Customer customer){
        customer.setId(++currentId);
        database.add(customer);
        return customer;
    }
    public Customer findById(Long id){
        // variant 1 ) Cikl
       // for (Customer customer : database){
        //   if (customer.getId().equals(id)){
          //     return customer;
         //  }
       // }
       // return null;
 //       Variant 2) Streem
        return database.stream()
                .filter(x-> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    //poluchenie vseh pokupatilei
    public List<Customer> findAll(){
        return database;
    }
    //    obnovlenie pokupatelya v baze dannih
    public void update(Long id,String newName){
        Customer customer = findById(id);
        if (customer != null){
            customer.setName(newName);
        }
    }
    public void deleteById(Long id){
        Customer customer = findById(id);
        database.removeIf(x -> x.getId().equals(id));
        }
    }

