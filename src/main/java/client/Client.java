package client;

import shop.controller.CustomerController;
import shop.controller.ProductController;
import shop.domain.Customer;
import shop.domain.Product;

import java.util.Scanner;

public class Client {
    private static ProductController productController;
    private static CustomerController customerController;
    private static Scanner scanner;

    public static void main(String[] args) {
        productController = new ProductController();
        customerController = new CustomerController();
        scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equals(0)) {
            try {
                System.out.println("Vibirete operaciyu");
                System.out.println("1-operaciya s produktami");
                System.out.println("2-operaciya s pokupatelyami");
                System.out.println("0-vihod ");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        productOperations();
                    case "2":
                        customerOperations();
                    case "0":
                        System.out.println("Vihod iz programmi");
                        break;
                    default:
                        System.out.println("Nekorektnii vvod");

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void productOperations() {
        String choice = "";
        while (!choice.equals("0")) {
            try {
                System.out.println("Viberite operaciyu s produktami:");
                System.out.println("1. Sohranenie produkta");
                System.out.println("2. Poluchenie vseh produktov");
                System.out.println("3. Poluchenie produkta po identifikatoru");
                System.out.println("4. Obnovlenie produkta");
                System.out.println("5. Udalenie produkta po id");
                System.out.println("6. Udalenie po nazvaniyu");
                System.out.println("7. Vostanovlenie po id");
                System.out.println("8. Poluchenie kolichestva produktov");
                System.out.println("9. Poluchenie obshei stoimosti produktov");
                System.out.println("10. Poluchenie srednei ceni produkta");

                System.out.println("0. Vihod");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Vvedite nazvanie produkta");
                        String title = scanner.nextLine();
                        System.out.println("Vvedite cenu produkta");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.println(productController.save(title, price));
                        break;
                    case "2":
                        productController.getAllActiveProducts().forEach(System.out::println);
                        break;
                    case "3":

                        System.out.println("Vvedite id produkta");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.println(productController.getActiveProductById(id));
                        break;
                    case "4":
                        System.out.println("Vvedite id produkta");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Vvedite novuyu cenu produkta");
                        price = Double.parseDouble(scanner.nextLine());
                        productController.update(id, price);
                        break;
                    case "5":
                        System.out.println("Vvedite id produkta");
                        id = Long.parseLong(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Vvedite nazvanie produkta");
                        title = scanner.nextLine();
                        productController.deleteByTitle(title);
                        break;
                    case "7":
                        System.out.println("Vvedite id produkta");
                        id = Long.parseLong(scanner.nextLine());
                        productController.restoreById(id);
                        break;
                    case "8":
                        System.out.println(productController.getAllActiveProductsCount());
                        break;
                    case "9":
                        System.out.println(productController.getAllActiveProductsTotalCost());
                        break;
                    case "10":
                        System.out.println(productController.getAllActiveProductAveragePrice());
                        break;


                    case "0":
                        System.out.println("Vihod v glavnoe menu");
                        break;
                    default:
                        System.out.println("Nekorrektnii vvod");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private static void customerOperations() {
        String choice = "";
        while (!choice.equals("0")) {
            try {
                System.out.println("Viberite operaciyu s pokupatelyami: ");
                System.out.println("1. Sohranenie pokupatilya");
                System.out.println("2. Poluchenie vseh pokupatelei");
                System.out.println("3. Poluchenie pokupatelya po identifikatoru");
                System.out.println("4. Obnovlenie pokupatelya");
                System.out.println("5. Udalenie pokupatelya po id");
                System.out.println("6. Udalenie pokupatelya po imeni");
                System.out.println("7. Vostanovlenie pokupatelya po id");
                System.out.println("8. Poluchenie kolichestva pokupatelei");
                System.out.println("9. Poluchenie obshei stoimosti korzini");
                System.out.println("10. Poluchenie srednei ceni tovara v korzine pokupatelya");
                System.out.println("13. ");
                System.out.println("");
                System.out.println("");
                System.out.println("0. Vihod");

                choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        System.out.println("Vvedite imya pokupatelya : ");
                        String name = scanner.nextLine();
                        System.out.println(customerController.save(name));
                        break;
                    case "2":
                        customerController.getAllActiveCustomers().forEach(System.out::println);

                        break;
                    case "3":
                        System.out.println("Vvedite id pokupatelya");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.println(customerController.getActiveCustomerById(id));
                        break;

                    case "4":
                        System.out.println("Vvedite id pokupatelya");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Vvedite novoe imya pokupatelya");
                        name = scanner.nextLine();
                        customerController.update(id, name);
                        break;
                    case "5":
                        System.out.println("Vvedite id pokupatelya");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Vvedite imya pokupatelya : ");
                        name = scanner.nextLine();
                        customerController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Vvedite id pokupatelya");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.restoreById(id);
                        break;
                    case "8":
                        System.out.println(customerController.getAllActiveCustomersNumber());
                        break;
                    case "9":
                        System.out.println("Vvedite id pokupatelya");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println(customerController.getCustomersCartTotalCost(id));
                        break;
                    case "10":
                        System.out.println("Vvedite id pokupatelya");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println(customerController.getCustomersCartAveragePrice(id));
                        break;
                    case "11":
                        System.out.println("Vvedite id pokupatelya");
                        Long customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Vvedite id produkta");
                        Long productId = Long.parseLong(scanner.nextLine());
                        customerController.addProductToCustomersCart(customerId, productId);

                        break;
                    case "12":
                        System.out.println("Vvedite id pokupatelya");
                        customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Vvedite id produkta");
                        productId = Long.parseLong(scanner.nextLine());
                        customerController.removeProductFromCustomerCart(customerId, productId);
                        break;
                    case "13":
                        System.out.println("Vvedite id pokupatelya");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.clearCustomersCart(id);

                        break;
                    case "0":
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }
    }
}


