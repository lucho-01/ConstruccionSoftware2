package app.infrastructure.mapper;

import java.time.LocalDate;
/*import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import app.domain.model.User;
import app.infrastructure.entities.UserEntity;

@Component
public class UserMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static UserEntity toEntity(User user) {
        if (user == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setDocument(user.getDocument());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setAddress(user.getAddress());
        
        if (user.getBirthdate() != null && !user.getBirthdate().isEmpty()) {
            entity.setBirthdate(LocalDate.parse(user.getBirthdate(), FORMATTER));
        }

        entity.setRole(user.getRole());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());

        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User user = new User();
        user.setId(entity.getId());
        user.setDocument(entity.getDocument());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setFullName(entity.getFullName());
        user.setEmail(entity.getEmail());
        user.setAddress(entity.getAddress());
        
        if (entity.getBirthdate() != null) {
            user.setBirthdate(entity.getBirthdate().format(FORMATTER));
        }

        user.setRole(entity.getRole());
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());

        return user;
    }
}
*/
