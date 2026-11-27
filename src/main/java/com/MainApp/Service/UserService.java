package com.MainApp.Service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.MainApp.Entity.User;
import com.MainApp.Repository.UserRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {

	@Autowired
	UserRepository uRepo;
	
	public boolean saveUser(User u)
	{
		u.setStatus(false);
		u.setVerification(UUID.randomUUID().toString());
		
		Optional<User> op =  uRepo.findById(u.getUsername());
		if(op.isEmpty())
		{
			User newU = uRepo.save(u);
			if(newU != null)
			{
				sendMail(newU);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Autowired
	JavaMailSender mailSender;
	
	public void sendMail(User u)
	{
		String from = "sakshimane276@gmail.com";
		String to = u.getEmail();
		String subject = "Account verification";
		
		String content = "Dear [[name]],"
				+ "Please click on the link given below to verify your account"
				+"<h1><a href = \'[[url]]\' target=\'_black\'>VERIFY</a></h1>";
		content = content.replace("[[name]]", u.getUsername());
		String url = "http://localhost:8081/verify?code="+u.getVerification();
		
		content = content.replace("[[url]]", url);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try
		{
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			mailSender.send(message);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	
	String result="";
	public String checkUser(String username, String password)
	{
		Optional<User> op = uRepo.findByUsernameAndPassword(username, password);
		if(op.isPresent())
		{
			result = "exists";
			if(op.get().isStatus() == false)
			{
				result = "Your account is inactive...Please activate your account";
				
			}
		}
			else
			{
				result="Invalid credentials";
			}
			return result;
		}
	
	public boolean verify(String code)
	{
		Optional<User> op= uRepo.findByVerification(code);
		if(op.isPresent())
		{
			op.get().setStatus(true);
			op.get().setVerification(null);
			uRepo.save(op.get());
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

