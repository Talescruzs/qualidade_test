from selenium import webdriver
from selenium.webdriver.common.by import By  
from selenium.common.exceptions import NoSuchElementException  

from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from SeleniumDriver.mainSelenium import MainSelenium
# from mainSelenium import MainSelenium

import time
  
  
class SearchSelenium(MainSelenium):  
    def __init__(self, driver: webdriver.Remote):
        super(SearchSelenium, self).__init__(driver)
        self.driver = driver
    
    def get_search_bar_text(self) -> str:
        try:
            search_bar = self.driver.find_element(By.XPATH, "/html/body/div[3]/div/div/div/div/form/div[2]/input")
            return search_bar.get_attribute("value")
        except NoSuchElementException:
            print("Search bar not found.")
            return ""

    def get_search_results(self) -> list:
        returns = []
        results = self.driver.find_elements(By.XPATH, "/html/body/main/div[1]/div/div/div[3]/div/div/div/div/div[5]/div[2]/div/div/div[1]")
        for result in results:
            element = dict()
            element["title"] = result.find_element(By.XPATH, "./div[1]//a").text
            element["type"] = result.find_element(By.XPATH, "./div[1]/div[3]/div/div[contains(@class, 'gs-fileFormat')]/span[2]").text
            element["text"] = result.find_element(By.XPATH, "./div[1]/div[3]/div/div[contains(@class, 'gs-bidi-start-align gs-snippet')]").text
            returns.append(element)
        print(results) 
        return returns
    

    def click_search(self):  
        self.driver.find_element(By.XPATH, "/html/body/header/div/div/div/div/nav/ul/li[5]/button").click()
        self.driver.implicitly_wait(10)
    
    def click_option(self, option: str):
        try:
            element = self.driver.find_element(By.XPATH, f"/html/body/div[3]/div/div/div/div/form/div[1]/div/div/ul/li[{option}]/label")
            element.click()
        except NoSuchElementException:
            print(f"Option '{option}' not found.")

    def click_search_button(self):
        search_button = self.driver.find_element(By.XPATH, "/html/body/div[3]/div/div/div/div/form/div[2]/div/button")
        search_button.click()
        self.driver.implicitly_wait(10)
        
    def check_option_is_selected(self, option: str) -> bool:
        try:
            element = self.driver.find_element(By.XPATH, f"//*[@id='gsearch-{option}']")
            print("aaaaaaa")
            return element.is_selected()
        except:
            print(f"Option '{option}' not found.")
            return False

    def write_on_search_bar(self, text: str):
        try:
            search_bar = self.driver.find_element(By.XPATH, "/html/body/div[3]/div/div/div/div/form/div[2]/input")
            search_bar.send_keys(text)
        except NoSuchElementException:
            print("Search bar not found.")
    
    def clean_search_bar(self):
        try:
            search_bar = self.driver.find_element(By.XPATH, "/html/body/div[3]/div/div/div/div/form/div[2]/input")
            search_bar.clear()
        except NoSuchElementException:
            print("Search bar not found.")
        
if __name__ == "__main__":
    pass

