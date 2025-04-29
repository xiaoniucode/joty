package cn.xilio.leopard.biz.infrastructure.repository.impl;


import cn.xilio.leopard.biz.domain.user.model.User;
import cn.xilio.leopard.biz.domain.user.repository.UserRepository;
import cn.xilio.leopard.biz.infrastructure.database.UserEntityRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


@Repository
public class JpaUserRepository implements UserRepository {
    @Resource
    private UserEntityRepository userEntityRepository;

    @Override
    public User saveUser(User user) {
        return userEntityRepository.save(user);
    }

    @Override
    public User getByName(String username) {
        Specification<User> spec = (root, query, cb) ->
                cb.equal(root.get("username"), username);
        return userEntityRepository.findOne(spec).orElse(null);
    }
}



