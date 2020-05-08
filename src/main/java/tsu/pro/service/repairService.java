package tsu.pro.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsu.pro.bean.*;
import tsu.pro.bean.Repair;
import tsu.pro.mapper.RepairMapper;

@Service
public class repairService {
    @Autowired
    private RepairMapper RepairMapper;

    public Stuts insertRepair(Repair Repair, int userId, House house) {
        Stuts st = new Stuts();
        Repair.setOwnerID(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        Repair.setUpdate_Tm(df.format(new Date()));
        if (Repair.getPosition() == 0) {
            Repair.setRepairInfo(house.getHouseDes() + house.getHouseName());
        }
        int status = RepairMapper.insert(Repair);
        if (status == 1) {
            st.setStuts("ok");
            st.setMessage("插入成功");
            return st;

        } else {
            st.setStuts("error");
            st.setMessage("插入失败");
            return st;
        }


    }

    public Info<Repair> selectById(int RepairID) {
        Info<Repair> info = new Info<Repair>();
        Repair Repair = new Repair();
        Repair = RepairMapper.selectId(RepairID);
        if (Repair != null) {
            info.setT(Repair);
            info.setMesage("查询成功");
            info.setStatus("ok");
            return info;
        } else {
            info.setMesage("查询失败");
            info.setStatus("error");
            return info;
        }


    }

    public Info<Repair> findList() {
        Info<Repair> info = new Info<Repair>();
        List<Repair> list = new ArrayList<Repair>();
        list = RepairMapper.selectlist();
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

    public Info<Repair> findLike(int ownerID) {
        Info<Repair> info = new Info<Repair>();
        List<Repair> list = new ArrayList<Repair>();
        list = RepairMapper.selectlike(ownerID);
        if (list.isEmpty()) {
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

    public Info<Repair> findByStatus(int status, int userId) {
        Info<Repair> info = new Info<Repair>();
        List<Repair> list = new ArrayList<Repair>();
        list = RepairMapper.selectByStatus(status, userId);
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

    public Stuts deleteByID(int RepairID) {
        Stuts st = new Stuts();
        int status = RepairMapper.delete(RepairID);
        if (status == 1) {
            st.setStuts("ok");
            st.setMessage("删除成功");
            return st;

        } else {
            st.setStuts("error");
            st.setMessage("删除失败");
            return st;
        }


    }

    public Stuts update(Repair Repair) {
        Stuts st = new Stuts();
        int status = RepairMapper.update(Repair);
        if (status == 1) {
            st.setStuts("ok");
            st.setMessage("修改成功");
            return st;

        } else {
            st.setStuts("error");
            st.setMessage("修改失败");
            return st;
        }

    }

}
