package cn.xilio.leopard.module.biz.repository;


import cn.xilio.leopard.module.biz.domain.dataobject.User;

public interface UserRepository {

    public User saveUser(User user);

    User getByName(String username);
}
