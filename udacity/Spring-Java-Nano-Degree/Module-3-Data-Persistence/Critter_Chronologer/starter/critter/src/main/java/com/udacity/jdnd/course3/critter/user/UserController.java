package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;


/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertDTOToCustomer(customerDTO);
        Customer savedCustomer = customerService.save(customer);
        CustomerDTO savedCustomerDTO = convertCustomerToDTO(savedCustomer);
        return savedCustomerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> listOfCustomers = customerService.getAllCustomers();
        List<CustomerDTO> listOfCustomersDTO = listOfCustomers.stream()
                                                              .map(this::convertCustomerToDTO)  
                                                              .collect(Collectors.toList());
        return  listOfCustomersDTO;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getCustomerByPetId(petId);
        CustomerDTO customerDTO = convertCustomerToDTO(customer);
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertDTOToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.save(employee);
        EmployeeDTO savedEmployeeDTO = convertEmployeeToDTO(savedEmployee);
        return savedEmployeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        EmployeeDTO employeeDTO = convertEmployeeToDTO(employee);
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
        Employee employee = employeeService.findById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.save(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        //throw new UnsupportedOperationException();
        List<Employee> employees = employeeService.getEmployeesForService(employeeDTO.getDate(), employeeDTO.getSkills());
        List<EmployeeDTO> employeesDTO = employees.stream()
                                                  .map(this::convertEmployeeToDTO)
                                                  .collect(Collectors.toList());
        return employeesDTO;
    }

    /////////////////////////////////////////////////////////////////////
    private CustomerDTO convertCustomerToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);

		// Retrieve Pet's identifiers from Pet entities
		List<Pet> pets = customer.getPets();
		if (pets != null) {
			List<Long> petIds = pets.stream()
                                    .map(pet -> pet.getId())
                                    .collect(Collectors.toList());

			customerDTO.setPetIds(petIds);

		}
		return customerDTO;
    }
    

    private Customer convertDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        // Retrieve Pet's identifiers to get Pet entities
        List<Long> petIds = customerDTO.getPetIds();

        if (petIds != null) {
            List<Pet> pets = petService.getPetsByIds(petIds);
            customer.setPets(pets);
        }
        return customer;
    }
///////////////////////////////////////////////////////////////////////

    private EmployeeDTO convertEmployeeToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
        employeeDTO.setSkills(employee.getSkills());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
		return employeeDTO;
    }

    private Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
        employee.setSkills(employeeDTO.getSkills());
		employee.setDaysAvailable(employeeDTO.getDaysAvailable());
		return employee;
	}

}
