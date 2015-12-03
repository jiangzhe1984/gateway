package sjes.gateway.admin.module.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sjes.gateway.admin.module.base.domain.Menu;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-19.
 */
public interface MenuRepository extends JpaRepository<Menu,Long>, JpaSpecificationExecutor<Menu> {

    /**
     * 查询所有父菜单
     */
    @Query("from Menu where isParent = 1")
    List<Menu> findIsParent();

    @Transactional
    @Modifying
    @Query("delete from Menu where id = :id")
    void remove(@Param("id") Long id);
}
