package com.ruoyi.merchant.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.merchant.mapper.MerAccountMapper;
import com.ruoyi.merchant.mapper.MerInfoMapper;
import com.ruoyi.merchant.mapper.MerSecretKeyMapper;
import com.ruoyi.merchant.domain.MerAccount;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.domain.MerSecretKey;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;

/**
 * 商户基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
@Service
public class MerInfoServiceImpl implements IMerInfoService 
{
	@Autowired
    private ISysUserService userService;
    @Autowired
    private MerInfoMapper merInfoMapper;
    @Autowired
    private MerAccountMapper merAccountMapper;
    @Autowired
    private MerSecretKeyMapper merSecretKeyMapper;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private SysRoleMapper roleMappler;
    @Autowired
    private SysUserRoleMapper userRoleMappler;
    /**
     * 查询商户基础信息
     * 
     * @param id 商户基础信息ID
     * @return 商户基础信息
     */
    @Override
    public MerInfo selectMerInfoById(Long id)
    {
        return merInfoMapper.selectMerInfoById(id);
    }

    /**
     * 查询商户基础信息列表
     * 
     * @param merInfo 商户基础信息
     * @return 商户基础信息
     */
    @Override
    public List<MerInfo> selectMerInfoList(MerInfo merInfo)
    {
        return merInfoMapper.selectMerInfoList(merInfo);
    }

    /**
     * 新增商户基础信息
     * 
     * @param merInfo 商户基础信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertMerInfo(MerInfo merInfo)
    {
    	SysUser curUser = ShiroUtils.getSysUser();
    	String md5Key = merInfo.getMd5key();
        merInfo.setCreateTime(DateUtils.getNowDate());
        merInfo.setUpdateTime(DateUtils.getNowDate());
        int i = merInfoMapper.insertMerInfo(merInfo);
        merInfo=merInfoMapper.selectMerInfoByMerNo(merInfo.getMerNo());
        MerAccount merAccount=new MerAccount();
        merAccount.setMerId(merInfo.getId());
        merAccount.setCreditAmt(0l);
        merAccount.setCreditBalanceAmt(0l);
        merAccount.setCreditFixAmt(0l);
        merAccount.setCreditUseAmt(0l);
        merAccount.setVersion(0l);
        merAccountMapper.insertMerAccount(merAccount);
        MerSecretKey merSecretKey=new MerSecretKey();
        merSecretKey.setMerNo(merInfo.getMerNo());
        merSecretKey.setMerId(merInfo.getId());
        merSecretKey.setMd5Key(md5Key);
        merSecretKey.setCreateTime(new Date());
        merSecretKey.setSignType(1l);
        merSecretKey.setUpdateTime(new Date());
        merSecretKeyMapper.insertMerSecretKey(merSecretKey);
        SysUser user=new SysUser();
        user.setCreateTime(new Date());
        user.setDeptId(curUser.getDeptId());
        user.setUserName(merInfo.getMerName());
        user.setPhonenumber(merInfo.getTel());
        user.setLoginName(merInfo.getTel());
        user.setSalt(ShiroUtils.randomSalt());
        user.setType(1l);
        user.setUserTypeId(merInfo.getId());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), "123456", user.getSalt()));
        userService.insertUser(user);
        user = userService.selectUserByLoginName(merInfo.getTel());
        Long[] roleIds=new Long[1];
        roleIds[0]=100l;
        userService.insertUserAuth(user.getUserId(), roleIds);
        return i;
    }

    /**
     * 修改商户基础信息
     * 
     * @param merInfo 商户基础信息
     * @return 结果
     */
    @Override
    public int updateMerInfo(MerInfo merInfo)
    {
    	String md5key = merInfo.getMd5key();
        merInfo.setUpdateTime(DateUtils.getNowDate());
        MerSecretKey merSecretKey=new MerSecretKey();
        merSecretKey.setMerNo(merInfo.getMerNo());
        merSecretKey.setMerId(merInfo.getId());
        merSecretKey.setMd5Key(md5key);
        merSecretKey.setSignType(1l);
        merSecretKey.setUpdateTime(new Date());
        merSecretKeyMapper.updateMerSecretKey(merSecretKey);
        return merInfoMapper.updateMerInfo(merInfo);
        
    }

    /**
     * 删除商户基础信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerInfoByIds(String ids)
    {
        return merInfoMapper.deleteMerInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户基础信息信息
     * 
     * @param id 商户基础信息ID
     * @return 结果
     */
    @Override
    public int deleteMerInfoById(Long id)
    {
        return merInfoMapper.deleteMerInfoById(id);
    }
}
