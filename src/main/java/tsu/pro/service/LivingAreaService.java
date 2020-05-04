package tsu.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsu.pro.bean.House;
import tsu.pro.bean.Info;
import tsu.pro.bean.LivingArea;
import tsu.pro.mapper.HouseMapper;
import tsu.pro.mapper.LivingAreaMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivingAreaService {
	@Autowired
	private LivingAreaMapper livingAreaMapper;

	public Info<LivingArea> findList() {
		Info<LivingArea> info = new Info<LivingArea>();
		List<LivingArea> list = new ArrayList<LivingArea>();
		list = livingAreaMapper.queryAll();
		if (!list.isEmpty()) {
			info.setInfos(list);
			info.setStatus("ok");
			info.setMesage("成功");
			return info;
		} else {
			info.setStatus("error");
			info.setMesage("失败");
			return info;
		}
	}

	public Info<LivingArea> selectById(int id) {
		Info<LivingArea> info = new Info<LivingArea>();
		LivingArea Charge = new LivingArea();
		Charge = livingAreaMapper.selectById(id);
		if (Charge != null) {
			info.setT(Charge);
			info.setMesage("查询成功");
			info.setStatus("ok");
			return info;
		} else {
			info.setMesage("查询失败");
			info.setStatus("error");
			return info;
		}

	}
}
