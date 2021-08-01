package by.dach.app.repository;

import by.dach.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query(value = "update users set role_id = :roleId where id = :userId", nativeQuery = true)
    void setRoleIdForUser(long userId, long roleId);

}
