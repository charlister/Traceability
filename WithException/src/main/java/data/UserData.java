package data;

import exception.UserCollectionException;
import model.User;
import repository.UserRepository;

import java.util.Random;

public class UserData {

    public UserData(UserRepository repository) throws UserCollectionException {
        this.buildUsers(repository);
    }

    private void buildUsers(UserRepository repository) throws UserCollectionException {
        Random rand = new Random();
        for (int i = 0; i<2; i++) {
            if(!repository.createUser(new User("userId"+i, 18 + rand.nextInt(43), "user"+i+"@gmail.com", "password"))) {
                System.err.println(UserCollectionException.notCreated());
            }
        }
    }
}
