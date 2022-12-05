package repository;

import exception.UserCollectionException;
import model.User;

public interface IUserRepository {
    boolean createUser(User user) throws UserCollectionException;
    User connexion(String email, String password) throws UserCollectionException;
}
