package web23.web18.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MapperProfile {

    void add(String avator, int id);

}
