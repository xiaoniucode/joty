package cn.xilio.leopard.infrastructure.repository.impl;

import cn.xilio.leopard.domain.user.model.User;
import cn.xilio.leopard.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class JpaUserRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;

    @Override
    public void saveUser() {
        userEntityRepository.save(new User());
    }
}

@Repository
interface UserEntityRepository extends JpaRepository<User, String> {


}
