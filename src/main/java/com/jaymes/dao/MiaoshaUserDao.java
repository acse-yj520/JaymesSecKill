package com.jaymes.dao;

import com.jaymes.entity.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MiaoshaUserDao {

  @Select("select * from user where id = #{id}")
  MiaoshaUser getById(@Param("id") long id);

  @Update("update user set password = #{password} where id = #{id}")
  void update(MiaoshaUser toBeUpdate);
}
