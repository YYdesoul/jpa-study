## I. 简介

### ORM

在解释什么是JPA和Spring Data JPA之前我们应该先来了解一下什么是ORM。**ORM(Object-Relational Mapping, 对象关系映射)，是一种面向对象编程语言中的对象和数据库中的数据之间的映射**。使用ORM工具、框架可以让应用程序操作数据库。在过去，有很多针对Java的ORM框架，但是每一套框架都有自己的一套操作方法和规范，这就使得Java程序操作不同数据库时显得杂乱无章。于是乎，Sun公司推出了一套操作持久层（数据库）的规范(API)用于结束这种乱象，这套规范也就是**JPA**。

### JPA

JPA(Java Persistence API，Java持久层API) 是Sun公司定义的一套**基于ORM的接口规范**，用于给Java程序操作数据库。JPA 通过 JDK 5.0 注解描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中 。在这之后很多ORM框架实现了JPA规范，其中最有名的有**Hibernate**、TopLink、JDO等。JPA 和 Hibernate 的关系就像 JDBC 和 JDBC 驱动的关系，JPA 是规范，Hibernate 除了作为 ORM 框架之外，它也是一种 JPA 实现。

![jpa_and_hibernate](C:\study\java\jpa-study\blog\springdata_jpa_img\jpa_and_hibernate.png)

**JPA的优势**

- **标准化** 

	JPA 是 JCP 组织发布的 Java EE 标准之一，因此任何声称符合 JPA 标准的框架都遵循同
	样的架构，提供相同的访问API，这保证了基于JPA开发的企业应用能够经过少量的修改就能够在 不同的 JPA 框架下运行。

- **容器级特性的支持** 

	JPA 框架中支持大数据集、事务、并发等容器级事务，这使得 JPA 超越了简单持久化框架的 局限，在企业应用发挥更大的作用。

- **简单方便** 

	JPA 的主要目标之一就是提供更加简单的编程模型：在 JPA 框架下创建实体和创建 Java 类一
	样简单，没有任何的约束和限制，只需要使用 javax.persistence.Entity 进行注释，JPA的 框架和接口也都非常简单，没有太多特别的规则和设计模式的要求，开发者可以很容易的掌握。 JPA 基于非侵入式原则设计，因此可以很容易的和其它框架或者容器集成

- **查询能力**

	 JPA 的查询语言是面向对象而非面向数据库的，它以面向对象的自然语法构造查询语句，可以
	看成是 Hibernate HQL 的等价物。JPA 定义了独特的 JPQL（Java Persistence Query Language），JPQL 是 EJB QL 的一种扩展，它是针对实体的一种查询语言，操作对象是实体，而 不是关系数据库的表，而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING 等通常只有 SQL 才能够提供的高级查询特性，甚至还能够支持子查询

- **高级特性**

	 JPA 中能够支持面向对象的高级特性，如类之间的继承、多态和类之间的复杂关系，这样的支 持能够让开发者最大限度的使用面向对象的模型设计企业应用，而不需要自行处理这些特性在关系 数据库的持久化

Spring Data JPA 是 Spring 基于 ORM 框架、JPA 规范的基础上封装的一套 JPA 应用框架，可使开 发者用极简的代码即可实现对数据库的访问和操作。它提供了包括增删改查等在内的常用功能，且 易于扩展！学习并使用 Spring Data JPA 可以极大提高开发效率！
Spring Data JPA 让我们解脱了 DAO 层的操作，基本上所有 CRUD 都可以依赖于它来实现,在实际的 工作工程中，推荐使用 Spring Data JPA + ORM（如：hibernate）完成操作，这样在切换不同的 ORM 框架时提供了极大的方便，同时也使数据库层操作更加简单，方便

### Spring Data JPA

Spring Data JPA 是 Spring 基于 ORM 框架、JPA 规范的基础上封装的一套 JPA 应用框架，可使开 发者用极简的代码即可实现对数据库的访问和操作。它提供了包括增删改查等在内的常用功能，且易于扩展！学习并使用 Spring Data JPA 可以极大提高开发效率！

Spring Data JPA 让我们解脱了 DAO 层的操作，基本上所有 CRUD 都可以依赖于它来实现,在实际的 工作工程中，推荐使用 Spring Data JPA + ORM（如：hibernate）完成操作，这样在切换不同的 ORM 框架时提供了极大的方便，同时也使数据库层操作更加简单，方便解耦。

## III. 第一个Spring Data JPA项目

**步骤如下**

1. 在pom.xml中导入依赖

	```xml
	    <dependencies>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-data-jpa</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-jdbc</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-web</artifactId>
	        </dependency>
	
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <scope>runtime</scope>
	        </dependency>
	        <dependency>
	            <groupId>org.projectlombok</groupId>
	            <artifactId>lombok</artifactId>
	            <optional>true</optional>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-test</artifactId>
	            <scope>test</scope>
	        </dependency>
	    </dependencies>
	```

	

2. 配置application.yml

	```yaml
	spring:
	  datasource:
	    driver-class-name: com.mysql.cj.jdbc.Driver
	    url: jdbc:mysql://localhost:3306/jpa_study?useUnicode=true&characterEncoding=utf8
	    username: root
	    password: 123456
	  data:
	    rest:
	      basePath: /api
	  jpa:
	    hibernate:
	      ddl-auto: update
	    show-sql: true
	
	server:
	  port: 8080
	```

	

3. 编写实体类

	```java
	package com.soul.jpastudy.domain;
	
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;
	
	import javax.persistence.*;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	
	/**
	 * 客户的实体类
	 *      配置映射关系
	 *          1. 实体类和表的映射关系
	 *          2. 实体类中属性和表中字段的映射关系
	 *  @Entity:声明实体类
	 *  @Table: 配置实体类和表的映射关系
	 *      name: 配置数据库表的名称
	 */
	
	@Entity
	@Table(name = "cst_customer")
	public class Customer {
	
	    @Id // 声明主键的配置
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
	    @Column(name = "cust_id")   // 映射数据库表中的字段
	    private Long custId;
	
	    @Column(name = "cust_name")
	    private String custName;
	
	    @Column(name = "cust_source")
	    private String custSource;
	
	    @Column(name = "cust_level")
	    private String custLevel;
	
	    @Column(name = "cust_industry")
	    private String custIndustry;
	
	    @Column(name = "cust_phone")
	    private String custPhone;
	
	    @Column(name = "cust_address")
	    private String custAddress;
	
	}
	```

	

4. 编写Repository

	```java
	package com.soul.jpastudy.repository;
	
	import com.soul.jpastudy.domain.Customer;
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;
	import org.springframework.data.rest.core.annotation.RepositoryRestResource;
	import org.springframework.transaction.annotation.Transactional;
	
	import java.util.List;
	
	@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
	public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	    Customer findByCustName(String custName);
	
	    @Query("select c from Customer c")
	    List<Customer> findAll();
	
	    @Transactional
	    @Modifying
	    @Query("update Customer c set c.custName = ?1 where c.custId = ?2")
	    int updateCustomer(@Param("custName") String custName, @Param("custId") Long custId);
	
	    @Transactional
	    @Modifying
	    @Query("delete from Customer c where c.custId = :custId and c.custIndustry = :custIndustry")
	    int deleteCustomer(@Param("custId") Long custId, @Param("custIndustry") String custIndustry);
	
	}
	
	```

	

5. 单元测试

	```java
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
	```

	

## IV. JPQL

基于首次在 EJB2.0 中引入的 EJB 查询语言(EJB QL),Java **JPQL(Java Persistence Query Language)是一种可移植的查询语言**，旨在以面向对象表达式语言的表达式，将 SQL 语法和简单查询语义绑定在一起使用这种语言编写的查询是可移植的，可以被编译成所有主流数据库服务器上的 SQL。

其特征与原生SQL语句类似，并且完全面向对象，通过类名和属性访问，而不是表名和表的属性。

**基本语法**

```sql
select 实体别名.属性名，
from 实体名 as 实体别名 
where 实体别名.实体属性 op 比较值

# example
update Customer c set c.custName = ?1 where c.custId = ?2 # ?后跟变量序号（详见第一个Spring Data JPA项目）
delete from Customer c where c.custId = :custId and c.custIndustry = :custIndustry # :后跟变量名（详见第一个Spring Data JPA项目）

```

## 动态查询

在Spring Data JPA中，可以使用匿名内部类的方式添加动态条件，实现动态查询。步骤如下：

1. 通过匿名内部类的方式创建一个Specification<Entity>对象
2. 编写toPredicate方法(
	- root: Table对应的Entity
	- CriteriaBuilder用于创建条件
3. 编写具体的条件

**示例代码：**

```java
    @Test
    public void testDynamicQuery() {
        List<User> users = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                Path<String> pwdPath = root.get("pwd");
                Predicate pwdPredicate = criteriaBuilder.equal(pwdPath, "123");
                predicate = criteriaBuilder.and(predicate, pwdPredicate);
                Path<String> namePath = root.get("name");
                Predicate namePredicate = criteriaBuilder.like(namePath, "%Jack%");
                predicate = criteriaBuilder.and(pwdPredicate, namePredicate);
                Path<Integer> idPath = root.get("id");
                Predicate idPredicate = criteriaBuilder.equal(idPath, 2);
                predicate = criteriaBuilder.and(predicate, idPredicate);
                return predicate;
            }
        });
        
        // print results
        for (User user : users) {
            System.out.println(user);
        }
    }
```



## 一对多、多对一、多对多

Spring Data JPA中可以在实体类属性上添加`@ManyToOne`、`@OneToMany`、`@ManyToMany`注解配置多对一、一对多和多对多。

### 1. 一对多、多对一

**@OneToMany**: 

- 作用：建立一对多的关系映射 
- 属性： 
	- targetEntityClass：指定多的多方的类的字节码
	-  mappedBy：指定从表实体类中引用主表对象的名称。 
	- cascade：指定要使用的级联操作 
	- fetch：指定是否采用延迟加载 
	- orphanRemoval：是否使用孤儿删除

**@ManyToOne** 

- 作用：建立多对一的关系 
- 属性： 
	- targetEntityClass：指定一的一方实体类字节码 
	- cascade：指定要使用的级联操作 
	- fetch：指定是否采用延迟加载 
	- optional：关联是否可选。如果设置为 false，则必须始终存在非空关系。

**@JoinColumn**

- 作用：用于定义主键字段和外键字段的对应关系。 
- 属性： 
	- name：指定外键字段的名称 
	- referencedColumnName：指定引用主表的主键字段名称 
	- unique：是否唯一。默认值不唯一 
	- nullable：是否允许为空。默认值允许。 
	- insertable：是否允许插入。默认值允许。 
	- updatable：是否允许更新。默认值允许。 
	- columnDefinition：列的定义信息。

**级联操作**

级联操作：指操作一个对象同时操作它的关联对象

使用方法：只需要在操作主体的注解上配置 cascade

```java
/**
	* cascade:配置级联操作 
	* CascadeType.MERGE 级联更新 
	* CascadeType.PERSIST 级联保存：
	* CascadeType.REFRESH 级联刷新：
	* CascadeType.REMOVE 级联删除： 
	* CascadeType.ALL 包含所有
*/ 
@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
```



**示例代码**

```java
@Entity
@Table(name = "article")
@IdClass(ArticlePrimaryKey.class)
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @Column(name = "article_id", nullable = false)
    private Integer articleId;

    @Column(name = "article_name")
    private String articleName;
```

```java
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "country_id")
    private int countryId;


    @OneToMany(targetEntity = Article.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<Article> articles;
}
```



### 2. 多对多

@ManyToMany 

- 作用：用于映射多对多关系 
- 属性： cascade：配置级联操作。 
	- fetch：配置是否采用延迟加载。 
	- targetEntity：配置目标的实体类。映射多对多的时候不用写。

@JoinTable 

- 作用：针对中间表的配置 
- 属性：
	- name：配置中间表的名称
	-  joinColumns：中间表的外键字段关联当前实体类所对应表的主键字段
	- inverseJoinColumn：中间表的外键字段关联对方表的主键字段

@JoinColumn 

- 作用：用于定义主键字段和外键字段的对应关系。 
- 属性： 
	- name：指定外键字段的名称
	-  referencedColumnName：指定引用主表的主键字段名称 
	- unique：是否唯一。默认值不唯一
	- nullable：是否允许为空。默认值允许。 
	- insertable：是否允许插入。默认值允许。 
	- updatable：是否允许更新。默认值允许。 
	- columnDefinition：列的定义信息。



## 多表+动态查询

**步骤**

1. 创建结果实体类

	```java
	package com.soul.entity;
	
	
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class UserArticleDTO {
	    private Integer id;
	
	    private String name;
	
	    private String articleName;
	}
	
	```

	

2. 编写Repository

	```java
	package com.soul.repository;
	
	import com.soul.entity.Article;
	import com.soul.entity.User;
	import com.soul.entity.UserArticleDTO;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;
	
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import javax.persistence.TypedQuery;
	import javax.persistence.criteria.*;
	import java.util.ArrayList;
	import java.util.List;
	
	@Repository
	public class UserArticleDao {
	    @Autowired
	    @PersistenceContext
	    private EntityManager em;
	
	    static final Integer PAGE_SIZE = 2;
	
	    public List<UserArticleDTO> findUserArticle(int currentPage) {
	        CriteriaBuilder builder = em.getCriteriaBuilder();
	        CriteriaQuery<UserArticleDTO> query = builder.createQuery(UserArticleDTO.class);
	
	        // from table
	        Root<User> rootUser = query.from(User.class);
	        Root<Article> rootArticle = query.from(Article.class);
	
	        // where conditions
	        List<Predicate> predicates = new ArrayList<>();
	
	        Predicate predicate1 = builder.equal(rootUser.get("id"), rootArticle.get("userId"));
	        Predicate predicate2 = builder.equal(rootArticle.get("articleName"), "macbook");
	        Predicate predicate3 = builder.like(rootArticle.get("articleName"), "%ms surface%");
	        Predicate predicateOr = builder.or(predicate2, predicate3);
	        predicates.add(predicate1);
	        predicates.add(predicateOr);
	
	        Predicate finalPredicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));
	
	
	        query.multiselect(rootUser.get("id").as(Integer.class), rootUser.get("name").as(String.class),
	                rootArticle.get("articleName").as(String.class))
	                .where(finalPredicate);
	
	        // no paging
	        // List<UserArticleDTO> resultList = em.createQuery(query).getResultList();
	        // return resultList;
	
	        // add paging to query
	        TypedQuery<UserArticleDTO> typedQuery = em.createQuery(query);
	        typedQuery.setFirstResult((currentPage - 1) * PAGE_SIZE);
	        typedQuery.setMaxResults(PAGE_SIZE);
	
	        // execute query
	        List<UserArticleDTO> resultList = typedQuery.getResultList();
	
	        return resultList;
	
	    }
	}
	
	```

	

3. 测试

	```java
	    @Test
	    public void testUserArticleDao() {
	        List<UserArticleDTO> userArticle = userArticleDao.findUserArticle(1);
	        for (UserArticleDTO userArticleDTO : userArticle) {
	            System.out.println(userArticleDTO);
	        }
	    }
	```

## Spring Data JPA 对比 MyBatis

**比较**

- hibernate是面向对象的，而MyBatis是面向关系的
- 数据分析型的OLAP应用适合用MyBatis，事务处理型OLTP应用适合用JPA

- 项目维护迭代维度比较（长期快速迭代类、变动较小的类型）

	追求快速迭代，需求快速变更，灵活的 mybatis 修改起来更加方便，而且一般每一次的改动不会带来性能上的下降。JPA经常因为添加关联关系或者开发者不了解优化导致项目越来越糟糕（这里可能要考研功力了）

**比较总结**

- 表关联较多的项目，优先使用mybatis

- 持续维护开发迭代较快的项目建议使用mybatis，因为一般这种项目需要变化很灵活，对sql的灵活修改要求较高

- 对于传统项目或者关系模型较为清晰稳定的项目，建议JPA（比如DDD设计中的领域层）

- 目前微服务比较火，基于其职责的独立性，如果模型清晰，可以考虑使用JPA，但如果数据量较大全字段返回数据量大的话可能在性能有影响，需要根据实际情况进行分析

## 总结

Spring Data JPA 是Spring Data中一款强大的用于操作关系型数据库的技术。它的出现，省去了我们编写Dao层的步骤。在业务逻辑相对简单的时候，使用它会极大的提高开发效率。但是当业务逻辑太过复杂时，Spring Data JPA缺乏灵活性的问题就会暴露出来，这个时候MyBatis反而是更好的选择。

## 参考

[SpringBoot Jpa入门案例](https://blog.csdn.net/weixin_43780471/article/details/96589631?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-1.control&spm=1001.2101.3001.4242)

[JPQL和SQL的比较](https://blog.csdn.net/zt15732625878/article/details/78378995)

[Spring Data JPA中多表联合查询最佳实践](https://zhongpan.tech/2020/07/20/034-best-practice-of-multi-table-joint-query-in-spring-data-jpa/)

[Spring Data JPA 和MyBatis比较](https://www.huaweicloud.com/articles/92ab56a0be0452dc0faae9fd5055bd03.html)

