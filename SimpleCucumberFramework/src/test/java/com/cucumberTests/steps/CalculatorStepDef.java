package com.cucumberTests.steps;

import java.util.List;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorStepDef {
	int a,b,res;
	int exp=102;
	
	@Given("a and b")
	public void a_and_b() {
	    a = 100;
	    b = 2;
	}

	@When("i add a and b")
	public void i_add_a_and_b() {
	     res = a + b;
	}

	@Then("result should be sum of two numbers")
	public void result_should_be_sum_of_two_numbers() {
	    Assert.assertEquals(exp, res);
	}
	
	@Given("a as {int} and b as {int}")
	public void a_as_and_b_as(Integer int1, Integer int2) {
	    a = int1;
	    b = int2;
	}
	@When("i subtract a and b")
	public void i_subtract_a_and_b() {
	    res = a - b;
	}
	@Then("result should be {int}")
	public void result_should_be(Integer exp) {
	    Assert.assertEquals(exp, res);
	}
	
	@When("i Multiple a and b")
	public void i_multiple_a_and_b() {
	    res = a * b;
	}

	@Then("result should be Multiplication of two numbers")
	public void result_should_be_multiplication_of_two_numbers() {
		int exp = 200;
	   Assert.assertEquals(exp, res);
	}
	
	@Given("a and b and c and d")
	public void a_and_b_and_c_and_d(DataTable dataTable) {
	    List<List<String>> data = dataTable.asLists();
	    System.out.print(data.get(0).get(0));
	    System.out.print(data.get(0).get(1));
	    System.out.print(data.get(1).get(0));
	    System.out.print(data.get(1).get(1));
	}	


}
