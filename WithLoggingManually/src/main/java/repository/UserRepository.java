package repository;

import exception.UserCollectionException;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository implements IUserRepository {
    // insertion de LOGGER
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    // insertion Handler pour fichier
    Handler fileHandler  = null;
    List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        try {
            // créer le fileHander
            fileHandler  = new FileHandler("./traceability.log");

            // ajouter le fileHander à LOGGER
//            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            // modifier le niveau de verbosité autorisé dans fileHander et LOGGER
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            // ajouter une invocation de log
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

    public UserRepository(List<User> users) {
        this.users = users;
        try {
            // créer le fileHander
            fileHandler  = new FileHandler("./traceability.log");

            // ajouter le fileHander à LOGGER
//            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            // modifier le niveau de verbosité autorisé dans fileHander et LOGGER
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            // ajouter une invocation de log
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

    @Override
    public boolean createUser(User user) throws UserCollectionException {
        Optional<User> userWrapper = this.users
                .stream()
                .filter(u ->u.getEmail().equals(user.getEmail()))
                .findFirst();
        if (userWrapper.isPresent()) {
//            throw new UserCollectionException("an account already exists with this email address: "+user.getEmail());
            LOGGER.log(Level.WARNING, "an account already exists with this email address: "+user.getEmail());
            return false;
        }
        return this.users.add(user);
    }

    @Override
    public User connexion(String email, String password) throws UserCollectionException {
        Optional<User> userWrapper = this.users
                .stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
        if (userWrapper.isEmpty()) {
//            throw new UserCollectionException("invalid email or password");
            LOGGER.log(Level.WARNING, "invalid email or password");
            return null;
        }
        return userWrapper.get();
    }
}
