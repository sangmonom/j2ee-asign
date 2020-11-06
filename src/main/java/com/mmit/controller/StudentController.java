package com.mmit.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.entity.Student;
import com.mmit.service.StudentService;


@WebServlet({"/student","/student-add","/student-edit","/student-delete"})
public class StudentController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		EntityManagerFactory EMF=null;
		Object obj=getServletContext().getAttribute("emf");//application scope
		if(obj==null) {
		EMF=Persistence.createEntityManagerFactory("j2ee-asign");
		getServletContext().setAttribute("emf", EMF);
		}else {
			EMF=(EntityManagerFactory) obj;
		}
		stuService=new StudentService(EMF.createEntityManager());
		
	}
	@Override
	public void destroy() {
		EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
		if(emf!=null && emf.isOpen()) {
			emf.close();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
    	if("/student-add".equals(action)) {
    		//get data from req
    		String sid=req.getParameter("studentid");
    		String name=req.getParameter("studentname");
    		String email=req.getParameter("email");
    		String phone=req.getParameter("phone");
    		//create entity obj(when edit if come with null create new obj else use data in db)
    		Student s=(sid==null || sid.equals("") ? new Student() : stuService.findById(Integer.parseInt(sid)));
    		
    		s.setName(name);
    		s.setEmail(email);
    		s.setPhone(phone);
    		//insert into db;
    		stuService.save(s);
    		//redirect page
    		resp.sendRedirect(req.getContextPath().concat("/student"));
    		
    	}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if("/student".equals(path)) {
			List<Student> list=stuService.findAll();
			//set to request
			req.setAttribute("student",list);
			//forward
			getServletContext().getRequestDispatcher("/student.jsp").forward(req,resp);
		
		}else if("/student-add".equals(path)||"/student-edit".equals(path)) {
			//get data from db
			List<Student> list=stuService.findAll();
			
			//set data to req
			req.setAttribute("student",list);
			
		 if("/student-edit".equals(path)) {
			//get id from request 
			String sId=req.getParameter("studentid");
			
			//get data from db
			
			Student s=stuService.findById(Integer.parseInt(sId));
			
			//set data to req
			
			req.setAttribute("student", s);
			
		 }
		//forward page
			
			getServletContext().getRequestDispatcher("/student-add.jsp").forward(req, resp);
			
		}else if("/student-delete".equals(path)){
			String id=req.getParameter("studentid");
			
			stuService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath().concat("/student"));
			
	
	}
		
	
	}
}
