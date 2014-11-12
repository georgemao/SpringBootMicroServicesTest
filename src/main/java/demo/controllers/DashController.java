package demo.controllers;

import demo.Customer;
import demo.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by George.Mao on 11/7/2014.
 */
@Controller // Spring MVC Controller
public class DashController {

    @Value("${my.custom.prop}")
    private String myProperty;

    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/test.html")
    public String test(Model model){

        System.out.println("Loaded: " + myProperty);
        model.addAttribute("myProp", myProperty);

        return "test";
    }


    @RequestMapping("/greeting")
    public String greeting(Model model){

        model.addAttribute("name", "mememe");

        return "greeting";
    }

    @RequestMapping("/eDBTest")
    public String eDBTest(Model model){

        // simple DS for test (not for production!)
        /*SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        */

        System.out.println("Creating tables");
        testRepository.jdbcTemplate.execute("drop table customers if exists");
        testRepository.jdbcTemplate.execute("create table customers(" +
                "id serial, first_name varchar(255), last_name varchar(255))");


        String[] names = "John Woo;Jeff Dean;Josh Bloch;Josh Long".split(";");
        for (String fullname : names) {
            String[] name = fullname.split(" ");
            System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
            testRepository.jdbcTemplate.update(
                    "INSERT INTO customers(first_name,last_name) values(?,?)",
                    name[0], name[1]);
        }

        List<Customer> customers = testRepository.getAllCustomers();
        customers.forEach(System.out::println);
        model.addAttribute("customers", customers);

        return "customers";
    }
}
