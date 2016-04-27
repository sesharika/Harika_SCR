package com.scr.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scr.dao.CourseDAO;
import com.scr.vo.BookVO;
import com.scr.vo.CourseVO;
import com.scr.vo.CurriculumVO;
import com.scr.vo.ScheduleVO;

public class CourseDAOImplTest {
  
	private CourseDAO courseDAOObj = null;
	@BeforeClass
	public void beforeClass(){
		courseDAOObj = new CourseDAOImpl();
	}
	
	 @Test (enabled=false)
	  public void getAllCourses_Test() {
		  List<CourseVO> courseInfoList = courseDAOObj.listAllCourse();
		  
		  System.out.println("=========Displaying All the courses==========");
		  for(CourseVO courseInfo : courseInfoList){
			  System.out.println("CourseID: "+ courseInfo.getCourseId()+"\t CourseName: "+courseInfo.getCourseName()+"\t CourseDesc: "+courseInfo.getCourseDesc()+ "\t CourseAmount: "+ courseInfo.getCourseAmount());  
		  }
		  System.out.println("=========End ==========");
	  }
	 
	 @DataProvider
	  public Object[][] getCourseDetails_dp() {
	    return new Object[][] {
	      new Object[] { 3},
	     // new Object[] { 4},
	    };
	  }
	  
	  @Test(dataProvider="getCourseDetails_dp", enabled=false)
	  public void getCourseDetails_Test(int courseID) {
		  
		  CourseVO courseInfo = courseDAOObj.getCourseDetails(courseID);
		  System.out.println("======Displaying Course Details for Course ID " + courseID + " ==================");
		  System.out.println("Course Name : " + courseInfo.getCourseName());
		  System.out.println("Course Description : " + courseInfo.getCourseDesc());
		  System.out.println("Course Amount : " + courseInfo.getCourseAmount());
		  System.out.println(" " );
		
		  List<ScheduleVO> courseSchedule =  courseInfo.getScheduleList();
		  System.out.println("--Course Schedules--");
		  for(ScheduleVO scVo: courseSchedule){
			  System.out.println("Start Date: "+scVo.getStartDate() +", End Date:  "+ scVo.getEndDate());
			  System.out.println("Start Time: "+scVo.getStartTime() +", End Time:  "+ scVo.getEndTime());
			  System.out.println("Days On which the course is scheduled : ");
			  for(String day: scVo.getDaysOfWeek()){
				  System.out.println(day);
			  }
		  }
		  System.out.println(" " );
		  
		  List<CurriculumVO> courseCurri =  courseInfo.getCurriculumList();
		  System.out.println("--Course Curriculum--");
		  for(CurriculumVO curr: courseCurri){
			  System.out.println("Topic Name : "+curr.getTopicName());
		  }
		  System.out.println(" " );
		  
		  List<BookVO> courseBooks =  courseInfo.getBooksList();
		  System.out.println("--Books Referred--");
		  for(BookVO bookName: courseBooks){
			  System.out.println("Book Name : "+bookName.getBookName());
		  }
		  System.out.println(" " );
		  
	  }
	  

	  
	  @DataProvider
	  public Object[][] addCourse_dp() {
		
		  SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		 
		  //Test Case 1
		  String courseName1 = "First_Test_Course_Name_"+ new Random().nextInt(1000);
		  List<ScheduleVO> courseSchList1 = new ArrayList<ScheduleVO>();
		  courseSchList1.add(new ScheduleVO(2));
		  courseSchList1.add(new ScheduleVO(3));
		
		  List<CurriculumVO> currList1 = new ArrayList<CurriculumVO>();
		  currList1.add(new CurriculumVO(1));
		  currList1.add(new CurriculumVO(2));
		  
		  List<BookVO> bookList1 = new ArrayList<BookVO>();
		  bookList1.add(new BookVO(1));
		  bookList1.add(new BookVO(2));
		
		  CourseVO CourseVo1 = new CourseVO(0, courseName1, 10011, "unitTest added course@"+ sdf.format(new Date()), courseSchList1, bookList1, currList1);
		  
	    return new Object[][] {
	      new Object[] {CourseVo1, "Successfully created course : "+courseName1},
	    };
	    
	  }
	  
	  
	  @Test(dataProvider="addCourse_dp", enabled=false)
	  public void addCourse_Test(CourseVO addCourseVo1, String expectedMessage){
		  
		  CourseVO actualStatusMessage= courseDAOObj.addCourse(addCourseVo1);
		  
		  System.out.println("========================");
		  System.out.println(actualStatusMessage);
		  System.out.println("========================");
	
		Assert.assertEquals(actualStatusMessage, expectedMessage);
	  }
	  
	  @DataProvider
	  public Object[][] disableCourse_dp() {
			int courseId1 = 2;
			int courseId2 = 200000;
	  	
	    return new Object[][] {
	    	//Success case - where courseId 1 exists
	      new Object[] {courseId1, "successfully disabled the course : "+courseId1},
	       //CourseId doesn't exists - no rows updated 
	      new Object[] {courseId2, "No course with courseId : "+courseId2 + " available to disable"},
	    };
	  }
	  
	  @Test(dataProvider="disableCourse_dp", enabled=false)
	  public void disableCourse_Test(int  courseId, String expectedStatusMessage){
		  
		  String actualStatusMessage = courseDAOObj.disableCourse(courseId);
		  System.out.println("========================");
		  System.out.println(actualStatusMessage);
		  System.out.println("========================");
		  Assert.assertEquals(actualStatusMessage, expectedStatusMessage);
	  }
	  
	  @DataProvider
	  public Object[][] updateCourseInfo_dp() {
		  
		  String courseName1 = "First_Test_Course_Name_"+ new Random().nextInt(1000);
		  SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		 
		  CourseVO CourseInfoVo1 = new CourseVO(0, courseName1, 1999, "unitTest updated course info @"+ sdf.format(new Date()));
		  
	    return new Object[][] {
	      new Object[] {1,CourseInfoVo1, "Successfully updated the course details"},
	    //  new Object[] {addCourseVo2, "Unable to create the course due to some issues"}
	    };
	  }
	  
	  
	  @Test(dataProvider="updateCourseInfo_dp", enabled=false)
	  public void updateCourseInfo_Test(int courseID, CourseVO CourseInfoVo1, String expectedMessage){
		  
		  String actualStatusMessage= courseDAOObj.updateCourse(courseID, CourseInfoVo1);
		  System.out.println("========================");
		  System.out.println(actualStatusMessage +" for course "+courseID);
		  System.out.println("========================");
		  Assert.assertEquals(actualStatusMessage, expectedMessage);
	  }
	  
	  @DataProvider
	  public Object[][] updateCourseSchedule_dp() {
		List<ScheduleVO> courseSchList1 = new ArrayList<ScheduleVO>();
		courseSchList1.add(new ScheduleVO(3));
		courseSchList1.add(new ScheduleVO(4));
	    return new Object[][] {
	      new Object[] {67, courseSchList1, "Successfully updated the schedule"},
	    //  new Object[] {addCourseVo2, "Unable to create the course due to some issues"}
	    };
	  }
	  
	  
	  @Test(dataProvider="updateCourseSchedule_dp", enabled=false)
	  public void updateCourseSchedule_Test(int courseID, List<ScheduleVO> courseSchList, String expectedMessage){
		 
//		  String actualStatusMessage= courseDAOObj.updateCourseSchedule(courseID, courseSchList);
//		  System.out.println("========================");
//		  System.out.println(actualStatusMessage+" for course "+courseID);
//		  System.out.println("========================");
//		  Assert.assertEquals(actualStatusMessage, expectedMessage);
	  }
	  
	  @DataProvider
	  public Object[][] updateCourseCurriculum_dp() {
		List<CurriculumVO> courseCurrList1 = new ArrayList<CurriculumVO>();
		courseCurrList1.add(new CurriculumVO(16));
		courseCurrList1.add(new CurriculumVO(15));
	    return new Object[][] {
	      new Object[] {2, courseCurrList1, "Successfully updated the Curriculum"},
	    //  new Object[] {addCourseVo2, "Unable to create the course due to some issues"}
	    };
	  }
	  
	  
	  @Test(dataProvider="updateCourseCurriculum_dp", enabled=false)
	  public void updateCourseCurriculum_Test(int courseID, List<CurriculumVO> courseCurrList, String expectedMessage){
		 
//		  String actualStatusMessage= courseDAOObj.updateCourseCurriculum(courseID, courseCurrList);
//		  System.out.println("========================");
//		  System.out.println(actualStatusMessage+" for course "+courseID);
//		  System.out.println("========================");
//		  Assert.assertEquals(actualStatusMessage, expectedMessage);
	  }
	  
	  @DataProvider
	  public Object[][] updateCourseBook_dp() {
		List<BookVO> courseBookList1 = new ArrayList<BookVO>();
		courseBookList1.add(new BookVO(4));
		courseBookList1.add(new BookVO(5));
	    return new Object[][] {
	      new Object[] {3, courseBookList1, "Successfully updated the books"},
	    //  new Object[] {addCourseVo2, "Unable to create the course due to some issues"}
	    };
	  }
	  
	  
	  @Test(dataProvider="updateCourseBook_dp", enabled=false)
	  public void updateCourseBook_Test(int courseID, List<BookVO> courseSchList, String expectedMessage){
		 
//		  String actualStatusMessage= courseDAOObj.updateCourseBook(courseID, courseSchList);
//		  System.out.println("========================");
//		  System.out.println(actualStatusMessage+" for course "+courseID);
//		  System.out.println("========================");
//		  Assert.assertEquals(actualStatusMessage, expectedMessage);
	  }
	  
}
