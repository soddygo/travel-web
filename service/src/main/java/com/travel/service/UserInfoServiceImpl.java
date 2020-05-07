package com.travel.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.common.util.PageUtil;
import com.travel.dao.mapper.UserInfoMapper;
import com.travel.pojo.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 单号生成器
 * </p>
 *
 * @author dmall
 * @since 2019-09-19
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> {

    /**
     * 可以用mybatis 写自定义sql
     */
    @Resource
    private UserInfoMapper userInfoMapper;


    /**
     * search user info
     *
     * @param userInfo user
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @return page
     */
    public Page<UserInfo> search(UserInfo userInfo, Integer pageNo, Integer pageSize) {

        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new QueryWrapper<UserInfo>()
                .lambda()
                .like(StringUtils.isNotBlank(userInfo.getUserNick()), UserInfo::getUserNick, userInfo.getUserNick())
                .like(StringUtils.isNotBlank(userInfo.getUserName()), UserInfo::getUserName, userInfo.getUserName())
                .like(StringUtils.isNotBlank(userInfo.getPhoneNo()), UserInfo::getPhoneNo, userInfo.getPhoneNo())
                .like(StringUtils.isNotBlank(userInfo.getEmail()), UserInfo::getEmail, userInfo.getEmail())
                .eq(UserInfo::getYn, 1);

        //分页
        Page<UserInfo> page = PageUtil.build(pageNo, pageSize);

        return this.page(page, lambdaQueryWrapper);

    }


    /**
     * query user info by id
     *
     * @param id id
     * @return user
     */
    public UserInfo queryOneById(Long id) {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new QueryWrapper<UserInfo>()
                .lambda()
                .like(UserInfo::getId, id)
                .eq(UserInfo::getYn, 1);

        return this.getOne(lambdaQueryWrapper);
    }

    /**
     * save user info
     *
     * @param userInfo user
     */
    public void saveUserInfo(UserInfo userInfo) {

        userInfo.setCreated(null);
        userInfo.setModified(null);
        userInfo.setId(null);

        this.save(userInfo);
    }

}
