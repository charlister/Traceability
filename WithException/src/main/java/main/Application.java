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
            case "q":
                quit = true;
                break;
            default:
                System.err.println("Invalid option !");
        }
    }

    void productMenu() throws InterruptedException {
        productMenuBuffer.append("c : register a product\n");
        productMenuBuffer.append("r : display all my products\n");
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
