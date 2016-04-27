package com.scr.dao.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class RunTests {

	public static void main(String[] args) {
		
		//Instructions
		String className ="";
		String methodsStr = "";
		if(args.length == 0){
			System.out.println("=========Instructions to run=============");
			System.out.println("This program takes two command line parameters:");
			System.out.println("Param 1 is TestClassName");
			System.out.println("Param 2 is MethodName. Can be comma separated list of methods. For eg: <method1,method2,..>");
			System.out.println("Sample Commands : <CourseDAOImplTest> <getAllCourses_Test,getCourseDetails_Test>" );
			System.out.println("Below are the list of TestClasses and their corresponding methods");
			System.out.println("==========================================");
			
			Map<String, String> displayMap = new HashMap<String, String>();
			//displayMap.put("CourseDAOImplTest", "test.com.scr.dao.impl.CourseDAOImplTest");
			//displayMap.put("BooksDAOImplTest", "test.com.scr.dao.impl.BooksDAOImplTest");
			displayMap.put("StudentDAOImplTest", "com.scr.dao.impl.StudentDAOImplTest");
			//displayMap.put("BooksDAOImplTest", "com.scr.dao.impl.BooksDAOImplTest");
			
			for (String key : displayMap.keySet()){
				getMethodList(key, displayMap.get(key));
				System.out.println(" ");
			}
			return;
		}else{
			className = args[0];
			methodsStr = args[1];
		}
	
		String testClassName = "test.com.scr.dao.impl."+className;
		
		List<String> methodList = new ArrayList<String>();
		StringTokenizer str = new StringTokenizer(methodsStr.trim(), ",");
		while(str.hasMoreTokens()){
			methodList.add(str.nextToken().trim());
		}
	
	
		List<XmlInclude> includeList = buildMethodList(methodList);
		List<XmlClass> classesList=null;
		try {
			classesList = setTestClassAndMethod(testClassName, includeList);
			buildAndLaunchTestSuite(classesList);
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to locate the TestClassName. Please verify");
		}
		
	}

/**
 * Adds the list of testMethods to run
 * @param methodList
 * @return
 */
	private static List<XmlInclude> buildMethodList(List<String> methodList) {
		List<XmlInclude> includeList = new ArrayList<XmlInclude>();
		for(String methodtoInclude: methodList){
			includeList.add(new XmlInclude(methodtoInclude));
		}
		return includeList;
	}
  /**
   * setting test class and methods to be run
   * @param testClassName
   * @param includeList
   * @return
   * @throws ClassNotFoundException
   */

	private static List<XmlClass> setTestClassAndMethod(String testClassName, List<XmlInclude> includeList) throws ClassNotFoundException {
		
		XmlClass xmlClassObj = new XmlClass();
		Class<?> className = Class.forName(testClassName);
		xmlClassObj.setClass(className);
		xmlClassObj.setIncludedMethods(includeList);
		
		List<XmlClass> classesList = new ArrayList<XmlClass>(Arrays.asList(xmlClassObj));;
		
		return classesList;
	}

/**
 * Builds and runs the test suite
 * @param classesList - list of classes
 */
	private static void buildAndLaunchTestSuite(List<XmlClass> classesList) {
		
		XmlSuite suite = new XmlSuite();
		XmlTest test = new XmlTest(suite);
		test.setXmlClasses(classesList);
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>(Arrays.asList(suite));
		
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
	}

	/**
	 * 
	 * @param classDisplayName - for display purpose only
	 * @param className - whose methods to be displayed
	 */
    private static void getMethodList(String classDisplayName, String className) {
    	Class cls;
		try {
			cls = Class.forName(className);
	    	List<Method> methodList = getMethodsAnnotatedWith(cls,org.testng.annotations.Test.class);
	    	for (Method m:methodList) {
	    		System.out.println(classDisplayName+" "+m.getName());
	    	}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
	private static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        while (klass != Object.class) {
            final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));       
            for (final Method method : allMethods) {
                if (annotation == null || method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }
            klass = klass.getSuperclass();
        }
        return methods;
    }//End methods display
	
}
