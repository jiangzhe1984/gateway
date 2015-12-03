package client.api.user.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by gaoqichao on 15-12-1.
 */
@Data
public class User implements Serializable {

	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 　邮箱
	 */
	private String email;

	/**
	 * 　密码
	 */
	private String password;

	/**
	 * 密码强度
	 */
	private int pwdStrength = 0;

	/**
	 * 　手机号
	 */
	private String mobile;

	/**
	 * 性别
	 */
	private int sex;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 会员卡号
	 */
	private String cardNum;

	/**
	 * 感兴趣的分类
	 */
	private String categories;

	/**
	 * 会员卡有效期
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateLine;

	/**
	 * 虚拟会员卡号
	 */
	private String virtualCardNum;

	/**
	 * 头像路径
	 */
	private String portraitPath;

	/**
	 * 用户是否激活
	 */
	private boolean enable = true;

	/**
	 * 生成时间
	 */
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime createdDate = LocalDateTime.now();

	/**
	 * 更新时间
	 */
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime updatedDate;
}
