package sjes.gateway.admin.module.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sjes.gateway.admin.module.base.domain.AclUser;
import sjes.gateway.admin.module.base.domain.Authority;
import sjes.gateway.admin.module.base.domain.RoleAuthority;
import sjes.gateway.admin.module.base.domain.UserAuthority;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-18.
 */
public interface UserAuthorityRepository extends JpaRepository<UserAuthority,Long> {

    /**
     * 根据用户查询出关联记录
     */
    @Query("from UserAuthority where securityuser = :aclUser")
    List<UserAuthority> findListByUser(@Param("aclUser") AclUser aclUser);

    /**
     * 根据用户和权限删除关联记录
     */
    @Modifying
    @Transactional
    @Query("delete from UserAuthority as ua where  ua.authority = :authority and ua.securityuser = :securityuser")
    void removeByRoleAndAuthAndUser(@Param("authority") Authority authority, @Param("securityuser") AclUser aclUser);

    /**
     * 根据用户、权限、角色与权限关联，来删除用户与权限关联记录
     */
    @Modifying
    @Transactional
    @Query("delete from UserAuthority as ua where ua.roleAuthority = :roleAuthority and ua.authority = :authority and ua.securityuser = :securityuser")
    void removeByRAU(@Param("roleAuthority") RoleAuthority roleAuthority, @Param("authority") Authority authority, @Param("securityuser") AclUser aclUser);

    /**
     * 根据权限来删除用户与权限关联记录
     */
    @Modifying
    @Transactional
    @Query("delete from UserAuthority as ua where ua.authority = :authority")
    void removeByAuth(@Param("authority") Authority authority);

}
