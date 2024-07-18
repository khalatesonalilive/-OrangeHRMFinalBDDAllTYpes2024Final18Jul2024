package PageLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseLayer.BaseClass;
import UtilityLayer.Wait;

public class PimPage extends BaseClass {

	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[2]")
	private WebElement pimLink;

	@FindBy(xpath = "//a[text()='Add Employee']")
	private WebElement addEmployeeButton;

	@FindBy(name = "firstName")
	private WebElement fname;

	@FindBy(name = "lastName")
	private WebElement lname;

	@FindBy(xpath = "//button[text()=' Save ']")
	private WebElement saveButton;

	@FindBy(xpath = "//a[text()='Employee List']")
	private WebElement employeeListButton;

	@FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
	private WebElement employeeId;

	@FindBy(xpath = "//button[text()=' Search ']")
	private WebElement searchButton;

	@FindBy(xpath = "(//i[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[2]")
	private WebElement checkElement;

	@FindBy(xpath = "//button[text()=' Delete Selected ']")
	private WebElement deleteSelectedMsg;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	private WebElement confirmDelete;

	@FindBy(xpath = "//span[text()='No Records Found']")
	private WebElement noRecordsFound;

	public PimPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnPimLink() {
		Wait.click(pimLink);
	}

	public void addData(String fname1, String lname1) {

		Wait.click(addEmployeeButton);
		Wait.sendKeys(fname, fname1);
		Wait.sendKeys(lname, lname1);
		Wait.click(saveButton);
	}

	public String captureIDAndClickOnEmployeeList() {
		String eid = Wait.getAttribute(employeeId, "value");
		Wait.click(employeeListButton);
		return eid;
	}

	public void clickOnSearch(String eid) {
		Wait.sendKeys(employeeId, eid);
		Wait.click(searchButton);
	}

	public void checkElementAndDelete() throws Exception {
		Thread.sleep(5000);
		Wait.click(checkElement);
		Wait.click(deleteSelectedMsg);
		Wait.click(confirmDelete);
	}

	public String checkDeletedOrNot(String eid)

	{
		Wait.sendKeys(employeeId, eid);
		Wait.click(searchButton);
		String msg = Wait.getText(noRecordsFound);
		return msg;

	}

}
