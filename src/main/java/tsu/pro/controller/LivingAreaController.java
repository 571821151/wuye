package tsu.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tsu.pro.bean.House;
import tsu.pro.bean.Info;
import tsu.pro.bean.LivingArea;
import tsu.pro.service.HouseService;
import tsu.pro.service.LivingAreaService;


@RestController
@RequestMapping(value = "/area")
public class LivingAreaController {
	@Autowired
	private LivingAreaService livingAreaService;



	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Info<LivingArea> getHouseList() {
		return livingAreaService.findList();
	}


	/**
	 * 查询业主
	 * ID
	 */
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public Info<LivingArea> findbyID(@PathVariable("id")  int id) {
		return	livingAreaService.selectById(id);

	}


}
