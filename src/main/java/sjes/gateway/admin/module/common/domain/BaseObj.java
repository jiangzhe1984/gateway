package sjes.gateway.admin.module.common.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by jiangzhe on 15-11-16.
 */

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseObj implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
