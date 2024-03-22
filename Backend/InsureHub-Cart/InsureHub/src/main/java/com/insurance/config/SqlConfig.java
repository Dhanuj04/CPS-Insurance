//package insurance.config;
//
//import insurance.repository.CartRequestRepo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@Configuration
////EmployeeRepo.class shall have CRUD operations + custom code +
////it is possible to have multiple repo then your code shall look lik this
////@EnableMongoRepositories(basePackageClasses = {EmployeeRepo.class,UserRepo.class})
//@EnableJpaRepositories (basePackageClasses = CartRequestRepo.class)
//public class SqlConfig {
//
//    //    @Bean
//    public CommandLineRunner commandLineRunner(CartRequestRepo repo){
//        //repo
//
////        return args -> repo.insert(new Employee(101,"Dhanuj",10000,"dhanuj@gmail.com"));
//        return null;
//
//
//    }
//}