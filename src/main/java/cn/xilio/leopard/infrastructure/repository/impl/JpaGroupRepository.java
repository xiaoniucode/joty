package cn.xilio.leopard.infrastructure.repository.impl;

import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.repository.GroupRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@AllArgsConstructor
public class JpaGroupRepository implements GroupRepository {

    private final GroupEntityRepository groupEntityRepository;

    /**
     * Delete a group based on its ID
     *
     * @param groupId Group ID
     * @param userId
     * @return Was the deletion successful
     */
    @Override
    public long deleteById(String groupId, String userId) {
        Specification<Group> spec = (root, query, cb) ->
                cb.and(
                        cb.equal(root.get("createdBy"), userId),
                        cb.equal(root.get("id"), groupId)
                );
        return groupEntityRepository.delete(spec);
    }

    /**
     * Update group information based on group ID
     *
     * @param group Grouping entity
     * @return Is the update successful
     */
    @Override
    public boolean saveGroup(Group group) {
        groupEntityRepository.save(group);
        return true;
    }

    /**
     * Retrieve all groups of the specified user
     *
     * @param query  Page param
     * @param userId USER ID
     * @return Grouped List
     */
    @Override
    public PageResponse<Group> getGroupsByUser(PageQuery query, String userId) {
        int page = query.getPage();
        int size = query.getSize();
        Specification<Group> spec = (root, query1, cb) ->
                cb.equal(root.get("createdBy"), userId);
        PageRequest pageRequest = PageRequest.of(
                page < 1 ? 0 : (page - 1),
                size,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        Page<Group> entityPage = groupEntityRepository.findAll(spec, pageRequest);
        return PageResponse.of(
                entityPage.getContent(),
                (int) entityPage.getTotalElements(),
                entityPage.getNumber() + 1,
                entityPage.getSize(),
                entityPage.hasNext()
        );
    }


    /**
     * Query grouping based on grouping ID
     *
     * @param groupId Group ID
     * @param userId  User Id
     * @return Grouping entity, return null if it does not exist
     */
    @Override
    public Group getById(String groupId, String userId) {
        Specification<Group> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("id"), groupId));
            predicates.add(cb.equal(root.get("createdBy"), userId));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return groupEntityRepository.findOne(spec).orElse(null);
    }

    /**
     * Get the number of groups for the specified user
     *
     * @param userId USER ID
     * @return Number of groups
     */
    @Override
    public long getCountByUser(String userId) {
        Specification<Group> spec = (root, query, cb) ->
                cb.equal(root.get("createdBy"), userId);
        return groupEntityRepository.count(spec);
    }

    @Override
    public void deleteBatch(List<String> ids, String userId) {
        Specification<Group> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(root.get("id").in(ids));
            predicates.add(cb.equal(root.get("createdBy"), userId));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        List<Group> groups = groupEntityRepository.findAll(spec);
        if (!groups.isEmpty()) {
            groupEntityRepository.deleteAll(groups);
        }
    }
}

@Repository
interface GroupEntityRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

}
