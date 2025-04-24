package cn.xilio.leopard.infrastructure.repository.impl;


import cn.xilio.leopard.domain.user.model.User;
import cn.xilio.leopard.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class JpaUserRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;

    @Override
    public void saveUser(User user) {
        userEntityRepository.save(user);
    }

    @Override
    public User getByName(String username) {
        Specification<User> spec = (root, query, cb) ->
                cb.equal(root.get("username"), username);
        return userEntityRepository.findOne(spec).orElse(null);
    }
}

@Repository
interface UserEntityRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


}

