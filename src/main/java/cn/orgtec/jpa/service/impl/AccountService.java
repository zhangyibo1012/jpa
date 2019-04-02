package cn.orgtec.jpa.service.impl;

import cn.orgtec.jpa.entity.Account;
import cn.orgtec.jpa.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * <p>AccountService.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public int add(Account account) {
        accountRepository.save(account);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)

    public Account updataAccount(Account account){
        //插入和更新同一个方法，内部原理先根据id查一下，没有insert,有update
       return accountRepository.save(account);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="account", allEntries=true)
    public int updateAc(Account account){
        int i =accountRepository.updateAccount(account.getId(),account.getBankNumber());
        return i;
    }

    @Cacheable(value = "account", key = "#roleId  + '_menu'")
    public Account findById(int id) {
        return accountRepository.findById(id).orElse(null);

    }

    public List<Account> findList() {
        return accountRepository.findAll();
    }

    public List<Account> findByCondition(Account account){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //返回类型 select * 中的 *
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        //form
        Root<Account> root = query.from(Account.class);
        //where条件
        Predicate predicate1 = criteriaBuilder.like(root.get("bankNumber"), "%" + account.getBankNumber() + "%");
        query.where(predicate1);
        TypedQuery<Account> typedQuery = entityManager.createQuery(query);
        List<Account> resultList = typedQuery.getResultList();
        return resultList;
    }
    public List<Integer> findIdsByCondition(String bankNumber) {

        CriteriaBuilder criteriaBuilder =
                entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<Account> root = query.from(Account.class);
        //返回指定列
        query.select(root.get("id"));

        Predicate pre1 = criteriaBuilder.like(root.get("bankNumber"), "%" + bankNumber + "%");
        query.where(pre1);

        List<Integer> resultList = entityManager.createQuery(query).getResultList();
        return resultList;

    }

    /**
     * Cacheable
     *  value 缓存名称 key 缓存key 不指定默认所有方法参数名称 如果指定 按照 spel表达式指定
     *  unless 当结果是null时不缓存
     *
     *    @CacheEvict是用来标注在需要清除缓存元素的方法或类上的
     *      @CacheEvict(value="users", allEntries=true)  需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。
     *
     * @param bankNumber
     * @return
     */
    @Cacheable(value = "findByBankNum", key = "#bankNumber", unless = "#result == null")
    public List<Account> findByBankNum(String bankNumber){
        CriteriaBuilder criteriaBuilder =
                entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        //通过构造函数赋值，顺序要一致，返回结果只有这三列值
        query.multiselect(root.get("id"),root.get("bankNumber"),root.get("version"));

        Predicate pre1 = criteriaBuilder.like(root.get("bankNumber"), "%" + bankNumber + "%");
        query.where(pre1);

        List<Account> resultList = entityManager.createQuery(query).getResultList();
        return resultList;
    }

}
