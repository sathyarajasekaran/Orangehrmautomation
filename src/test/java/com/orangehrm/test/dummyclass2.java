package com.orangehrm.test;
import com.orangehrm.base.BaseClass;
import org.testng.annotations.Test;

public class dummyclass2 extends BaseClass{

    @Test
    public void dummyTest2(){
        String Title = driver.getTitle();
        assert Title .equals("OrangeHRM"):"Test failed-Title is not matching";
        System.out.println("Test Passed: "+Title);
    }
}
