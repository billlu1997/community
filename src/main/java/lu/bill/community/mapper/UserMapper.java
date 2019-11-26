package lu.bill.community.mapper;

import lu.bill.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO COMMUNITY.PUBLIC.USER (NAME, ACCOUNT_ID, TOKEN, GMT_CREATE, GMT_MODIFIED) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified});")
    void insert(User user);


    @Select("SELECT * FROM COMMUNITY.PUBLIC.USER WHERE TOKEN=#{token}")
    User findByToken(@Param("token") String token);
}
