package tsu.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsu.pro.bean.Charge;
import tsu.pro.bean.House;
import tsu.pro.bean.Info;
import tsu.pro.bean.Stuts;
import tsu.pro.mapper.ChargeMapper;
import tsu.pro.mapper.HouseMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {
@Autowired
  private HouseMapper houseMapper;

	public Info<House> findList() {
		Info<House> info=new Info<House>();
		List<House> list=new ArrayList<House>();
		list=houseMapper.queryAll();
		if(!list.isEmpty()){
			info.setInfos(list);
			info.setStatus("ok");
			info.setMesage("成功");
			return info;
		}
		else{
			info.setStatus("error");
			info.setMesage("失败");
			return info;
		}
	}
	public Info<House> selectById(int id) {
		Info<House> info=new Info<House>();
		House Charge=new House();
		Charge=houseMapper.getByid(id);
		if(Charge!=null){
			info.setT(Charge);
			info.setMesage("查询成功");
			info.setStatus("ok");
			return info;
		}
		else{
			info.setMesage("查询失败");
			info.setStatus("error");
			return info;
		}


	}



}
