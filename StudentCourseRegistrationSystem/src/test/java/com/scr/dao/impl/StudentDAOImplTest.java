package com.scr.dao.impl;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scr.util.Constants;
import com.scr.vo.StudentVO;

public class StudentDAOImplTest {
	private StudentDAOImpl studentDAOObj;


	@BeforeClass
	public void beforeClass() {
		studentDAOObj = new StudentDAOImpl();
	}


	@Test(dataProvider="createStudent_dp", enabled=false)
	public void createStudent(String firstName, String lastName, String emailId, String password,  String expectedMessage) {
		StudentVO studentVO = new StudentVO(0, firstName, lastName, emailId, password,Constants.USER_STUDENT);
		StudentVO actualMessage = studentDAOObj.createStudent(studentVO);
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider
	public Object[][] createStudent_dp() {

		return new Object[][] {
			new Object[] { "Harika", "M" , "mharikaa@gmail.com", "mharika", Constants.FAILURE},
			new Object[] { "vidya", "ks" , "vkss@gmail.com", "aasdh", Constants.FAILURE},	      
			new Object[] { "Pavani", "Baradi" , "pbaradii@gmail.com", "passwordBaradi1", Constants.FAILURE}
		};
	}

	@Test(dataProvider="deleteStudent_dp",enabled=false)
	public void deleteStudent(String emailId, String expectedMessage) {
//		StudentVO studentVO = new StudentVO(0, null, null, emailId, null,null);
//		String actualMessage = studentDAOObj.deleteStudent(studentVO);
//		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider
	public Object[][] deleteStudent_dp() {

		return new Object[][] {
			new Object[] {"vks@gmail.com",Constants.FAILURE},
			new Object[] {"nn@gmail.com",Constants.FAILURE},
			new Object[] {"stomar@gmail.com",Constants.FAILURE}
		};
	}

	@Test(dataProvider="dropCourse_dp",enabled=false)
	public void dropCourse(String emailId, int courseId, int scheduleId, String expectedMessage) {
//		String actualMessage = studentDAOObj.dropCourse(emailId, courseId, scheduleId);
//		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider
	public Object[][] dropCourse_dp() {

		return new Object[][] {
			new Object[] {"vkss@gmail.com",7,12,Constants.SUCCESS},
			new Object[] {"pbaradii@gmail.com",7,13 ,Constants.SUCCESS},
			new Object[] {"stomar@gmail.com",2,1,Constants.FAILURE}
		};
	}

	@Test(dataProvider="enrollCourse_dp", enabled=false)
	public void enrollCourse(String emailId, int courseId, int scheduleId, String expectedMessage) {
//		String actualMessage = studentDAOObj.enrollCourse(emailId, courseId, scheduleId);
//		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider
	public Object[][] enrollCourse_dp() {

		return new Object[][] {
			new Object[] {"vkss@gmail.com",7,12,Constants.SUCCESS},
			new Object[] {"pbaradii@gmail.com",7,13 ,Constants.SUCCESS},
			new Object[] {"stomar@gmail.com",2,1,Constants.FAILURE}
		};
	}


	@Test(dataProvider="getStudentDetails_dp", enabled=false)
	public void getStudentDetails(String email) {
//		StudentVO studentVO = studentDAOObj.getStudentDetails(email);
//		if(studentVO!=null)
//			System.out.println("Student Details "+studentVO.toString());
	}

	@DataProvider
	public Object[][] getStudentDetails_dp() {

		return new Object[][] {
			new Object[] {"vks@gmail.com"},
			new Object[] {"nn@gmail.com"},
			new Object[] {"stomar@gmail.com"}
		};
	}

	@Test(dataProvider="getStudentEnrolledCourses_dp",enabled=false)
	public void getStudentEnrolledCourses(String emailId) {

//		StudentVO studentVO = studentDAOObj.getStudentEnrolledCourses(emailId);
//		if(studentVO!=null){
//			List<CourseVO> courseList = studentVO.getCourseList();
//			if(courseList!=null){
//				for (CourseVO courseVO : courseList) {
//					System.out.println("Course Details "+courseVO.toString());
//				}}
//		}
	}

	@DataProvider
	public Object[][] getStudentEnrolledCourses_dp() {

		return new Object[][] {
			new Object[] {"vkss@gmail.com"},
			new Object[] {"nn@gmail.com"},
			new Object[] {"stomar@gmail.com"}
		};
	}

	@Test(enabled=false)
	public void getStudents() {
		List<StudentVO> studentList = studentDAOObj.getStudents();
		for (StudentVO studentVO : studentList) {
			System.out.println("Student Details "+studentVO.toString());
		}
	}

	@Test(dataProvider="updateStudent_dp",enabled=false)
	public void updateStudent(int studentID, String firstName, String lastName, String emailId, String password, String expectedMessage) {
//		StudentVO studentVO = new StudentVO(studentID, firstName, lastName, emailId, password, null);
//		String actualMessage = studentDAOObj.updateStudent(studentVO);
//		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider
	public Object[][] updateStudent_dp() {

		return new Object[][] {
			new Object[] { 1,"Harika", "M" , "mhaarika@gmail.com", "mharika", Constants.SUCCESS},
			new Object[] { 2,"vidya", "ks" , "vvks@gmail.com", "aasdh", Constants.FAILURE},	      
			new Object[] { 3,"Pavani", "Baradi" , "ppbaradi@gmail.com", "passwordBaradi1", Constants.FAILURE}
		};
	}

	@Test(dataProvider="login_dp",enabled=false)
	public void login(String email, String password){
		StudentVO studentVO = studentDAOObj.login(email, password);
		if(studentVO!=null){
			System.out.println("Login successful. Student Details are "+studentVO.toString());
		}
	}

	@DataProvider
	public Object[][] login_dp() {

		return new Object[][] {
			new Object[] { "mharikaa@gmail.com", "mharika"},
			new Object[] { "vkss@gmail.com", "aasdh"},	      
			new Object[] { "pbaradii@gmail.com", "passwordBaradi1"}
		};
	}

}
