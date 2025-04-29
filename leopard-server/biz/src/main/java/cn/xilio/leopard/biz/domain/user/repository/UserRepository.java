package cn.xilio.leopard.biz.domain.user.repository;


import cn.xilio.leopard.biz.domain.user.model.User;

public interface UserRepository {

    public User saveUser(User user);

    User getByName(String username);
}
