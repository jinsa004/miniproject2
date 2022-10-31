package site.metacoding.miniproject.domain.company;

import java.util.List;

public interface CompanyDao {
	public List<Company> findAll();

	public Company findById(Integer companyId);

	public void insert(Company company);

	public void update(Company company);

	public void deleteById(Integer companyId);

	public Company findByCompanyUsername(String companyUsername);

	public Company findByIdCompanyUsername(String companyUsername);

	public Company findByCompanyPassword(String companyPassword);

	public Company findByCompanyEmail(String companyEmail);
}
