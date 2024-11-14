package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;


/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {       

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertDTOToSchedule(scheduleDTO);
		return convertScheduleToDTO(scheduleService.save(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> schedulesDTO = schedules.stream()
                                                  .map(this::convertScheduleToDTO)
                                                  .collect(Collectors.toList());
        return schedulesDTO;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
       // throw new UnsupportedOperationException();
       List<Schedule> schedules = scheduleService.getAllSchedulesForPet(petId);
       List<ScheduleDTO> schedulesDTO = schedules.stream()
                                                  .map(this::convertScheduleToDTO)
                                                  .collect(Collectors.toList());
       return schedulesDTO;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
        List<Schedule> schedules = scheduleService.getAllSchedulesForEmployee(employeeId);
        List<ScheduleDTO> schedulesDTO = schedules.stream()
                                                  .map(this::convertScheduleToDTO)
                                                  .collect(Collectors.toList());
        return schedulesDTO;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        //throw new UnsupportedOperationException();
        List<Schedule> schedules = scheduleService.getAllSchedulesForCustomer(customerId);
        List<ScheduleDTO> schedulesDTO = schedules.stream()
                                                  .map(this::convertScheduleToDTO)
                                                  .collect(Collectors.toList());
        return schedulesDTO;
    }


    private Schedule convertDTOToSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);

		// List<Long> petIds = scheduleDTO.getPetIds();
		// if (petIds != null) {
		// 	List<Pet> pets = petService.getPetsByIds(petIds);
		// 	schedule.setPets(pets);
		// }

        // List<Long> employeeIds = scheduleDTO.getEmployeeIds();
		// if (employeeIds != null) {
		// 	List<Employee> employees = employeeService.getEmployeesByIds(employeeIds);
		// 	schedule.setEmployees(employees);
		// }
        schedule.setPets(petService.getPetsByIds(scheduleDTO.getPetIds()));
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setEmployees(employeeService.getEmployeesByIds(scheduleDTO.getEmployeeIds()));

		return schedule;
	}

	private ScheduleDTO convertScheduleToDTO(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(schedule, scheduleDTO);

		List<Pet> pets = schedule.getPets();
        List<Long> petIds = pets.stream()
                                .map(pet -> pet.getId())
                                .collect(Collectors.toList());
		
		
        List<Employee> employees = schedule.getEmployees();
		List<Long> employeeIds = employees.stream()
                                            .map(employee -> employee.getId())
                                            .collect(Collectors.toList());
		

        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);

		return scheduleDTO;
	}
}
