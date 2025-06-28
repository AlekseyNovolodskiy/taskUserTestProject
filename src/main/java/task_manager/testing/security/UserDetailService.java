package task_manager.testing.security;



import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import task_manager.testing.entity.UserEntity;
import task_manager.testing.repositories.UserRepository;

@Service
@Data
@Lazy
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntitiesByEmail = userRepository.findUserEntitiesByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + username + " not found"));


        return User.builder()
                .username(userEntitiesByEmail.getEmail())
                .password(userEntitiesByEmail.getPassword())
                .roles(String.valueOf(userEntitiesByEmail.getRole()))
                .build();
    }
}

