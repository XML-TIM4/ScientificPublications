package xmlteam4.Project.mappers;

import org.springframework.stereotype.Component;
import xmlteam4.Project.DTOs.UserDTO;
import xmlteam4.Project.model.ObjectFactory;
import xmlteam4.Project.model.TUser;

@Component
public class UserMapper implements IMapper<TUser, UserDTO> {
    @Override
    public TUser toEntity(UserDTO dto) {
        ObjectFactory objectFactory = new ObjectFactory();
        TUser user = objectFactory.createTUser();

        user.setAuthorId(dto.getAuthorId());
        user.setEmail(dto.getEmail());
        user.setIsEditor(dto.getEditor());
        user.setPassword(dto.getPassword());
        user.setPublicationRoles(new TUser.PublicationRoles());

        return user;
    }

    @Override
    public UserDTO toDTO(TUser tUser) {
        return new UserDTO(tUser.getUsername(), tUser.getPassword(), tUser.getEmail(), tUser.isIsEditor(),
                tUser.getAuthorId());
    }
}
