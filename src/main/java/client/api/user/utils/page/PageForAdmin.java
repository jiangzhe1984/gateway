package client.api.user.utils.page;

import client.api.user.domain.User;
import lombok.Data;

import java.util.List;

/**
 * Created by gaoqichao on 15-12-1.
 */
@Data
public class PageForAdmin {
	/**
	 * 总数
	 */
	long totalCount;
	/**
	 * 数据列表
	 */
	List<User> list;
}
