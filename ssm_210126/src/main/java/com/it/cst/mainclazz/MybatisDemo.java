package com.it.cst.mainclazz;

import com.alibaba.druid.pool.DruidDataSource;
import com.it.cst.bean.Blog;
import com.it.cst.mapper.BlogMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


import javax.sql.DataSource;

public class MybatisDemo {

    public static void main(String[] args) {
       // DataSource dataSource = getDataSource();
        DataSource dataSource= new ComboPooledDataSource();
        JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlog(2);
        System.out.println(blog);

        System.out.println("master");
    }


//    private static DataSource getDataSource(){
//
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUrl("jdbc:mysql://localhost:3306/mn_first_20201107?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=utc");
//        druidDataSource.setUsername("root");
//        druidDataSource.setPassword("root");
//           return  druidDataSource;
//    }

    
}
