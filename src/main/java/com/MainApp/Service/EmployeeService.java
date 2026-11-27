package com.MainApp.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MainApp.Entity.Employee;
import com.MainApp.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository eRepo;

	@Value("${app.upload-dir:src/main/webapp/images}")
	private String uploadDir;

	public void addEmployee(Employee e, MultipartFile file)
	{
		String imageName = null;
		if (file != null && !file.isEmpty())
		{
			imageName = Paths.get(file.getOriginalFilename()).getFileName().toString();
			e.setImageName(imageName);
		}
		eRepo.save(e);

		if (imageName == null)
		{
			return;
		}
		try
		{
			Path dir = Paths.get(uploadDir);
			Files.createDirectories(dir);
			try (InputStream is = file.getInputStream())
			{
				Files.copy(is, dir.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	public List<Employee> readAll(String username)
	{
		return eRepo.findByUsername(username);
	}

	public void deleteByid(int id)
	{
		eRepo.deleteById(id);
	}

	public void deleteAll()
	{
		eRepo.deleteAll();
	}
}