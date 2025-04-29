package cn.xilio.leopard.repository;


import cn.xilio.leopard.domain.dataobject.User;

public interface UserRepository {

    public User saveUser(User user);

    User getByName(String username);
}
