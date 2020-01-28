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

        user.setId(dto.getId());
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setEditor(dto.getEditor());
        user.setPassword(dto.getPassword());
        user.setExpertise(dto.getExpertise());

        return user;
    }

    @Override
    public UserDTO toDTO(TUser tUser) {
        return new UserDTO(tUser.getPassword(), tUser.getEmail(), tUser.getId(), tUser.isEditor(), tUser
                .getExpertise());
    }
}
