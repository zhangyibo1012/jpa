package cn.orgtec.jpa.service.impl;

import cn.orgtec.jpa.entity.User;
import cn.orgtec.jpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * <p>UserService.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private EntityManager entityManager;

    public User getUserWithAccount(Integer id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
//        Join<User, Account> account = root.join(root.getModel().getSingularAttribute("account", Account.class), JoinType.LEFT);
        Predicate pre = criteriaBuilder.equal(root.get("id"), id);
        query.where(pre);
        User singleResult = entityManager.createQuery(query).getSingleResult();
        return singleResult;

    }

    public List<User> getUserWithAccountList(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        return  entityManager.createQuery(query).getResultList();

    }

    @Transactional(rollbackFor = Exception.class)
    public int add(User user) {
        userRepository.save(user);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(User user) {
        //插入和更新同一个方法，内部原理先根据id查一下，没有insert,有update
        userRepository.save(user); 
        return 1;
    }


    public int delete(int id) {
        userRepository.deleteById(id);
        return 1;
    }

    public User findById(int id) {
        return userRepository.findById(id).get();

    }

    public List<User> findList() {
        return userRepository.findAll();
    }
}
