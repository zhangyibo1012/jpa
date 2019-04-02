package cn.orgtec.jpa.repository;

import cn.orgtec.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>UserRepository.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User ,Integer> {
}
