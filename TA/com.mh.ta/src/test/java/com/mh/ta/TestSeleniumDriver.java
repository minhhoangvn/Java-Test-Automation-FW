package com.mh.ta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.base.selenium.SeleniumFinderServices;
import com.mh.ta.core.core.element.Element;
import com.mh.ta.core.core.element.FindBy;

public class TestSeleniumDriver {
	@Test
	public void testDriver()  {
		Injector inject = Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				WebDriver driver = new ChromeDriver();
				bind(WebDriver.class).annotatedWith(Names.named("SeleniumDriver")).toInstance(driver);
				bind(SeleniumFinderServices.class).annotatedWith(Names.named("SeleniumFinderService")).toInstance(new SeleniumFinderServices(driver));;
				bind(SeleniumDriver.class);

			}
		});
		SeleniumDriver d = inject.getInstance(SeleniumDriver.class);
		for (Entry<Key<?>, Binding<?>> set : inject.getAllBindings().entrySet()) {
			System.err.println(set.getKey().getClass().getName());
			System.err.println(set.getValue().getClass().getName());
			
		}
		WebDriver driver = inject.getInstance(Key.get(WebDriver.class, Names.named("SeleniumDriver")));
		try {
			
			driver.get("http://toolsqa.com/automation-practice-table/");
			Element elementMain = d.findElement(FindBy.tag("table"));
			List<Element> element = elementMain.findAllElements(FindBy.tag("tr"));
			List<String> text = new ArrayList<>();
			element.forEach(e->text.add(e.getElementText()));
			//element.setText("Test ABC");
			WebElement elementM = driver.findElement(By.tagName("table"));
			List<WebElement> elementL = elementM.findElements(By.tagName("tr"));
			List<String> tt = new ArrayList<>();
			elementL.forEach(e->tt.add(e.getText()));
			System.err.println("List Element Driver         "+ tt);
			System.err.println("List Element SeleniumDriver " + text);
			element.get(0).mosueClick();
			Thread.sleep(5000);
			//Element element = d.findElement(FindBy.id("lst-ib"));
			//element.setText("Test");
		} catch (Exception e) {
			System.err.println(e);
			System.err.println(e.getStackTrace());
			System.err.println(e.getCause());
			driver.quit();
		}
		driver.quit();
		
		// d.getUrl();
		// d.diposeDriver();

	}
}
