package com.logistics.plan.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.plan.domain.entity.User;
import com.logistics.plan.service.UserService;
import com.logistics.plan.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

   /* @Autowired
    private UserRedis userRedis;
    private static final String keyHead="token:";*/
   @Autowired
   private UserService userService;

    @PostMapping(value = "/login")
    public R login(@RequestBody User user, HttpServletRequest request){
        String username = user.getUsername();
        String password = user.getPassword();
//        Message msg = new Message();
        if (StringUtils.isEmpty(username)){
//            msg.setStatus(false);
//            msg.setMessage("请输入账号");
            return R.failed("账号不能为空");
        }
        if (StringUtils.isEmpty(password)){
//            msg.setStatus(false);
//            msg.setMessage("请输入密码");
            return R.failed("密码不能为空");
        }

        User userServiceOne = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        if(userServiceOne == null){
            return R.failed("账号不存在，请重新登录");
        }
        if(!userServiceOne.getPassword().equals(password)){
            return R.failed("密码不正确，请重新输入");
        }
//        if(!userName.equals("admin") || !password.equals("cpricpri")){
//            msg.setStatus(false);
//            msg.setMessage("账号或者密码错误");
//            return msg;
//        }
//        String token = UUID.randomUUID().toString();
        //userRedis.add("token","384b11c5-a52e-4159-9e4e-3d9a1bcaa2c",4L);
//        msg.setStatus(true);
//        msg.setMessage("384b11c5-a52e-4159-9e4e-3d9a1bcaa2c");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", userServiceOne.getRole()+","+username);
//        jsonObject.put("role", userServiceOne.getRole());
        return R.ok(jsonObject);
    }

    /**
     * 新增账户
     * @param user
     * @return
     */
    @PostMapping("register")
    public R registerUser(@RequestBody User user){
        String username = user.getUsername();
        int count = userService.count(new QueryWrapper<User>().eq("username", username));
        if(count >= 1){
            return R.failed("账号已存在，请重新输入");
        }else {
            //新增的为学生账户
            user.setRole("student");
            userService.save(user);
            return R.ok("新增成功");
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PatchMapping("update")
    public R updateUser(@RequestBody User user){
        userService.updateById(user);
        return R.ok();
    }

    /**
     * 查询用户列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public IPage listStudentUser(@RequestParam("pageNo") Integer pageNo,
                                 @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "username", required = false) String username){
        Page<User> page = new Page<>(pageNo, pageSize);
        return userService.page(page,
                new QueryWrapper<User>()
                        .eq("role", "student")
                        .likeRight(username != null && !username.equals(""), "username", username)
                        .likeRight(name != null && !name.equals(""), "name", name));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteUser(@PathVariable String id){
        userService.removeById(id);
        return R.ok();
    }

}
