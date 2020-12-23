package com.j.vlog.mapper;

import com.j.vlog.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

/**
 * @ClassName UserMapper
 * @Description 用户Mapper
 * @Author orange
 * @Date 5.12.20
 **/

public interface UserMapper {
    /**
     * 新增用户，并返回自增主键
     *
     * @param user 入参user对象
     * @throws SQLException 异常
     */
    @Insert("INSERT INTO t_user (wx_openid,phone,nickname,avatar,create_time) VALUES (#{wxOpenId},#{phone},#{nickname},#{avatar},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user) throws SQLException;

    /**
     * 根据手机号查询用户信息
     *
     * @param phone 手机号
     * @return User 用户对象
     * @throws SQLException 异常
     */
    @Select({"<script>",
            "SELECT * FROM t_user",
            "WHERE 1=1",
            "<when test='phone!=null'>",
            "AND phone = #{phone}",
            "</when>",
            "</script>"})
    User findUserByPhone(@Param("phone") String phone) throws SQLException;


    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @throws SQLException 异常
     */
    @Update("UPDATE t_user SET password=#{password}, nickname=#{nickname}, avatar=#{avatar}, gender=#{gender}, birthday=#{birthday}, address=#{address} " +
            "WHERE phone=#{phone}")
    void updateUser(User user) throws SQLException;


    /**
     * 根据微信openId查询用户信息
     *
     * @param wxOpenId 微信OpenId
     * @return User 用户对象
     * @throws SQLException 异常
     */
    @Select({"<script>",
            "SELECT * FROM t_user",
            "WHERE 1=1",
            "<when test='wxOpenId!=null'>",
            "AND wx_openid = #{wxOpenId}",
            "</when>",
            "</script>"})
    User fineUserByOpenId(@Param("wxOpenId") String wxOpenId) throws SQLException;

    @Select("SELECT id, phone, nickname, avatar, gender, address, birthday, banner, signature FROM t_user WHERE id=#{id}")
    User getUser(@Param("id") int id);
}

