package org.apache.jmeter.control.model;

import org.apache.jmeter.control.CriticalSectionController;
import org.apache.jmeter.control.ForeachController;
import org.apache.jmeter.control.IfController;
import org.apache.jmeter.control.IncludeController;
import org.junit.Test;

import org.junit.Assert;

public class ControllerModelTest {
	
	@Test
	public void testForEach() {
		ForeachControllerInitializer initer = new ForeachControllerInitializer();
		ForeachController ele = (ForeachController) initer.initilizeElement();
		
		Assert.assertTrue("ForEach Controller".equals(ele.getName()));	
		Assert.assertFalse(ele.isDone());
		Assert.assertTrue("".equals(ele.getStartIndexAsString()));
		Assert.assertTrue("".equals(ele.getEndIndexAsString()));
		Assert.assertTrue("".equals(ele.getReturnValString()));
		Assert.assertTrue("".equals(ele.getInputValString()));
		Assert.assertTrue(ele.getUseSeparator());
	}
	
	
	@Test
	public void testCriticalSection() {
		CriticalSectionControllerInitializer initer = new CriticalSectionControllerInitializer();
		CriticalSectionController ele = (CriticalSectionController) initer.initilizeElement();
		
		Assert.assertTrue("Critical Section Controller".equals(ele.getName()));	
		Assert.assertTrue("global_lock".equals(ele.getLockName()));
	}
	
	@Test
	public void testIfController() {
		IfControllerInitializer initer = new IfControllerInitializer();
		IfController ele = (IfController) initer.initilizeElement();
		
		Assert.assertTrue("If Controller".equals(ele.getName()));	
		Assert.assertTrue("".equals(ele.getCondition()));
		Assert.assertFalse(ele.isEvaluateAll());
	}
	
	@Test
	public void testIncludeController() {
		IncludeControllerInitializer initer = new IncludeControllerInitializer();
		IncludeController ele = (IncludeController) initer.initilizeElement();
		
		Assert.assertTrue("Include Controller".equals(ele.getName()));	
		Assert.assertTrue("".equals(ele.getIncludePath()));		
	}
	
}

