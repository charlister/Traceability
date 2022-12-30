package main;

import data.ProductData;
import data.UserData;
import exception.ProductCollectionException;
import exception.UserCollectionException;
import model.Product;
import model.User;
import repository.ProductRepository;
import repository.UserRepository;

import java.util.Date;
import java.util.Scanner;

public class Application {
    private StringBuffer connexionMenuBuffer = new StringBuffer();
    private StringBuffer productMenuBuffer = new StringBuffer();
    private boolean isConnected = false;
    private User userConnected;
    private boolean quit = false;
    private Scanner sc;

    UserRepository userRepository = new UserRepository();
    ProductRepository productRepository = new ProductRepository();

    Application() throws InterruptedException {
        connexionMenu();
        productMenu();
        initRepositories();

        this.sc = new Scanner(System.in);
    }

    /**
     * all operations
     * @throws UserCollectionException
     */
    private void scenarioUser1() throws UserCollectionException {
        User user;
        Product product;

        user = userRepository.connexion("user1@gmail.com", "password");

        productRepository.getProducts(user.getId());

        product = new Product(1000, "product1000", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.getProductById(1000, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1001, "product1001", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1002, "product1002", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1001, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1003, "product1003", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1003, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1004, "product1004", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.getProductById(1004, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        Product p = new Product(1004, "product1004", product.getPrice()+5, new Date(), product.getUserId());
        try {
            productRepository.updateProduct(p, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * create operation
     * @throws UserCollectionException
     */
    private void scenarioUser2() throws UserCollectionException {
        User user;
        Product product;

        user = userRepository.connexion("user2@gmail.com", "password");

        productRepository.getProducts(user.getId());

        product = new Product(1005, "product1005", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1006, "product1006", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1007, "product1007", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1008, "product1008", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1009, "product1009", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1010, "product1010", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1011, "product1011", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1012, "product1012", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1013, "product1013", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1014, "product1014", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * create and delete operations
     * @throws UserCollectionException
     */
    private void scenarioUser3() throws UserCollectionException {
        User user;
        Product product;

        user = userRepository.connexion("user3@gmail.com", "password");

        productRepository.getProducts(user.getId());

        product = new Product(1015, "product1015", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1015, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1016, "product1016", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1016, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1017, "product1017", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1017, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1018, "product1018", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1018, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1019, "product1019", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1019, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        product = new Product(1020, "product1020", 1, new Date(), user.getId());
        try {
            productRepository.addProduct(product, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }

        try {
            productRepository.deleteProduct(1020, user.getId());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }
    }

    void initRepositories() {
        try {
            new UserData(userRepository);
            new ProductData(productRepository);
        } catch (UserCollectionException e) {
            System.err.println(e.getMessage());
        } catch (ProductCollectionException e) {
            System.err.println(e.getMessage());
        }
    }

    void connexionMenu() throws InterruptedException {
        connexionMenuBuffer.append("i : sign in\n");
        connexionMenuBuffer.append("u : sign up\n");
        connexionMenuBuffer.append("l : launch scenarios and quit\n");
        connexionMenuBuffer.append("q : quit");
        Thread.sleep(1000);
    }

    void displayConnexionMenu() {
        System.out.println(connexionMenuBuffer);
    }

    void chooseOptionConnexion() {
        System.err.print("choice : ");
        String choice = this.sc.nextLine().trim();
        String email;
        String password;
        switch(choice) {
            case "i":
                System.err.print("email : ");
                email = this.sc.nextLine().trim();
                System.err.print("password : ");
                password = this.sc.nextLine().trim();
                User user = null;
                try {
                    user = userRepository.connexion(email, password);
                } catch (UserCollectionException e) {
                    System.err.println(e.getMessage());
                }
                if(user != null) {
                    isConnected = true;
                    userConnected = user;
                }
                break;
            case "u":
                System.err.print("name : ");
                String name = this.sc.nextLine().trim();
                System.err.print("age : ");
                int age = Integer.parseInt(this.sc.nextLine().trim());
                System.err.print("email : ");
                email = this.sc.nextLine().trim();
                System.err.print("password : ");
                password = this.sc.nextLine().trim();
                try {
                    userRepository.createUser(new User(name, age, email, password));
                } catch (UserCollectionException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "l":
                try {
                    scenarioUser1();
                } catch (UserCollectionException e) {
                    System.err.println("Problem detected in user1's scenario");
                }
                try {
                    scenarioUser2();
                } catch (UserCollectionException e) {
                    System.err.println("Problem detected in user2's scenario");
                }
                try {
                    scenarioUser3();
                } catch (UserCollectionException e) {
                    System.err.println("Problem detected in user3's scenario");
                }
                quit = true;
                break;
            case "q":
                quit = true;
                break;
            default:
                System.err.println("Invalid option !");
                break;
        }
    }

    void productMenu() throws InterruptedException {
        productMenuBuffer.append("c : register a product\n");
        productMenuBuffer.append("rps : display all my products\n");
        productMenuBuffer.append("rp : display a product\n");
        productMenuBuffer.append("u : change a product\n");
        productMenuBuffer.append("d : remove a product\n");
        productMenuBuffer.append("o : sign out\n");
        productMenuBuffer.append("q : quit");
        Thread.sleep(1000);
    }

    void displayProductMenu() {
        System.out.println(productMenuBuffer);
    }

    Product writeProduct() {
        System.err.print("id : ");
        long id = Long.parseLong(this.sc.nextLine().trim());
        System.err.print("name : ");
        String name = this.sc.nextLine().trim();
        System.err.print("price : ");
        float price = Float.parseFloat(this.sc.nextLine().trim());
        System.err.print("expiration date : ");
        System.err.print("\tyear (YYYY) : ");
        int year = Integer.parseInt(this.sc.nextLine().trim());
        System.err.print("\tmonth (mm) : ");
        int month = Integer.parseInt(this.sc.nextLine().trim());
        System.err.print("\tday (dd) : ");
        int day = Integer.parseInt(this.sc.nextLine().trim());
        return new Product(id, name, price, new Date(year, month, day), userConnected.getId());
    }

    void chooseOptionProduct() {
        long id;
        System.err.print("choice : ");
        String choice = this.sc.nextLine().trim();
        switch(choice) {
            case "c":
                try {
                    productRepository.addProduct(writeProduct(), userConnected.getId());
                } catch (ProductCollectionException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "rps":
                productRepository.getProducts(userConnected.getId()).forEach(product ->  {
                    System.out.println(product);
                });
                break;
            case "rp":
                System.err.print("product id : ");
                id = Long.parseLong(this.sc.nextLine().trim());
                try {
                    Product product = productRepository.getProductById(id, userConnected.getId());
                    System.out.println(product);
                } catch (ProductCollectionException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "u":
                try {
                    productRepository.updateProduct(writeProduct(), userConnected.getId());
                } catch (ProductCollectionException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "d":
                System.err.print("product id : ");
                id = Long.parseLong(this.sc.nextLine().trim());
                try {
                    productRepository.deleteProduct(id, userConnected.getId());
                } catch (ProductCollectionException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "o":
                isConnected = false;
                break;
            case "q":
                quit = true;
                break;
            default:
                System.err.print("Invalid option !");
        }
    }

    void run() {
        while (!quit) {
            if (isConnected) {
                displayProductMenu();
                chooseOptionProduct();
            }
            else {
                displayConnexionMenu();
                chooseOptionConnexion();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        app.run();
        System.err.print("See you later !");
    }
}
