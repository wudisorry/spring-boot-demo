package com.arh.springbootdemo.service.dynamicDataSourceImpl;

import com.arh.springbootdemo.configuration.DynamicDataSourceContext;
import com.arh.springbootdemo.configuration.DynamicDataSourceSwitch;
import com.arh.springbootdemo.dao.IUserRepository;
import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(name = "use.dynamicDataSource", havingValue = "true", matchIfMissing = false)
@DynamicDataSourceSwitch(dataSourceKey = DynamicDataSourceContext.USER_KEY)
@Transactional
public class UserServiceDynamicSourceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        //throw new MyServiceException("就是想报错");
    }

    @Override
    public User queryUserById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User u = byId.orElse(new User());
        return u;
    }
}
