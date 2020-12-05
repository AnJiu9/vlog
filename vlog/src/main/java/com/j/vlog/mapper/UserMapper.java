package com.j.vlog.mapper;

import com.j.vlog.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

/**
 * @ClassName UserMapper
 * @Description 用户Mapper
 * @Author orange
 * @Date 5.12.20
 **/

public interface UserMapper {
    /**
     *新增用户，并返回自增主键
     *
     * @param user 入参user对象
     * @throws SQLException 异常
     */
    @Insert("INSERT INTO t_user (phone,password,nickname,avatar,gender,birthday,address,create_time)" +
            "VALUES (#{phone},#{password},#{nickname},#{avatar},#{gender},#{birthday},#{address},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user) throws SQLException;

    /**
     * 根据手机号查询用户信息
     *
     * @param  phone 手机号
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
}