package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleRepository;
import ru.kata.spring.boot_security.demo.DAO.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    //    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("BEFORE!!!");
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {

//        if (isThereAnyRoles()) {
//            System.out.println("SAVEROLESCHECK!!!!!");
//            em.createNativeQuery("INSERT INTO t_role(id, name) where VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN') ");
//        }

        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(new HashSet<>());
        System.out.println("BEFORE!!!!!!!!!!!!!!");
        user.getRoles().add(new Role(1L, "ROLE_USER"));
        System.out.println("AFTER!!!!!!!!!!!!!!!");
        //user.getRoles().add(new Role(2L, "ROLE_ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        System.out.println("SAVE!!!!!!!!!!!!!!!");
        return true;
    }

    public boolean isThereAnyRoles() {
        System.out.println("CHECK ROLES!!!!!");
        List<Role> roles = em.createNativeQuery("SELECT * FROM t_role").getResultList();
        return (roles.isEmpty());
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Transactional
    public void updateUser(Long userId, User updatedUser) {
        User oldUser = userRepository.findById(userId).get();
        updatedUser.setRoles(oldUser.getRoles());
        updatedUser.setUsername(oldUser.getUsername());
        updatedUser.setPassword(oldUser.getPassword());
        userRepository.save(updatedUser);
    }
}
