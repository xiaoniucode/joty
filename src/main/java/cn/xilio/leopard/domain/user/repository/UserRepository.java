package cn.xilio.leopard.domain.user.repository;

import cn.xilio.leopard.domain.user.model.User;

public interface UserRepository {

    public User saveUser(User user);

    User getByName(String username);
}
