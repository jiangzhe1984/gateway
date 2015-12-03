package sjes.gateway.admin.module.base.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import sjes.gateway.admin.module.common.domain.BaseObj;

import javax.persistence.*;

/**
 * Created by jiangzhe on 15-11-17.
 * 角色与权限资源
 */
@Data
@Entity
@Table(name="t_roleauthority")
@DynamicInsert
public class RoleAuthority extends BaseObj {

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "role")
    private Role role;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "authority")
    private Authority authority;

}
