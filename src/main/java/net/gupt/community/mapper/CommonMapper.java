package net.gupt.community.mapper;

import net.gupt.community.entity.Common;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Cui
 */
@Component
public interface CommonMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKeyWithBLOBs(Common record);

    int updateByPrimaryKey(Common record);

    /**
     * 插入帖子信息
     *
     * @param record 收录帖子数据
     * @return 执行结果
     */
    int insert(Common record);

    /**
     * 获取所有的通用帖子
     *
     * @param postType 传入类型
     * @param openId 微信的OpenID
     * @return 通用帖子列表
     */
    List<Common> findAllCommons(Integer postType, String openId);

    /**
     * 删除帖子和相关数据
     *
     * @param articleType 帖子类型
     * @param id 帖子Id
     * @return
     */
    int deleteArticleByIdAndType(Integer articleType, Integer id);
}