package tsu.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tsu.pro.bean.*;
import tsu.pro.bean.Repair;
import tsu.pro.service.HouseService;
import tsu.pro.service.OwnerService;
import tsu.pro.service.repairService;
import tsu.pro.utils.UserUtils;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/repair")
public class RepairController {
    @Autowired
    private repairService repairservice;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private HouseService houseService;

    /**
     * 插入保修信息
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Stuts createUser(@ModelAttribute Repair Repair, HttpSession session) {
        User user = UserUtils.GetUser(session);
        Stuts stuts = new Stuts();
        if (user != null) {
            Owner owner = ownerService.selectByUserId(user.getId()).getT();
            if (owner != null) {
                House house = houseService.selectById(owner.getownerHouseId()).getT();
                return repairservice.insertRepair(Repair, user.getId(),house);
            } else
                stuts.setMessage("该用户非业主");
        } else
            stuts.setMessage("用户登陆异常");

        stuts.setStuts("error");

        return stuts;


    }

    /**
     * 查询保修
     * ID
     */
    @RequestMapping(value = "/repId/{id}", method = RequestMethod.GET)
    public Info<Repair> findbyID(@PathVariable("id") int RepairID) {
        return repairservice.selectById(RepairID);

    }

    /**
     * 查保修列表
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Info<Repair> findList() {
        return repairservice.findList();

    }

    /**
     * 模糊查询保修信息
     */
    @RequestMapping(value = "/{ownerID}", method = RequestMethod.GET)
    public Info<Repair> findlike(@PathVariable("ownerID") int ownerID) {
        return repairservice.findLike(ownerID);
    }

    /**
     * 模糊查询保修信息
     */
    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public Info<Repair> findByStatus(@PathVariable("status") int status, HttpSession session) {
        User user = UserUtils.GetUser(session);
        return repairservice.findByStatus(status, user.getId());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Stuts deleteByID(@PathVariable("id") int RepairID) {
        return repairservice.deleteByID(RepairID);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Stuts update(@ModelAttribute Repair Repair) {
        return repairservice.update(Repair);
    }

    @RequestMapping(value = "/fix/{RepairID}/{fixerID}", method = RequestMethod.GET)
    public Stuts fix(@PathVariable("RepairID") int RepairID, @PathVariable("fixerID") int fixerID) {
        Info<Repair> repairInfo = repairservice.selectById(RepairID);
        Repair repair = repairInfo.getT();
        repair.setFixerId(fixerID);
        //设置维修状态
        repair.setRepairStates(1);
        Stuts s = repairservice.update(repair);
        s.setMessage("维修单状态设置成功");
        return s;
    }

}