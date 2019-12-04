package lu.bill.community.mapper;

import lu.bill.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUser(Integer userId, Integer offset, Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(Integer userId);

    @Select("select * from question where id=#{questionId}")
    Question getById(Long questionId);

    @Update("update question set title=#{title}, description=#{description}, gmt_modified=#{gmtModified}, tag=#{tag} where id=#{id}")
    void update(Question question);
}
