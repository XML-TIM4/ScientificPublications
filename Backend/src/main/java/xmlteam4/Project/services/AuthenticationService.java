package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xmlteam4.Project.DTOs.UserDTO;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.mappers.UserMapper;
import xmlteam4.Project.repositories.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserDTO register(UserDTO user) throws RepositoryException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }
}
