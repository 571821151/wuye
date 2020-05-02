package tsu.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tsu.pro.bean.*;
import tsu.pro.mapper.HouseMapper;
import tsu.pro.mapper.UserMapper;
import tsu.pro.service.HouseService;
import tsu.pro.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/house")
public class HouseController {
	@Autowired
	private HouseService houseService;



	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Info<House> getHouseList() {
		return houseService.findList();
	}

	/**
	 * 查询业主
	 * ID
	 */
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public Info<House> findbyID(@PathVariable("id")  int id) {
		return	houseService.selectById(id);

	}



}
