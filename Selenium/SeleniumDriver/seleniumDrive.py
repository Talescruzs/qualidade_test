from selenium import webdriver
from selenium.webdriver.common.by import By  
from selenium.common.exceptions import NoSuchElementException  

from SeleniumDriver.mainSelenium import MainSelenium
  
  
class BasicTests(MainSelenium):  
    def __init__(self, driver: webdriver.Remote):
        super(BasicTests, self).__init__(driver)
        self.driver = driver
  
    def open_page(self, url):  
        self.driver.get(url)  
    
    def get_title(self):  
        return self.driver.title
  
    def open_menu(self):  
        self.driver.find_element("css selector", "button[data-js-item='js-skip-links-navvuebutton']").click()
   
    def change_language(self, language: str):  
        try:  
            xpath = f"//ul[contains(@class, 'ml-stack-nav__menu')]//a[contains(@href, '{language}')]"
            self.driver.find_element(By.XPATH, xpath).click()
        except NoSuchElementException:  
            assert False, f"Language option '{language}' not found."
