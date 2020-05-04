package tsu.pro.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tsu.pro.bean.LivingArea;
import tsu.pro.bean.Role;
import tsu.pro.bean.User_Role;

import java.util.ArrayList;

@Mapper
public interface LivingAreaMapper {
    @Select("select * from livingarea")
    ArrayList<LivingArea> queryAll();

    @Select("select * from livingarea where id=#{id}")
    LivingArea selectById(int id);
}
