package client.api.item.domain;

import client.api.item.serializer.CustomDateDeSerializer;
import client.api.item.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by mac on 15/9/15.
 */
@Data
public class Brand implements Serializable {

    private Long id; // 主键

    private String name; // 品牌名称

    private String logo; // logo

    private String url; // URL

    private Integer orders; // 排序

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间
}
