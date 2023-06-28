package com.Shopping.Ecommerce.App.Controller;

import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.RequestDTO.CustomerRequestDto;
import com.Shopping.Ecommerce.App.RequestDTO.OrderRequestDto;
import com.Shopping.Ecommerce.App.RequestDTO.RemoveCardForCustomerDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CustomerResponseDto;
import com.Shopping.Ecommerce.App.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto;
        try{
            customerResponseDto = customerService.addCustomer(customerRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getById")
    public ResponseEntity getCustomerById(@RequestParam("id") int id){
        CustomerResponseDto customerResponseDto;
        try{
            customerResponseDto = customerService.getCustomerByID(id);
        }
        catch(Exception e){
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public List<CustomerResponseDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity deleteCustomerByID(@RequestParam("id") int id){
        try{
            customerService.deleteCustomerByID(id);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Customer removed", HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/getByEmail")
    public ResponseEntity getCustomerByEmail(@RequestParam("email") String email){
        CustomerResponseDto customerResponseDto;
        try{
            customerResponseDto = customerService.getCustomerByEmail(email);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.FOUND);
    }

    @DeleteMapping("/removeCard")
    public ResponseEntity<String> removeCard(@RequestBody RemoveCardForCustomerDto removeCardForCustomerDto){
        try{
            customerService.removeCard(removeCardForCustomerDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Card removed", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeAllCards")
    public ResponseEntity<String> removeCard(@RequestParam int customerId){
        try{
            customerService.removeAllCards(customerId);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Cards removed", HttpStatus.ACCEPTED);
    }
}