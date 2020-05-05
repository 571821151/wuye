package tsu.pro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tsu.pro.bean.House;
import tsu.pro.bean.LivingArea;

import java.util.ArrayList;

@Mapper
public interface HouseMapper {
    @Select("select * from house")
	ArrayList<House> queryAll();

    @Select("select * from house where id =#{ id}")
    House getByid(int id);


    @Select("SELECT house.areaId, house.id, house.houseName, house.houseDes FROM house, owners, users WHERE owners.userId = users.ID  AND owners.ownerHouseId = house.id  AND users.ID =#{userId}")
    House getByUserId(int userId);
}
