package com.orangehrm.test;
import com.orangehrm.base.BaseClass;
import org.testng.annotations.Test;

import javax.swing.*;

public class dummyclass extends BaseClass{

    @Test
    public void dummyTest(){
        String Title = driver.getTitle();
        assert Title .equals("OrangeHRM"):"Test failed-Title is not matching";
        System.out.println("Test Passed: "+Title);
    }
}
