/**
 * 
 */
package com.scr.client;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.scr.dao.CourseDAO;
import com.scr.dao.StudentsDAO;
import com.scr.dao.impl.CourseDAOImpl;
import com.scr.dao.impl.StudentDAOImpl;
import com.scr.util.Constants;
import com.scr.vo.CourseVO;
import com.scr.vo.StudentVO;

public class SCRMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("========================== Welcome to Student Course Registratin System =======================");
		while (true) {
			System.out.println("\n Do you want to list the courses we are offering?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getCourses();
				break;

			case "n":	
				listDateRangeCourses();
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}		
	}

	private static void listDateRangeCourses(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to list the courses which are between a date range?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getDateRangeCourseList();
				break;

			case "n":	
				listAmountRangeCourses();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}	
	}

	private static void listAmountRangeCourses(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to list the courses which are between certain amount range?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getAmountRangeCourseList();
				break;

			case "n":	
				listDayRangeCourses();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}	
	}

	private static void listDayRangeCourses(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to list the courses which are on a partitular day?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getDayRangeCourseList();
				break;

			case "n":	
				listTimeRangeCourses();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}	
	}

	private static void listTimeRangeCourses(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to list the courses which are between a time range?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getTimeRangeCourseList();
				break;

			case "n":	
				listCourseDetails();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}	
	}

	private static void listCourseDetails(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to view a particular course details?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getCoursesDetails();
				break;

			case "n":	
				register();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				listTimeRangeCourses();
				break;
			}

		}	
	}

	private static void register(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to list the courses for which you have enrolled?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":
				getEnrolledCourseDetails();
				break;
				
			case "n":	
				signup();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}	
	}

	private static void signup(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to register?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				addStudent();
				break;

			case "n":	
				enroll();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;

			}

			if (choice.equals("")) {
				break;
			}

		}	
	}


	private static void enroll(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Enroll for a course?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				enrollForCourse();
				break;

			case "n":	
				listStudentCourseDetails();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;
			}

			if (choice.equals("")) {
				break;
			}

		}	
	}
	private static void listStudentCourseDetails(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n Do you want to list the courses for which you have enrolled?[y/n]");
			System.out.println("Enter blank to exit");
			String choice = scanner.nextLine();
			switch (choice) {
			case "y":	
				getEnrolledCourseDetails();
				break;

			case "n":	
				listCourseDetails();
				break;

			default:	
				System.out.println("Please enter [y/n] or blank");
				break;
			}

			if (choice.equals("")) {
				break;
			}

		}	
	}

	private static void getCourses(){
		CourseDAO courseDAO = new CourseDAOImpl();
		List<CourseVO> courseVOs = courseDAO.listAllCourse();
		for (CourseVO courseVO : courseVOs) {
			//courseVO.printCourseList(courseVO);
		}
	}

	private static void enrollForCourse(){
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.println("Please enter your emailid");
//		String email = scanner.nextLine();
//		System.out.println("Please enter the course id for which you want to enroll");
//		int courseId = scanner.nextInt();
//		System.out.println("Please enter the schedule id for which you want to enroll");
//		int scheduleId = scanner.nextInt();
//		StudentsDAO studentDAO = new StudentDAOImpl();
//		String result = studentDAO.enrollCourse(email, courseId, scheduleId);
//		if(result.equals(Constants.SUCCESS)){
//			System.out.println("Student successfully enrolled for course "+courseId);
//		}else{
//			System.out.println("Cannot enroll student for this course "+courseId);
//		}
	}

	

	private static void getEnrolledCourseDetails(){
//		StudentsDAO studentDAO = new StudentDAOImpl();
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("\n Enter your email id to get enrolled courses");
//		System.out.println("Email id: ");
//		String emailid = scanner.nextLine();
//		StudentVO studentVO = studentDAO.getStudentEnrolledCourses(emailid);
//		if(studentVO!=null){
//			List<CourseVO> courseVOs = studentVO.getCourseList();
//			for (CourseVO courseVO : courseVOs) {
//				//courseVO.printCourseList(courseVO);
//			}
//		}
	}

	private static void getCoursesDetails() {
		CourseDAO courseDAO = new CourseDAOImpl();		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Enter course id to get Course Details for a particular course");
		System.out.println("Course id : ");
		int courseId = scanner.nextInt();
		CourseVO courseVO = courseDAO.getCourseDetails(courseId);
		if(courseVO!=null){
		//courseVO.printCourseList(courseVO);
		}else{
			System.out.println("No course found with course id "+courseId);
		}
	}

	private static void addStudent() {
		int studentId;
		String firstName = "";
		String lastName = "";
		String emailId = "";
		String password = "";

		StudentsDAO studentDAO = new StudentDAOImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Enter student information to add");

		System.out.println("FirstName : ");
		firstName = scanner.nextLine();
		System.out.println("LastName : ");
		lastName = scanner.nextLine();
		System.out.println("EmailId : ");
		emailId = scanner.nextLine();
		System.out.println("Password : ");
		password = scanner.nextLine();

		StudentVO studentVO = new StudentVO(firstName, lastName, emailId, password, null);
//		String result = studentDAO.createStudent(studentVO);
//		if(result.equals(Constants.SUCCESS)){
//			System.out.println("Student successfully added");
//		}
	}

	private static void getDateRangeCourseList(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Enter start Date in YYYY/MM/DD ");
		String startDate =  scanner.nextLine();
		System.out.println("Enter End Date in YYYY/MM/DD ");
		String endDate = scanner.nextLine();
		CourseDAO courseDAO = new CourseDAOImpl();
		List<CourseVO> courseVOs = courseDAO.getDateRangeCourseList(startDate,endDate);
		if(courseVOs!=null && courseVOs.size()>0){
			System.out.println("");
			System.out.println("Please find below Course Details");
			for (CourseVO courseVO : courseVOs) {
				//courseVO.printCourseList(courseVO);
			}
		}else{
			System.out.println("Sorry!! No course exist between the dates you specified");
		}
	}

	private static void getAmountRangeCourseList(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Enter amount starting from ");
		int startAmount =  scanner.nextInt();
		System.out.println("until amount ");
		int endAmount =  scanner.nextInt();
		CourseDAO courseDAO = new CourseDAOImpl();
		List<CourseVO> courseVOs = courseDAO.getAmountRangeCourseList(startAmount,endAmount);;
		
		if(courseVOs!=null && courseVOs.size() > 0){
			System.out.println("Please find below Course Details");
			for (CourseVO courseVO : courseVOs) {
				//courseVO.printCourseList(courseVO);
			}
		}else{
			System.out.println("Sorry!! No course exist between the amount you specified");
		}
	}

	private static void getTimeRangeCourseList(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Enter start Time ");
		System.out.println("Enter Hours (24 hour format) ");
		int startHours =  scanner.nextInt();
		System.out.println("Enter minutes ");
		int startMinutes =  scanner.nextInt();
		System.out.println("Enter seconds ");
		int startSeconds =  scanner.nextInt();

		System.out.println("Enter end Time ");
		System.out.println("Enter Hours (24 hour format) ");
		int endHours =  scanner.nextInt();
		System.out.println("Enter minutes ");
		int endMinutes =  scanner.nextInt();
		System.out.println("Enter seconds ");
		int endSeconds =  scanner.nextInt();

		CourseDAO courseDAO = new CourseDAOImpl();
		List<CourseVO> courseVOs = courseDAO.getTimeRangeCourseList(new Time(startHours,startMinutes,startSeconds),new Time(endHours,endMinutes,endSeconds));;
		
		if(courseVOs!=null && courseVOs.size() > 0){
			System.out.println("Please find below Course Details");
			for (CourseVO courseVO : courseVOs) {
				//courseVO.printCourseList(courseVO);
			}
		}else{
			System.out.println("Sorry!! No course exist between the time you specified");
		}
	}

	private static void getDayRangeCourseList(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Enter Days seperated with comma ");
		String days =  scanner.nextLine();
		String delimiter = "[,]";
		String[] daysOfWeek = days.split(delimiter);
		CourseDAO courseDAO = new CourseDAOImpl();
		List<CourseVO> courseVOs = courseDAO.getDayRangeCourseList(daysOfWeek);
		if(courseVOs!=null && courseVOs.size() > 0){
			System.out.println("Please find below Course Details");
			for (CourseVO courseVO : courseVOs) {
				//courseVO.printCourseList(courseVO);
			}
		}else{
			System.out.println("Sorry!! No course exist for the day you specified");
		}
	}

}
