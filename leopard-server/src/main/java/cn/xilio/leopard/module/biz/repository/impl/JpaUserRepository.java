package cn.xilio.leopard.module.biz.repository.impl;



import cn.xilio.leopard.module.biz.domain.dataobject.User;
import cn.xilio.leopard.module.biz.repository.UserRepository;
import cn.xilio.leopard.module.biz.repository.dao.UserEntityRepository;
import jakarta.annotation.Resource;
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



