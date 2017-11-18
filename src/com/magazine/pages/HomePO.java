package com.magazine.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.magazine.architecture.WebDriverSingleton;

public class HomePO {
	@FindBy(id = "inpHeaderSearch")
	private WebElement inpHeaderSearch;
	@FindBy(id = "btnHeaderSearch")
	private WebElement btnHeaderSearch;
	@FindBy(id = "product_7734857")
	private WebElement product;
	@FindBy(xpath = "//button")
	private WebElement carButton;

	@FindBy(xpath = "//img[@alt='Conjunto de Canto Logan Mesa Com Banco Nogueira/Brownie - At House']")
	private WebElement product01;

	@FindBy(xpath = "//div[@id='root']/div/div[2]/div/div/div/div/div[3]/div[2]/div[2]/button")
	private WebElement goButton;

	@FindBy(xpath = "(//input[@name='login'])[2]")
	private WebElement login;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//div[@id='root']/div/div[2]/div/div/div[2]/div[2]/div/div/form/button")
	private WebElement loginButton;

	@FindBy(xpath = "//div[@id='root']/div/div[2]/div/div/button")
	private WebElement buttonContinue;

	public HomePO acessar() {
		WebDriverSingleton.get().get("https://www.magazineluiza.com/");
		return PageFactory.initElements(WebDriverSingleton.get(), HomePO.class);
	}

	public void moveCursorToSearch() {
		Actions actionAcessFieldSearch = new Actions(WebDriverSingleton.get());
		actionAcessFieldSearch.moveToElement(inpHeaderSearch);
		inpHeaderSearch.click();
	}

	public void fillFieldSearch() {
		inpHeaderSearch.clear();
		inpHeaderSearch.sendKeys("Banco");
	}

	public void clickOnSearchButton() {
		btnHeaderSearch.click();
	}

	public void SelectProduct() {
		product01.click();
	}

	public void AddCar() {
		carButton.click();
	}

	public void GotoLoginPage() {
		goButton.click();
	}

	public void Login() {
		login.clear();
		login.sendKeys("test@yahoo.com.br");
		password.clear();
		password.sendKeys("teste123");
		loginButton.click();
	}

	public void ConfimBuy() {
		buttonContinue.click();

	}
}