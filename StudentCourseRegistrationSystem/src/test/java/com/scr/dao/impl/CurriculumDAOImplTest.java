package com.scr.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scr.vo.CurriculumVO;

public class CurriculumDAOImplTest {
	
	
	private CurriculumDAOImpl  curriculumDAOObj = null;
	@BeforeClass
	public void beforeClass(){
		  curriculumDAOObj = new CurriculumDAOImpl();
		  //System.out.println("BaseClass::@BeforeClass");
	}
	
	//Test Case for getAllCurriculum
	@Test (enabled=false)
	  public void getAllCurriculum_Test() throws SQLException, IOException {
		 
		  List<CurriculumVO> AllCurriculum = curriculumDAOObj.getAllCurriculum();
		  
		  for(CurriculumVO curriculumVO : AllCurriculum){
			  System.out.println("CurriculumID : " +curriculumVO.getCurriculumId()+ "Topic Name " +curriculumVO.getTopicName());
		  }
	  }

	//Test Case for getAllCgetCourseCurriculum
	@DataProvider (name="getCourseCurriculum")
	  public Object[][] getCourseCurriculum_dp() {
	    return new Object[][] {
	      new Object[] { 1},
	    };
	  }
	
	@Test (dataProvider="getCourseCurriculum",enabled=false)
	public void getCourseCurriculum_Test(int courseID)
	{			  
		  List<CurriculumVO> courseCurriculum = curriculumDAOObj.getCourseCurriculum(courseID);
		  
		  for(CurriculumVO curriculumVO : courseCurriculum){
			  System.out.println("TOpic Name" +curriculumVO.getTopicName());
		  }
	  }
	

	//Test Case for addCurriculum
	@DataProvider(name="addCurriculum")
	  public Object[][] addCurriculum_dp() {
	    return new Object[][] {
	      new Object[] {new CurriculumVO("Exceptions1"),"success"},     
	    };
	  }
	
	@Test (dataProvider="addCurriculum",enabled=false)
	public void addCurriculum_Test(CurriculumVO cvo,String expectedStatusMessage)
	{			  
		CurriculumVO actualStatusMessage;
			actualStatusMessage = curriculumDAOObj.addCurriculum(cvo);
		Assert.assertEquals(actualStatusMessage, expectedStatusMessage);
	  }
	  
	//Test Case for addCourseCurriculum
	@DataProvider(name="addCourseCurriculum")
	  public Object[][] addCourseCurriculum_dp() {
		//List<CurriculumVO> curriculumList= null;
		ArrayList<CurriculumVO> curriculumList = new ArrayList<CurriculumVO>();
		CurriculumVO cvo = new CurriculumVO (4,"JAVA");
		CurriculumVO cvo1 = new CurriculumVO (5,"OOPS");
		curriculumList.add(cvo);
		curriculumList.add(cvo1);
	    return new Object[][] {
	      new Object[] {1,curriculumList,"success"},     
	    };
	  }
	
	@Test (dataProvider="addCourseCurriculum",enabled=false)
	public void addCourseCurriculum_Test(int courseId, List<CurriculumVO> curriculumList,String expectedStatusMessage)
	{		
		System.out.println("courseId: "+ courseId);
		for(CurriculumVO curriculumVO : curriculumList){
			
			System.out.println("CurriculumId: "+ curriculumVO.getCurriculumId());
			
		}
		
		String actualStatusMessage="";
			actualStatusMessage = curriculumDAOObj.addCourseCurriculum(courseId,curriculumList);
		Assert.assertEquals(actualStatusMessage, expectedStatusMessage);
	  }
	
	
	
	 
	//Test Case for updateCurriculum
	@DataProvider(name="updateCurriculum")
	public Object[][] updateCurriculum_dp() {
	return new Object[][] {
	new Object[] { new CurriculumVO(4,"MapFunctions"), "success"}

	};
}
	
	@Test (dataProvider="updateCurriculum",enabled=false)	
	public void updateCurriculum_Test(CurriculumVO cvo,String expectedMessage)
	{			  
		String actualStatusMessage="";
		actualStatusMessage = curriculumDAOObj.updateCurriculum(cvo);
		Assert.assertEquals(actualStatusMessage, expectedMessage);
	  }
	  
	
	//Test Case for deleteCurriculum
	@DataProvider(name="deleteCurriculum")
	public Object[][] deleteCurriculum_dp() {
	return new Object[][] {
	new Object[] { 6,"success"}

	};
}

	@Test (dataProvider="deleteCurriculum",enabled=false)	
	public void deleteCurriculum_Test(int curriculumId,String expectedMessage)
	{	
		String actualStatusMessage="";
		actualStatusMessage = curriculumDAOObj.deleteCurriculum(curriculumId);
		Assert.assertEquals(actualStatusMessage,expectedMessage);
	  }

}
