package cn.xilio.leopard.infrastructure.repository.impl;

import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.repository.GroupRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
     * @param group  Grouping entity
     * @param userId
     * @return Is the update successful
     */
    @Override
    public boolean updateById(Group group, String userId) {
        Specification<Group> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("id"), group.getId()));
            predicates.add(cb.equal(root.get("userId"), userId));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Optional<Group> existingGroup = groupEntityRepository.findOne(spec);
        if (existingGroup.isPresent()) {
            Group entity = existingGroup.get();
            entity.setName(group.getName());
            entity.setRemark(group.getRemark());
            groupEntityRepository.save(entity);
            return true;
        }
        return false;
    }

    /**
     * Retrieve all groups of the specified user
     *
     * @param userId USER ID
     * @return Grouped List
     */
    @Override
    public List<Group> getGroupsByUser(String userId) {
        Specification<Group> spec = (root, query, cb) ->
                cb.equal(root.get("userId"), userId);
        return groupEntityRepository.findAll(spec);
    }

    /**
     * Query grouping based on grouping ID
     *
     * @param groupId Group ID
     * @param userId User Id
     * @return Grouping entity, return null if it does not exist
     */
    @Override
    public Group getById(String groupId, String userId) {
        Specification<Group> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("id"), groupId));
            predicates.add(cb.equal(root.get("userId"), userId));
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
                cb.equal(root.get("userId"), userId);
        return groupEntityRepository.count(spec);
    }

    @Override
    public void deleteBatch(List<String> ids, String userId) {
        Specification<Group> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(root.get("id").in(ids));
            predicates.add(cb.equal(root.get("userId"), userId));
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
