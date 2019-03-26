package com.kuan.demo.controller;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Topic;
import com.kuan.demo.entity.User;
import com.kuan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class UserController {

    //@Autowired
    private UserService userService;

    @RequestMapping(value = "add_user",method = RequestMethod.POST)
    public ServerResponse userAdd(@RequestParam (value = "nickName") String nickName, @RequestParam(value = "password") String password,@RequestParam(value = "sex")String sex, @RequestParam(value = "area") String area) throws IOException, InterruptedException {
        User user = new User();
        user.setNickname(nickName);
        user.setPassword(password);
        user.setArea(area);
        return  userService.putUser(user);
    }

    @RequestMapping(value = "get_user",method = RequestMethod.GET)
    public ServerResponse<User> userGet(@RequestParam(value = "uid") String uid) throws IOException {
        ServerResponse<User> user = userService.getUser(uid);
        return user;
    }

    @RequestMapping(value = "update_user",method = RequestMethod.POST)
    public ServerResponse userUpdate(@RequestParam(value = "uid")String uid,@RequestParam(value = "info") String info,@RequestParam(value = "operate") int operate ) throws IOException {
        return userService.updateInfo(uid,info,operate);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ServerResponse<List> usersGet (){
        List<User> list = new ArrayList<>();
        String [] id = {"0001","0002","0003","0004","0005","0006","0007","0008","0009","0010"};
        String [] area = {"上海","杭州","重庆","湖南","江苏","广东","山东","北京","广州","重庆"};
        String [] sex = {"男","男","女","女","女","女","女","女","男","男"};
        String [] nickName = {"薄荷微凉","南极森林","阡陌凉笙","风筝与风","可萌可撩","木槿惜年","奶包咕叽","屁颠小孩","冷城少年","疾风剑豪"};
        for (int i = 0 ; i<10; i++){
            User user = new User();
            user.setRowKey(id[i]);
            user.setArea(area[i]);
            user.setSex(sex[i]);
            user.setNickname(nickName[i]);
            list.add(user);
        }
        return  new ServerResponse<List>(1,"success",list);
    }

    @RequestMapping(value = "/topic",method = RequestMethod.GET)
    public ServerResponse<List> topicGet() {
        List<Topic> list = new ArrayList<>();
        String [] id = {"0001","0002","0003","0004","0005","0006","0007","0008","0009","0010"};
        String [] essay = {"在你想要放弃的那一刻，想想为什么当初坚持走到了这里。","世事岂能两全，我们的一生中，得到的同时也总在失去，幸与不幸的区别只在于得失之间孰重孰轻。","一池柔情，一抹芳菲，桥外的荻花，是老去的故事。嫣红的脸颊，寂寞绯红于三月花雨的传奇",
                "求而不得，舍而不能，得而不惜，这是人最大的悲哀。","年轻的时候，多忙活点，是好事，不管是徒劳的还是有用的，这都促使你成熟。","小楼一夜听春雨，深巷明朝卖杏花。","只恐夜深花睡去，故烧高烛照红妆。",
                "晚来天欲雪，能饮一杯无？","海水梦悠悠，君愁我亦愁。","郎骑竹马来，绕床弄青梅。"};
        for (int i=0; i<10; i++){
            Topic topic = new Topic();
            topic.setRowKey(id[i]);
            topic.setEssay(essay[i]);
            list.add(topic);
        }
        return  new ServerResponse<List>(1,"success",list);
    }
}
