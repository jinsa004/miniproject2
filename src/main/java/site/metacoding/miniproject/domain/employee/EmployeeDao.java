package site.metacoding.miniproject.domain.employee;

import java.util.List;

public interface EmployeeDao {
	public List<Employee> findAll();

	public Employee findById(Integer employeeId);

	public void insert(Employee employee);

	public void update(Employee employee);

	public void deleteById(Integer employeeId);

	public Employee findByEmployeeUsername(String employeeUsername);

	public Employee findByEmployeePassword(String employeePassword);

	public Employee findByEmployeeEmail(String employeeEmail);
}
