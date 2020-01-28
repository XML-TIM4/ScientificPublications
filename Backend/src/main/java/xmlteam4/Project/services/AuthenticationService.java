package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xmlteam4.Project.DTOs.UserDTO;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.mappers.UserMapper;
import xmlteam4.Project.repositories.UserRepository;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;

import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;

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
        user.setId(TARGET_NAMESPACE + "/users/" + IDGenerator.createID());

        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }
}
