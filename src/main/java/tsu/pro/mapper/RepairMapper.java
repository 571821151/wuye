package tsu.pro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import tsu.pro.bean.Repair;

@Mapper
public interface RepairMapper {
    @Select("select * from repairS order by update_Tm desc")
    List<Repair> selectlist();

    @Select("select * from repairS where ownerID=#{ownerID} ")
    List<Repair> selectlike(int ownerID);

    @Select("select * from repairS where repairStates=#{repairStates} and  ownerID=#{ownerID} or repairStates=#{repairStates} and  position=1")
    List<Repair> selectByStatus(@Param("repairStates")int repairStates,@Param("ownerID") int ownerID);

    @Delete("delete from repairS where repariID=#{repariID}")
    int delete(int repairID);

    @Update("update repairS set repairMoney=#{repairMoney},repairInfo=#{repairInfo},repairDes=#{repairDes},ownerID=#{ownerID},expectTime=#{expectTime},repairDel=#{repairDel},repairIdentify=#{repairIdentify},update_Tm=#{update_Tm},repairStates=#{repairStates},fixerId=#{fixerId},fixDes=#{fixDes},repairImg1=#{repairImg1},repairImg2=#{repairImg2},repairImg3=#{repairImg3},repairImg4=#{repairImg4},repairImg5=#{repairImg5},fixImg1=#{fixImg1},fixImg2=#{fixImg2},fixImg3=#{fixImg3},fixImg4=#{fixImg4},fixImg5=#{fixImg5} where repariID=#{repariID}")
    int update(Repair repair);

    @Select("select * from repairS where repariID=#{repariID}")
    Repair selectId(int repairID);

    @Insert("insert into repairS(repairMoney,repairInfo,ownerID,repairDes,repairDel,repairImg1,repairImg2,repairImg3,repairImg4,repairImg5,repairStates,fixImg1,fixImg2,fixImg3,fixImg4,fixImg5,position,fixDes,expectTime,fixerId,repairIdentify,update_Tm) values(#{repairMoney},#{repairInfo},#{ownerID},#{repairDes},#{repairDel},#{repairImg1},#{repairImg2},#{repairImg3},#{repairImg4},#{repairImg5},#{repairStates},#{fixImg1},#{fixImg2},#{fixImg3},#{fixImg4},#{fixImg5},#{position},#{fixDes},#{expectTime},#{fixerId},#{repairIdentify},#{update_Tm})")
    int insert(Repair repair);
}
