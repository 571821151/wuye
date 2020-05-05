package tsu.pro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsu.pro.bean.*;
import tsu.pro.dao.UserDao;
import tsu.pro.mapper.HouseMapper;
import tsu.pro.mapper.RoleMapper;
import tsu.pro.mapper.UserMapper;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDao userdao;
    @Autowired
    RoleMapper rolemapper;
    @Autowired
    HouseMapper houseMapper;

    public String insertUser(@SuppressWarnings("rawtypes") User user) {


        System.out.println(user.toString());
        if (userMapper.insertUser(user) != 1) {

            return "插入错误";
        } else {
            try {
                User_Role role = new User_Role();
                role.setUser_id(userMapper.findUserByUserName(user.getName()).getId());
                role.setRole_id(1);
                rolemapper.userAddRole(role);

                return "插入成功";
            } catch (Exception e) {
                return "权限设置失败";

            }


        }

    }

    @SuppressWarnings("rawtypes")
    public userInfo<User> userList() {
        userInfo<User> info = new userInfo<User>();
        ArrayList<User> userList = new ArrayList<User>();
        userList = userMapper.queryAll();
        List list = new ArrayList<>();

        if (userList.size() >= 1) {
            info.setStatus("ok");
            info.setMessage("查询成功！");
            info.setInfos(userList);
            return info;
        } else {
            info.setStatus("error");
            info.setMessage("查询失败");
            return info;
        }

    }

    public userInfo findUser(String name, String password) {
        userInfo<User> info = new userInfo<User>();
        User<Permission> user = new User<Permission>();

        user = userMapper.finduserByName(name, password);
        if (null != user) {
            user.setPer(userdao.selectPermission(user));
            try {

                User_Role ur = rolemapper.queryRoleByUserid(user.getId());
                user.setUserRole(ur.getRole_id());
                House house = houseMapper.getByUserId(user.getId());
                if (house != null)
                    user.setHouserDes(house.getHouseDes() + house.getHouseName());

            } catch (Exception e) {
            }
            info.setStatus("ok");
            info.setMessage("登录成功");
            info.setT(user);
            return info;
        } else {
            info.setStatus("error");
            info.setMessage("登录失败");
            return info;
        }

    }

    @SuppressWarnings("rawtypes")
    public userInfo<User> updateUser(User user) {
        userInfo<User> info = new userInfo<User>();
        @SuppressWarnings("unused")
        User users = new User();
        int i = userMapper.updateUser(user);
        if (i == 1) {
            info.setStatus("ok");
            info.setMessage("更新成功");

            return info;
        } else {
            info.setStatus("error");
            info.setMessage("更新失败");
            return info;
        }
    }

    @SuppressWarnings("rawtypes")
    public userInfo<User> deleteUser(int id) {
        userInfo<User> info = new userInfo<User>();
        @SuppressWarnings("unused")
        User users = new User();
        int i = userMapper.deleteUser(id);
        if (i == 1) {
            info.setStatus("ok");
            info.setMessage("删除成功");

            return info;
        } else {
            info.setStatus("error");
            info.setMessage("删除失败");
            return info;
        }
    }

    public userInfo<User> selectByID(int id) {
        userInfo<User> info = new userInfo<User>();
        User user = new User();
        List roleList = new ArrayList();
        user = userMapper.findTag(id);
        if (null != user) {
            roleList = userdao.selectrolebyID(user.getId());
            info.setInfos(roleList);
            info.setStatus("ok");
            info.setMessage("登录成功");
            info.setT(user);
            return info;
        } else {
            info.setStatus("error");
            info.setMessage("登录失败");
            return info;
        }
    }

    //用户是否存在
    public boolean ExitUser(String userName) {
        User user = userMapper.findUserByUserName(userName);
        if (null != user) {
            return true;
        } else {
            return false;
        }
    }

    public userInfo<User> deleteuserrole(int id) {
        userInfo<User> info = new userInfo<User>();
        int i = 0;
        i = userMapper.deleteUserrole(id);
        if (i == 1) {
            info.setStatus("ok");
            info.setMessage("成功");

            return info;
        } else {
            info.setStatus("error");
            info.setMessage("失败");
            return info;
        }
    }
}
