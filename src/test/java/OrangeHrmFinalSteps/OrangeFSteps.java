package OrangeHrmFinalSteps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import BaseLayer.BaseClass;
import PageLayer.LoginPage;
import PageLayer.PimPage;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.datatable.DataTable;

public class OrangeFSteps extends BaseClass {
	public static LoginPage loginPage;
	public static PimPage pimPage;
	public static String eid;

	@Given("user enter the {string} browser and open the Login Url")
	public void user_enter_the_browser_and_open_the_login_url(String browserName) {

		BaseClass.initialisation(browserName);
	}

	@When("user enter valid credentials and click on login button")
	public void user_enter_valid_credentials_and_click_on_login_button() {

		loginPage = new LoginPage();
		loginPage.loginFunctionality("Admin", "admin123");
	}

	@Then("user validate title")
	public void user_validate_title() {

		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "OrangeHRM");
	}

	@Then("user validate url")
	public void user_validate_url() {

		String actualUrl = driver.getCurrentUrl();
		boolean url = actualUrl.contains("orangehrmlive");
		Assert.assertEquals(url, true);
	}

	@When("user click on pim link")
	public void user_click_on_pim_link() {
		pimPage = new PimPage();
		pimPage.clickOnPimLink();
	}

	@Then("user validate the url using url")
	public void user_validate_the_url_using_url(DataTable dataTable) {
//		| pim |
		List<List<String>> ls = dataTable.asLists();
		String expectedUrl = ls.get(0).get(0);
		String actualUrl = driver.getCurrentUrl();
		boolean url = actualUrl.contains(expectedUrl);
		Assert.assertEquals(url, true);
	}

	@Then("user click on the Add Employee link and enter {string},{string} and click on save button")
	public void user_click_on_the_add_employee_link_and_enter_and_click_on_save_button(String firstname,
			String lastname) {
		pimPage.addData(firstname, lastname);

	}

	@Then("capture the Employee id and click on Employee list")
	public void capture_the_employee_id_and_click_on_employee_list() throws Exception {
		Thread.sleep(5000);
		eid = pimPage.captureIDAndClickOnEmployeeList();
		Thread.sleep(5000);
	}

	@Then("enter employee id in employee id textbox and click on search button")
	public void enter_employee_id_in_employee_id_textbox_and_click_on_search_button() {
		pimPage.clickOnSearch(eid);
	}

	@Then("select the search employee and click on delete")
	public void select_the_search_employee_and_click_on_delete() throws Exception {
		pimPage.checkElementAndDelete();
		Thread.sleep(5000);
	}

	@Then("validate the user deleted or not")
	public void validate_the_user_deleted_or_not() {
		String msg = pimPage.checkDeletedOrNot(eid);
		Assert.assertEquals(msg, "No Records Found");
	}

	@AfterStep
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {

			byte[] f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());

			scenario.attach(f, "image/png" + date, scenario.getName());
		} else {
			byte[] f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());

			scenario.attach(f, "image/png" + date, scenario.getName());
		}

	}

}
