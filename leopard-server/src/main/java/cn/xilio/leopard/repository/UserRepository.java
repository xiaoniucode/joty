package cn.xilio.leopard.repository;


import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.User;

public interface UserRepository {

    public User saveUser(User user);

    User findByUsername(String username);

    PageResponse<User> selectUsers(UserPageQueryRequest request);

    User findById(String userId);
}
