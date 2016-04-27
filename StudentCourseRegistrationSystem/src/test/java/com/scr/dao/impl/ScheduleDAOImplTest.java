package com.scr.dao.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scr.dao.ScheduleDAO;
import com.scr.util.Constants;
import com.scr.vo.ScheduleVO;

public class ScheduleDAOImplTest {
	ScheduleDAO scheduleDAO = null;

	@BeforeClass
	public void beforeClass(){
	 scheduleDAO = new ScheduleDAOImpl();
	}

  @Test(dataProvider="addCourseSchedule_dp",enabled = false)
  public void addCourseSchedule(int courseId, List<ScheduleVO> scheduleVOs, String expectedMessage ) {
//    ScheduleVO actualMessage = scheduleDAO.addCourseSchedule(courseId, scheduleVOs);
//    Assert.assertEquals(actualMessage, expectedMessage);
  }
  
  @DataProvider
  public Object[][] addCourseSchedule_dp() {
	  List<ScheduleVO> scheduleVOs = new ArrayList<ScheduleVO>();
	  ScheduleVO scheduleVO = null;
	  for (int i = 1; i < 4; i++) {
		  scheduleVO = new ScheduleVO();
		  scheduleVO.setScheduleId(i);
		scheduleVOs.add(scheduleVO);
	}
    return new Object[][] {
      new Object[] { 1, scheduleVOs,Constants.SUCCESS},
    };
  }

  @Test(dataProvider="addSchedule_dp",enabled=false)
  public void addSchedule(ScheduleVO scheduleVO, String expectedMessage) {
    ScheduleVO actualMessage = scheduleDAO.addSchedule(scheduleVO);
    Assert.assertEquals(actualMessage, expectedMessage);
  }
  
  @DataProvider
  public Object[][] addSchedule_dp() {
    return new Object[][] {
    	new Object[] { new ScheduleVO(convertUtilDateToSqlDate(new Date("2013/11/20")), convertUtilDateToSqlDate(new Date("2015/11/20")), new Time(14, 0, 0), new Time(15, 0, 0),new String[]{"Monday", "Wednesday", "Friday"}), Constants.SUCCESS},
	      new Object[] { new ScheduleVO(convertUtilDateToSqlDate(new Date("2013/12/20")), convertUtilDateToSqlDate(new Date("2015/11/20")), new Time(14, 0, 0), new Time(15, 0, 0),new String[]{"Monday", "Wednesday", "Friday"}), Constants.SUCCESS},
	      new Object[] { new ScheduleVO(convertUtilDateToSqlDate(new Date("2013/9/20")), convertUtilDateToSqlDate(new Date("2015/11/20")), new Time(14, 0, 0), new Time(15, 0, 0),new String[]{"Monday", "Wednesday", "Friday"}), Constants.SUCCESS}
    };
  }

  @Test(dataProvider="deleteSchedule_dp",enabled=false)
  public void deleteSchedule(ScheduleVO scheduleVO, String expectedMessage) {
//    String actualMessage = scheduleDAO.deleteSchedule(scheduleVO);
//    Assert.assertEquals(actualMessage, expectedMessage);
  }
  
  @DataProvider
  public Object[][] deleteSchedule_dp() {
	  ScheduleVO scheduleVO = new ScheduleVO();
	  scheduleVO.setScheduleId(2);
    return new Object[][] {
      new Object[] { scheduleVO,Constants.FAILURE},
    };
  }

  @Test(enabled=false)
  public void getAllSchedule() {
//    List<ScheduleVO> scheduleVOs = scheduleDAO.getAllSchedule();
//    for (ScheduleVO scheduleVO : scheduleVOs) {
//		System.out.println(" Schedule Vo "+scheduleVO.toString());
//	}
  }

  @Test(dataProvider="getCourseSchedule_dp",enabled=false)
  public void getCourseSchedule(int courseId) {
//    List<ScheduleVO> scheduleVOs = scheduleDAO.getCourseSchedule(courseId);
//    for (ScheduleVO scheduleVO : scheduleVOs) {
//		System.out.println("Course Schedule "+scheduleVO.toString());
//	}
  }
  
  @DataProvider
  public Object[][] getCourseSchedule_dp() {
	  
    return new Object[][] {
      new Object[] { 1},
      new Object[] { 2},
      new Object[] { 6}
    };
  }

  @Test(dataProvider="updateSchedule_dp",enabled=false)
  public void updateSchedule(ScheduleVO scheduleVo, String expectedMessage) {
//    String actualMessage = scheduleDAO.updateSchedule(scheduleVo);
//    Assert.assertEquals(actualMessage, expectedMessage);
  }
  
  @DataProvider
  public Object[][] updateSchedule_dp() {
	  
	  return new Object[][] {
	      new Object[] { new ScheduleVO(11,convertUtilDateToSqlDate(new Date("2013/11/20")), convertUtilDateToSqlDate(new Date("2015/11/20")), new Time(14, 0, 0), new Time(15, 0, 0),new String[]{"Monday", "Wednesday", "Friday"}), Constants.SUCCESS},
	      new Object[] { new ScheduleVO(12,convertUtilDateToSqlDate(new Date("2013/12/20")), convertUtilDateToSqlDate(new Date("2015/11/20")), new Time(14, 0, 0), new Time(15, 0, 0),new String[]{"Monday", "Wednesday", "Friday"}), Constants.SUCCESS},
	      new Object[] { new ScheduleVO(13,convertUtilDateToSqlDate(new Date("2013/9/20")), convertUtilDateToSqlDate(new Date("2015/11/20")), new Time(14, 0, 0), new Time(15, 0, 0),new String[]{"Monday", "Wednesday", "Friday"}), Constants.SUCCESS}
	    };
  }
  
  public java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = (java.sql.Date)new java.sql.Date(date.getTime());
		return sqlDate;
	}
}
