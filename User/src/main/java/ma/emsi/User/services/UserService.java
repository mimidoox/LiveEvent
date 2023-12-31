package ma.emsi.User.services;

import ma.emsi.User.entities.User;
import ma.emsi.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        // Additional logic can be added here, e.g., validation, default values, etc.
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        // Additional logic can be added here, e.g., validation, updating specific fields, etc.
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElse(null); // Handle the case where the user with the given ID is not found
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
