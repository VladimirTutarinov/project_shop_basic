package shop.repository;

import shop.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Это Репозиторий Продуктов.
Репозитории - это второй слой приложения.
Репозитории нужны для того, чтобы обеспечивать доступ к данным.
Настоящее приложение хранит данные в базе данных.
Настоящий репозиторий содержит методы, которые работают с этой базой данных нашем случае у нас
будет имитация базы данных в виде Мар.
I методы будут осуществлять доступ к данным, хранящимся в Мар.
Репозитории обычно поддерживают стандартные CRUD-операции.
CRUD - Create, Read, Update, Delete (Создать, Прочитать, Обновить,Удалить)
 */
public class ProductRepository {
    //imitaciya bazi dannih
    //Kliuch eto indefikator produkta.
    //Znachenie - eto sam obiekt produkta po poryadku-1,2,3,4,5...
    private Map<Long, Product> datebase;

    private long currentId;
// В конструкторе заполняем базу продуктами и выставляем
    // значение переменной currentId в значение 5, чтобы следующему
// продукту был присвоен следующий идентификатор - 6.
    public ProductRepository(){
        datebase = new HashMap<>();
        datebase.put(1L,new Product(1L,"Banan",120,true));
        datebase.put(2L,new Product(2L,"Jabloko",80,true));
        datebase.put(3L,new Product(3L,"Apelsin",230,true));
        datebase.put(4L,new Product(4L,"Persik",190,true));
        datebase.put(5L,new Product(5L,"Limon",140,true));

        currentId = 5;

    }
    //Poluchenie iz bazi dannih odnogo produkta
public Product save(Product product){
        product.setId(++currentId);
        datebase.put(currentId, product);
        return product;

    }
       public Product findById(Long id){
        return datebase.get(id);
       }
//    Poluchenie iz bazi dannih vseh produktov
    public List<Product> findAll(){
        return new ArrayList<>(datebase.values());

    }
//   Izmineniya ceni sushestvuiushego produkta v baze dannih
    public void update(Long id, double newPrice){
        datebase.get(id).setPrice(newPrice);
    }
//  Udalenie produkta iz bazi dannih
    public void deleteById(Long id){
        datebase.remove(id);
    }
}
