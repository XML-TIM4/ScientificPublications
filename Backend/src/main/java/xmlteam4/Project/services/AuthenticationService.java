package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xmlteam4.Project.DTOs.UserDTO;
import xmlteam4.Project.exceptions.EntityAlreadyExistsException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.mappers.UserMapper;
import xmlteam4.Project.model.ObjectFactory;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.UserRepository;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IDGenerator idGenerator;


    public UserDTO register(UserDTO user) throws RepositoryException, EntityAlreadyExistsException {
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            throw new EntityAlreadyExistsException("User already exists");
        }

        ObjectFactory objectFactory = new ObjectFactory();
        TUser newUser = objectFactory.createTUser();
        newUser.setId(idGenerator.createID());
        newUser.setExpertise(user.getExpertise());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEditor(user.getEditor());

        return userMapper.toDTO(userRepository.save(newUser));
    }
}
