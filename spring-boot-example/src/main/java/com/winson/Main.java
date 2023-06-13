package com.winson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

//Special annotation that makes this a springbootapplication
@SpringBootApplication
//This annotation takes from @Controller and @ResponseBody making the class a controller and that all methods in the marked class will return a JSON response
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // This is for HTTP GET request where it specifies a GET method in the controller for serving GET HTTP request
    @GetMapping
    public List<Customer> getCustomers() {
        // getCustomer returns customer
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age

    ){

    }
    // This is for HTTP POST request where it specifies a POST method in the controller for serving POST HTTP request
    @PostMapping
    // We get the request and take the request converting it to customer and save
    // RequestBody capturing the request that comes from the client. Its a json object that is mapped into newCustomerRequest request
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);

    }

    @DeleteMapping("{customerId}")
    // PathVariable grabs the customerId and maps it into the variable id
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);
    }


}
//    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
//    }

//    In order for us to expose this method as a rest endpoint for clients to use as get request we use this annotation with a path. Also need the @RestController. This means any method within this class with this annotation will be exposed as rest endpoints that clients can call
    // This is for HTTP GET request where it specifies a GET method in the controller for serving GET HTTP request
//    @GetMapping("/greet")
//    public GreetResponse greet() {
//        // returning the value for all the variables (greet, favProgrammingLanguage, person)
//        GreetResponse response = new GreetResponse(
//                "Hello",
//                // in the json it shows the as array
//                List.of("Java", "Golang", "Javascript"),
//                new Person("Alex", 28, 30_000)
//        );
//        return response;
//    }

    // Creating string variable
//    record Person (String name, int age, double savings) {
//
//    }
    // Creating string variables for this GreatResponse object
//    record GreetResponse(
//            String greet,
//            List<String> favProgrammingLanguages,
//            // Person is object
//            Person person
//    )
//    {
//
//    }

//        class GreetResponse {
//            private final String greet;
//
//
//            GreetResponse(String greet) {
//                this.greet = greet;
//            }
//
//            // getter get method
//            public String getGreet() {
//                return greet;
//            }
//
//            @Override
//            public String toString() {
//                return "GreetResponse{" +
//                        "greet='" + greet + '\'' +
//                        '}';
//            }
//
//            @Override
//            public boolean equals(Object o) {
//                if (this == o) return true;
//                if (o == null || getClass() != o.getClass()) return false;
//                GreetResponse that = (GreetResponse) o;
//                return Objects.equals(greet, that.greet);
//            }
//
//            @Override
//            public int hashCode() {
//                return Objects.hash(greet);
//            }
//        }


