"""  
Login Page Class for https://practicetestautomation.com/practice-test-login/  
"""  
from selenium import webdriver
from selenium.webdriver.common.by import By  
from selenium.common.exceptions import NoSuchElementException  
  
  
class BasicTests:  
    def __init__(self, driver: webdriver.Remote):  
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
