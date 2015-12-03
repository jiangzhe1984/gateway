package sjes.gateway.admin.main.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jiangzhe on 15-11-27.
 */
@Data
public class PayAmount implements Serializable {
    private String key;
    private String value;
}
