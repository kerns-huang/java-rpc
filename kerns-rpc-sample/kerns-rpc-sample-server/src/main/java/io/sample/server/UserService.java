package io.sample.server;

import io.samle.bean.User;

/**
 * @author xiaohei
 * @create 2020-07-05 下午10:11
 **/
public class UserService {

    public User getUserById(Integer id){
        User user=new User();
        user.setName("test");
        user.setId(id);
        return new User();
    }
}
