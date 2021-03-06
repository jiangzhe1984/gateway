package sjes.gateway.admin.module.base.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import sjes.gateway.admin.module.common.domain.BaseObj;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jiangzhe on 15-11-16.
 * 部门
 */
@Data
@Entity
@Table(name = "t_org")
@DynamicInsert
public class AclOrg  extends BaseObj {

    /**
     * 部门名称
     */
    private String orgName;

    /**
     * 部门编号
     */
    private String orgNum;

    /**
     * 部门管理员
     */
    @JoinColumn(name="manager")
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private AclUser manager;

    /**
     * 父部门
     */
    @JoinColumn(name="parentorg")
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private AclOrg parentOrg;

    /**
     * 子部门
     */
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.REMOVE }, fetch = FetchType.EAGER,mappedBy = "parentOrg")
    private List<AclOrg> children;

    /**
     * 描述
     */
    private String description;
}
