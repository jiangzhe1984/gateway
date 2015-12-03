package sjes.gateway.admin.module.base.domain;

import lombok.Data;
import sjes.gateway.admin.module.common.domain.BaseObj;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jiangzhe on 15-11-18.
 * 菜单
 */
@Data
@Entity
@Table(name = "t_menu")
public class Menu extends BaseObj {
    /**
     * 菜单名称
     */
    private String text;

    /**
     * 是否打开，在菜单树中使用
     */
    private boolean expanded;

    /**
     * 是否是父菜单
     */
    private boolean isParent;

    /**
     * 是否是子菜单
     */
    private boolean leaf;

    /**
     * 功能模块地址
     */
    private String url;

    /**
     * 父菜单
     */
    @JoinColumn(name="parentMenu")
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Menu parentMenu;

    /**
     * 子菜单
     */
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.REMOVE }, fetch = FetchType.EAGER,mappedBy = "parentMenu")
    private List<Menu> children;


    private Integer sort;

}
