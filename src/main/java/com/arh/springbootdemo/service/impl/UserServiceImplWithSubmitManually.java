package com.arh.springbootdemo.service.impl;

import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.service.IUserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/10
 **/
@Service("userServiceImplWithSubmitManually")
public class UserServiceImplWithSubmitManually implements IUserService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addUser(User user) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Connection connection = ((SessionImplementor) session).connection();
        session.save(user);
        User user2 = session.find(User.class, 200);
        System.out.println(user2);
        showConn(connection, "afterSave");
        session.flush();
        User user3 = session.find(User.class, 200);
        System.out.println(user3);
        showConn(connection, "afterFlush");
//        session.clear();
        transaction.commit();
        User user4 = session.find(User.class, 200);
        System.out.println(user4);
        showConn(connection, "afterCommit");
    }

    private void showConn(Connection connection, String actionName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user where id = 200");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(actionName + ":" + resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User queryUserById(Integer id) {
        return null;
    }
}
