package shop.service;

import shop.domain.Product;
import shop.exceptions.product.ProductNotFoundExeption;
import shop.exceptions.product.ProductRestoreExeption;
import shop.exceptions.product.ProductSaveExeption;
import shop.repository.ProductRepository;

import java.util.List;

public class ProductService {
// servis produktov,eto tretii sloi prilogeniya,zadacha obispechivat vsiu biznes logiku
//
        private final ProductRepository repository;
        private static ProductService instance;
        //konstruktor
        private ProductService(){

          repository = new ProductRepository();
            //
   }
   public static ProductService getInstance() {
            if (instance == null) {
                instance = new ProductService();
            }
            return instance;
   }
   public Product save(Product product){
            if (product == null){
                throw new ProductSaveExeption("Sohranyaemii obiekt produkta ne moget bit null");
            }
            String title = product.getTitle();
            if (title == null || title.trim().isEmpty()){
                throw new ProductSaveExeption("Nazvanie sohranyaemogo produkta ne moget bit pustim");
            }

            if (product.getPrice() < 0){
                throw new ProductSaveExeption("Cena sohranyaemogo produkta ne moget bit otrecatelnoi");
            }
            product.setActive(true);
            return repository.save(product);
   }

// Vernut vse produkti iz bazi
    public List<Product> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .toList();

    }
    public Product getActiveProductById(Long id){
           Product product = repository.findById(id);

           if(product == null || !product.isActive()){
               throw new ProductNotFoundExeption(id);
           }
           return product;
    }
    public void update(Long id, double newPrice){
        if (newPrice < 0) {
            throw new ProductSaveExeption("Novaya cena  produkta ne moget bit otrecatelnoi");
        }
        getActiveProductById(id);
        repository.update(id, newPrice);
    }
    //udalenie po ego id
    public void deleteById(Long id){
            getActiveProductById(id).setActive(false);
    }
    //udalenie produkta po naimenovaniu
    public void deleteByTitle(String title){
            getAllActiveProducts()
                    .stream()
                    .filter(x -> x.getTitle().equals(title))
                    .forEach(x -> x.setActive(false));

    }
    //vostanovit udalennii produkt
    public void restoreById(Long id){
            Product product = repository.findById(id);

            if (product == null){
                throw new ProductRestoreExeption(String.format("Produkt s id %d ne sushestvuet", id));

            }
            if (product.isActive()){
                throw new ProductRestoreExeption(
                        String.format("Produkt s id %d uge aktiven", id));

            }
            product.setActive(true);
    }
    //vernut obshee kolichestvo produktov v baze dannih(aktivnih)
    public int getAllActiveProductsCount(){
            return getAllActiveProducts().size();
    }
    //vernut sumarnuiu stoimost vseh produktov v baze dannih(active)
    public double getAllActiveProductsTotalCost() {
            return getAllActiveProducts()
                    .stream()
                    .mapToDouble(Product::getPrice)
                    .sum();
    }
    //Vernut srednyuyu stoimost produkta v baze dannih
    public double getAllActiveProductAveragePrice() {
            int productsCount = getAllActiveProductsCount();
            if (productsCount == 0){
                return 0;
            }
            return getAllActiveProductsTotalCost() / getAllActiveProductsCount();
    }
}
