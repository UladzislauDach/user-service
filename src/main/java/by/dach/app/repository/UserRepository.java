package by.dach.app.repository;

import by.dach.app.model.User;
import by.dach.app.model.UserIdEmailFields;
import by.dach.app.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "update users set role_id = :roleId where id = :userId", nativeQuery = true)
    void setRoleIdForUser(long userId, long roleId);

    @Modifying
    @Query(value = "update users set status = :userStatus where id = :id", nativeQuery = true)
    void setUserStatus(long id, String userStatus);

    default Map<Long, String> getEmailAndIdWhereUserStatusIsPending2() {
        return findAll().stream().filter(o -> o.getStatus() == UserStatus.PENDING)
                .collect(Collectors.toMap(User::getId, User::getEmail));
    }

    List<UserIdEmailFields> getUserByStatus(UserStatus userStatus);

    boolean existsByLoginAndPassword(String userLogin, String userPassword);

    boolean existsUserByLogin(String login);

    @Query(value = "select password from users where login = :login", nativeQuery = true)
    String getEncodedPasswordByLogin(String login);

}
