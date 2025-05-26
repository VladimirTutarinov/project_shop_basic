package shop.controller;

import shop.domain.Product;
import shop.exceptions.product.ProductNotFoundExeption;
import shop.exceptions.product.ProductRestoreExeption;
import shop.exceptions.product.ProductSaveExeption;
import shop.service.ProductService;

import java.util.List;

/*
Контроллер - это класс, задача которого принять запрос от внешнего приложения,
обратиться к сервису, чтобы выполнить ту операцию, которую просит клиент,
и отправить ответ клиенту.
В реальных приложениях контроллеры принимают http-запросы.
Это текстовые запросы, которые могут быть отправлены через Интернет.
Контроллеры преобразуют полученный текст в Джава-объекты,
и приложение может продолжать работать дальше уже с Джава-объектом
*/
public class ProductController {
    private final ProductService service;

    public ProductController() {
        service = ProductService.getInstance();
    }
    public Product save(String title,double price) {
        Product product = new Product(title, price);
        return service.save(product);
    }

    // Vernut vse produkti iz bazi
    public List<Product> getAllActiveProducts() {
        return service.getAllActiveProducts();
    }

    public Product getActiveProductById(Long id){
        return service.getActiveProductById(id);

    }
    public void update(Long id, double newPrice){
        service.update(id, newPrice);
    }
    //udalenie po ego id
    public void deleteById(Long id){
        service.deleteById(id);

    }
    //udalenie produkta po naimenovaniu
    public void deleteByTitle(String title){
        service.deleteByTitle(title);
    }
    //vostanovit udalennii produkt
    public void restoreById(Long id){
        service.restoreById(id);
    }
    //vernut obshee kolichestvo produktov v baze dannih(aktivnih)
    public int getAllActiveProductsCount(){
        return service.getAllActiveProductsCount();
    }
    //vernut sumarnuiu stoimost vseh produktov v baze dannih(active)
    public double getAllActiveProductsTotalCost() {
        return service.getAllActiveProductsTotalCost();
    }
    //Vernut srednyuyu stoimost produkta v baze dannih
    public double getAllActiveProductAveragePrice() {
        return service.getAllActiveProductAveragePrice();


    }
}
