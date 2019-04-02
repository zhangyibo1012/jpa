package cn.orgtec.jpa.repository;

import cn.orgtec.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>AccountRepository.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Repository
public interface AccountRepository extends JpaRepository<Account ,Integer> {

    @Modifying
    @Query("update Account set bankNumber=:bankNumber where id=:id")
    int updateAccount(@Param("id") int id, @Param("bankNumber") String bankNumber);


//    @Modifying  //通知jpa这是一个update或者delete操作，jpql不支持insert操作
//    @Query("update Account set name=:name, money=:money,version=:version+1 where id=:id and version=:version")
//    int updateAccountByVersion(@Param("id") int id, @Param("name") String name, @Param("money") double money, @Param("version") int version);
}
