package com.yequ.common.domain.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.yequ.common.domain.service.UserBusService;
import com.yequ.common.domain.service.role.PermissionService;
import com.yequ.common.domain.service.role.RolePermissionRelationService;
import com.yequ.common.domain.service.role.RoleService;
import com.yequ.common.domain.service.role.RoleUserRelationService;
import com.yequ.common.domain.service.role.entity.PermissionEntity;
import com.yequ.common.domain.service.role.entity.RoleEntity;
import com.yequ.common.domain.service.role.entity.RolePermissionRelationEntity;
import com.yequ.common.domain.service.role.entity.RoleUserRelationEntity;
import com.yequ.common.domain.service.user.UserService;
import com.yequ.common.domain.service.user.entity.UserEntity;
import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultPageDto;
import com.yequ.common.interfaces.outbond.login.*;
import com.yequ.common.utils.CommonConstant;
import com.yequ.common.utils.JWTUtil;
import com.yequ.common.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Service
public class UserBusServiceImpl implements UserBusService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserRelationService roleUserRelationService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @Override
    public LoginResultDto login(UserVO userVO) {
        //登录信息存入UsernamePasswordAuthenticationToken 进入AuthenticationManager进行校验
        LoginResultDto loginResultDto = new LoginResultDto();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userVO.getUsername(),userVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("认证失败");
        }
        LoginUserVO loginUserVO = (LoginUserVO) authenticate.getPrincipal();
        String username = loginUserVO.getUsername();
        //loginUserVO存入缓存
        stringRedisTemplate.opsForValue().set(username, JSONObject.toJSONString(loginUserVO));
        //登录账号生成token 将token返回请求方 作为下次访问的凭证
        String token = JWTUtil.createJWT(username);
        loginResultDto.setToken(token);
        return loginResultDto;
    }

    @Override
    public ResultDto logout(UserVO userVO) {
        //登录信息存入UsernamePasswordAuthenticationToken 进入AuthenticationManager进行校验
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
        String username = loginUserVO.getUsername();
        stringRedisTemplate.delete(username);
        ResultDto dto = new ResultDto();
        dto.setMessage("退出成功");
        return dto;
    }

    @Override
    public ResultDto<RegisteredUserVO> modfiyUser(RegisteredUserVO vo) {
        ResultDto<RegisteredUserVO> resultDto = new ResultDto<RegisteredUserVO>();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(vo,userEntity);
        if(!userService.saveOrUpdate(userEntity)){
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
    public ResultDto<RoleVO> modfiyRole(RoleVO roleVO) {
        ResultDto<RoleVO> resultDto = new ResultDto<RoleVO>();
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO,roleEntity);
        if(!roleService.saveOrUpdate(roleEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改角色失败");
            resultDto.setObj(roleVO);
        }else {
            BeanUtils.copyProperties(roleEntity,roleVO);
            resultDto.setObj(roleVO);
        }
        return resultDto;
    }

    @Override
    public ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO) {
        ResultDto<RoleUserVO> resultDto = new ResultDto<RoleUserVO>();
        RoleUserRelationEntity roleUserRelationEntity = new RoleUserRelationEntity();
        BeanUtils.copyProperties(roleUserVO,roleUserRelationEntity);
        if(!roleUserRelationService.saveOrUpdate(roleUserRelationEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改角色失败");
            resultDto.setObj(roleUserVO);
        }else {
            BeanUtils.copyProperties(roleUserRelationEntity,roleUserVO);
            resultDto.setObj(roleUserVO);
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
        userEntityPage = userService.page(userEntityPage, lambdaQueryWrapper);
        ResultPageDto<RegisteredUserVO> resultPageDto = new ResultPageDto<>();
        BeanUtils.copyProperties(userEntityPage,resultPageDto);
        return resultPageDto;
    }

    @Override
    public ResultDto<RolePermissionVO> modfiyRolePermission(RolePermissionVO rolePermissionVO) {
        //todo 1.判断权限点ID是否存在，不存在新增
        //todo 2.将权限点ID与校色ID绑定入库
        ResultDto<RolePermissionVO> resultDto =  new ResultDto<RolePermissionVO>();
        RolePermissionRelationEntity rolePermissionRelationEntity = new RolePermissionRelationEntity();
        BeanUtils.copyProperties(rolePermissionVO,rolePermissionRelationEntity);
        if(rolePermissionVO.getPermissionId()==null){
            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setUrl(rolePermissionVO.getUrl());
            permissionEntity.setDescription(rolePermissionVO.getDescription());
            permissionService.save(permissionEntity);
            rolePermissionRelationEntity.setPermissionId(permissionEntity.getId());
        }
        if(!rolePermissionRelationService.saveOrUpdate(rolePermissionRelationEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改用户失败");
            resultDto.setObj(rolePermissionVO);
        }else {
            BeanUtils.copyProperties(rolePermissionRelationEntity, rolePermissionVO);
            resultDto.setObj(rolePermissionVO);
        }
        return resultDto;
    }

    @Override
    public ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO) {
        return null;
    }

    @Override
    public ResultPageDto<PermissionVO> queryPermission(QueryPageVO<PermissionVO> queryPageVO) {
        PermissionVO  permissionVO = queryPageVO.getParamObj();
        Page<PermissionEntity> permissionEntityPage= new Page<>();
        BeanUtils.copyProperties(queryPageVO,permissionEntityPage);
        LambdaQueryWrapper<PermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(!StringUtil.isEmpty(permissionVO.getDescription())) {
            lambdaQueryWrapper.like(PermissionEntity::getDescription,permissionVO.getDescription());
        }
        if(!StringUtil.isEmpty(permissionVO.getUrl())) {
            lambdaQueryWrapper.like(PermissionEntity::getUrl,permissionVO.getUrl());
        }
        permissionEntityPage =permissionService.page(permissionEntityPage,lambdaQueryWrapper);
        ResultPageDto<PermissionVO> resultPageDto = new ResultPageDto<>();
        BeanUtils.copyProperties(permissionEntityPage,resultPageDto);
        return resultPageDto;
    }

    @Override
    public ResultPageDto<RoleVO> queryRole(QueryPageVO<RoleVO> queryPageVO) {
        RoleVO  roleVO = queryPageVO.getParamObj();
        Page<RoleEntity> roleEntityPage= new Page<>();
        BeanUtils.copyProperties(queryPageVO,roleEntityPage);
        LambdaQueryWrapper<RoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(!StringUtil.isEmpty(roleVO.getName())) {
            lambdaQueryWrapper.like(RoleEntity::getName,roleVO.getName());
        }
        if(!StringUtil.isEmpty(roleVO.getCode())) {
            lambdaQueryWrapper.like(RoleEntity::getCode,roleVO.getCode());
        }
        roleEntityPage =roleService.page(roleEntityPage,lambdaQueryWrapper);
        ResultPageDto<RoleVO> resultPageDto = new ResultPageDto<>();
        BeanUtils.copyProperties(roleEntityPage,resultPageDto);
        return resultPageDto;
    }

}
