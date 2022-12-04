package repository;

import exception.UserCollectionException;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {
    List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public UserRepository(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean createUser(User user) throws UserCollectionException {
        Optional<User> userWrapper = this.users
                .stream()
                .filter(u ->u.getEmail().equals(user.getEmail()))
                .findFirst();
        if (userWrapper.isPresent()) {
            throw new UserCollectionException(UserCollectionException.alreadyExists(user.getEmail()));
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
            throw new UserCollectionException(UserCollectionException.badIdentifiers());
        }
        return userWrapper.get();
    }
}
