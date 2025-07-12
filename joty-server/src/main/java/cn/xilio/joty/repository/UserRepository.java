package cn.xilio.joty.repository;


import cn.xilio.joty.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.joty.core.common.page.PageResponse;
import cn.xilio.joty.domain.dataobject.User;

import java.util.List;

public interface UserRepository {

    public User saveUser(User user);

    User findByUsername(String username);

    PageResponse<User> selectUsers(UserPageQueryRequest request);

    User findById(String userId);

    void logicDeleteUser(List<String> ids);

    String findApiKeyByUserId(String userId);
}
