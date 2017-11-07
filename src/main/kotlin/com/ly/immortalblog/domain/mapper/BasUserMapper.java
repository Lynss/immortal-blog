package com.ly.immortalblog.domain.mapper;

import com.ly.immortalblog.domain.model.BasUser;
import com.ly.immortalblog.domain.model.BasUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BasUserMapper {
    @SelectProvider(type=BasUserSqlProvider.class, method="countByExample")
    long countByExample(BasUserExample example);

    @DeleteProvider(type=BasUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(BasUserExample example);

    @Delete({
        "delete from bas_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into bas_user (id, username, ",
        "password, sex, role)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{sex,jdbcType=TINYINT}, #{role,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(BasUser record);

    @InsertProvider(type=BasUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insertSelective(BasUser record);

    @SelectProvider(type=BasUserSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="sex", javaType=Byte.class, jdbcType=JdbcType.TINYINT),
        @Arg(column="role", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<BasUser> selectByExample(BasUserExample example);

    @Select({
        "select",
        "id, username, password, sex, role",
        "from bas_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="sex", javaType=Byte.class, jdbcType=JdbcType.TINYINT),
        @Arg(column="role", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    BasUser selectByPrimaryKey(Integer id);


    @Select({
        "select",
        "id, username, password, sex, role",
        "from bas_user",
        "where id = #{username,jdbcType=VARCHAR}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="sex", javaType=Byte.class, jdbcType=JdbcType.TINYINT),
        @Arg(column="role", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    BasUser selectByUsername(String username);

    @UpdateProvider(type=BasUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BasUser record, @Param("example") BasUserExample example);

    @UpdateProvider(type=BasUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BasUser record, @Param("example") BasUserExample example);

    @UpdateProvider(type=BasUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BasUser record);

    @Update({
        "update bas_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=TINYINT},",
          "role = #{role,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BasUser record);
}