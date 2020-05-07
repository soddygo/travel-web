package com.travel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息,用户登录用等
 *
 * @author soddy
 */
@Getter
@Setter
@TableName("user_info")
public class UserInfo implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String userNick;
    /**
     * 姓名
     */
    private String userName;
    /**
     * phoneNo
     */
    private String phoneNo;
    /**
     * email
     */
    private String email;
    /**
     * password
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime created;


    /**
     * 创建者ID
     */
    private Long creatorId;

    /**
     * 创建人
     */
    @Size(max = 50, message = "创建人[creatorName],最大长度50,超过请检查值是否符合规范")
    private String creatorName;

    /**
     * 更新时间
     */
    private LocalDateTime modified;

    /**
     * 修改者ID
     */
    private Long modifierId;


    /**
     * 修改人
     */
    @Size(max = 50, message = "修改人[modifierName],最大长度50,超过请检查值是否符合规范")
    private String modifierName;


    /**
     * 是否有效,1:有效;-1:无效
     */
    private Boolean yn;
}
