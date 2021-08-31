package com.soul.jpastudy;

import com.soul.jpastudy.domain.Customer;
import com.soul.jpastudy.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 测试jpa的保存
 *      添加一个客户到数据库中
 * Jpa的操作步骤
 *      1. 加载配置文件创建工厂（实体管理类工厂）对象
 *      2. 通过实体管理工厂共创获取实体管理器
 *      3. 获取事务对象，开启事务
 *      4. 完成增删改查操作
 *      5. 提交事务（回滚事务）
 *      6. 释放资源
 */
@SpringBootTest
class JpaStudyApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindByCustName(){
        Customer customer = customerRepository.findByCustName("testName");
        System.out.println(customer);
    }

    @Test
    void testFindAll() {
        List<Customer> all = customerRepository.findAll();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    @Test
    void testUpdateCustomer() {
        int row = customerRepository.updateCustomer("testUpdate", 1L);
        System.out.println(row);
        testFindAll();
    }

    @Test
    void testDeleteCustomer() {
        int row = customerRepository.deleteCustomer(1L, "testIndustry");
        System.out.println(row);
        testFindAll();
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setCustName("save test");
        customer.setCustLevel("save level");
//        customer.setCustId(1L);   // 加上是更新某某，不加是添加新的一行数据
        customerRepository.save(customer);
        testFindAll();
    }


//    @Test
//    void testAddUser() {
//        // 1. 加载配置文件创建工厂（实体管理工厂对象）
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        // 2. 通过实体管理工厂共创获取实体管理器
//        EntityManager em = factory.createEntityManager();
//        // 3. 获取事务对象，开启事务
//        EntityTransaction tx = em.getTransaction();
//        // 4. 完成增删改查操作
//        Customer customer = new Customer();
//        customer.setCustName("Jack");
//        customer.setCustLevel("2");
//        // 保存
//        em.persist(customer);
//        // 5. 提交事务（回滚事务）
//        tx.commit();
//        // 6. 释放资源
//        em.close();
//        factory.close();
//    }

}
