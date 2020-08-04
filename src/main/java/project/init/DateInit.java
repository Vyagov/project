package project.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.model.entity.Role;
import project.model.entity.User;
import project.repository.UserRepository;

import java.util.List;

@Component
public class DateInit implements CommandLineRunner {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DateInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRepository.count() == 0) {
            // admin
            User admin = new User();
            admin.setFirstName("Ivan");
            admin.setLastName("Admin");
            admin.setUsername("admin");
            admin.setEmail("ivan@admin.com");
            admin.setPassword(this.passwordEncoder.encode("admin"));

            Role adminAdminRole = new Role();
            adminAdminRole.setRole("ROLE_ADMIN");

            Role adminUserRole = new Role();
            adminUserRole.setRole("ROLE_USER");

            admin.setRoles(List.of(adminAdminRole, adminUserRole));

            this.userRepository.saveAndFlush(admin);

            // user
            User user = new User();
            user.setFirstName("Victor");
            user.setLastName("User");
            user.setUsername("user");
            user.setEmail("victor@user.com");
            user.setPassword(this.passwordEncoder.encode("user"));

            Role userUserRole = new Role();
            userUserRole.setRole("ROLE_USER");

            user.setRoles(List.of(userUserRole));

            this.userRepository.saveAndFlush(user);
        }
    }
}