package com.yequ.common.domain.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.user.service.UserService;
import com.yequ.common.domain.user.entity.UserEntity;
import com.yequ.common.domain.user.mapper.UserMapper;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.LoginUserVO;
import com.yequ.common.interfaces.outbound.login.QueryPageVO;
import com.yequ.common.interfaces.outbound.login.RegisteredUserVO;
import com.yequ.common.interfaces.outbound.login.UserVO;
import com.yequ.common.utils.CommonConstant;
import com.yequ.common.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginUserVO queryUserByName(String name) {
        UserEntity userEntity = baseMapper.findUserByName(name);
        UserVO userVO = new UserVO();
        userVO.setUsername(userEntity.getUsername());
        userVO.setPassword(userEntity.getPassword());
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setUserVO(userVO);
        List<String> list = new ArrayList<>();
        list.add("/admin/logout");
        loginUserVO.setPermissions(list);
        return loginUserVO;
    }

    @Override
    public ResultDto<RegisteredUserVO> modfiyUser(RegisteredUserVO vo) {
        ResultDto<RegisteredUserVO> resultDto = new ResultDto<RegisteredUserVO>();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(vo,userEntity);
        // 判断是否修改用户密码，为空就是不修改，不为空就是修改，对明文加密
        if(!StringUtil.isEmpty(userEntity.getPassword())){
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        }
        //用户ID不为空，且传递过来的名文明密码为空，就需要保持原有密码有效
        if(!StringUtil.isEmpty(vo.getId())&&vo.getId()!=0&&StringUtil.isEmpty(userEntity.getPassword())){
            UserEntity userEntityTemp = super.getById(vo.getId());
            userEntity.setPassword(userEntityTemp.getPassword());
        }
        if(!super.saveOrUpdate(userEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改用户失败");
            resultDto.setObj(vo);
        }else {
            BeanUtils.copyProperties(userEntity,vo);
            resultDto.setObj(vo);
        }
        return resultDto;
    }

    @Override
    public ResultPageDto<RegisteredUserVO> queryUser(QueryPageVO<RegisteredUserVO> queryPageVO) {
        RegisteredUserVO registeredUserVO = queryPageVO.getParamObj();
        Page<UserEntity> userEntityPage = new Page<>();
        BeanUtils.copyProperties(queryPageVO,userEntityPage);
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(!StringUtil.isEmpty(registeredUserVO.getUsername())){
            lambdaQueryWrapper.like(UserEntity::getUsername,registeredUserVO.getUsername());
        }
        if(!StringUtil.isEmpty(registeredUserVO.getWorkId())){
            lambdaQueryWrapper.like(UserEntity::getWorkId,registeredUserVO.getWorkId());
        }
        if(!StringUtil.isEmpty(registeredUserVO.getEmail())){
            lambdaQueryWrapper.like(UserEntity::getEmail,registeredUserVO.getEmail());
        }
        userEntityPage = super.page(userEntityPage, lambdaQueryWrapper);
        ResultPageDto<RegisteredUserVO> resultPageDto = new ResultPageDto<>();
        BeanUtils.copyProperties(userEntityPage,resultPageDto);
        return resultPageDto;
    }

}
