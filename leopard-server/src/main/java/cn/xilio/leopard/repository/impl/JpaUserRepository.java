package cn.xilio.leopard.repository.impl;


import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.core.security.SecurityUtils;
import cn.xilio.leopard.domain.dataobject.ShortUrl;
import cn.xilio.leopard.domain.dataobject.User;
import cn.xilio.leopard.repository.UserRepository;
import cn.xilio.leopard.repository.dao.UserEntityRepository;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class JpaUserRepository implements UserRepository {
    @Resource
    private UserEntityRepository userEntityRepository;

    @Override
    public User saveUser(User user) {
        return userEntityRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Specification<User> spec = (root, query, cb) ->
                cb.equal(root.get("username"), username);
        return userEntityRepository.findOne(spec).orElse(null);
    }

    @Override
    public PageResponse<User> selectUsers(UserPageQueryRequest request) {
        int page = request.getPage();
        int size = request.getSize();
        Specification<User> spec = (root, query, cb) -> {
            Predicate predicate = cb.equal(root.get("deleted"), 0);
            return predicate;
        };
        PageRequest pageRequest = PageRequest.of(page < 1 ? 0 : (page - 1), size, Sort.by(Sort.Direction.ASC, "sort"));
        Page<User> entityPage = userEntityRepository.findAll(spec, pageRequest);
        return PageResponse.of(
                entityPage.getContent(),
                (int) entityPage.getTotalElements(),
                entityPage.getNumber() + 1,
                entityPage.getSize(),
                entityPage.hasNext()
        );
    }

    @Override
    public User findById(String userId) {
        return userEntityRepository.findById(userId).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDeleteUser(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        //分批处理优化 每次最多删除2000条
        Lists.partition(ids, 2000).forEach(batch ->
                userEntityRepository.updateDeletedStatusByIds(batch)
        );
    }

    @Override
    public String findApiKeyByUserId(String userId) {
        return userEntityRepository.findApiKeyById(userId);
    }
}



