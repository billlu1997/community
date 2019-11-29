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

    @Insert("INSERT INTO USER (NAME, ACCOUNT_ID, TOKEN, GMT_CREATE, GMT_MODIFIED, BIO, AVATAR_URL) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio},#{avatarUrl});")
    void insert(User user);


    @Select("SELECT * FROM USER WHERE TOKEN=#{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM USER WHERE ACCOUNT_ID=#{accountId}")
    User findByAccount(@Param("accountId") String accountId);

    @Select("SELECT * FROM USER WHERE ID=#{id}")
    User findById(@Param("id") Integer id);
}
