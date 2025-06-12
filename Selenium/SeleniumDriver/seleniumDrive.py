from selenium import webdriver
from selenium.webdriver.common.by import By  
from selenium.common.exceptions import NoSuchElementException  

from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from SeleniumDriver.mainSelenium import MainSelenium
# from mainSelenium import MainSelenium

import time
  
  
class BasicTests(MainSelenium):  
    def __init__(self, driver: webdriver.Remote):
        super(BasicTests, self).__init__(driver)
        self.driver = driver
  
    def open_page(self, url):  
        self.driver.get(url)  
    
    def get_title(self):  
        return self.driver.title
    
    def element_is_visible(self, xpath: str) -> bool:  
        try:
            element = self.driver.find_element(By.XPATH, xpath)
            
            if element.is_displayed():
                return True
            else:
                return False
        except NoSuchElementException:
            print(f"Element with XPath '{xpath}' does not exist.")
            return False
  
    def open_menu(self):  
        self.driver.find_element("css selector", "button[data-js-item='js-skip-links-navvuebutton']").click()
        self.driver.implicitly_wait(10)

    def close_menu(self):  
        self.driver.find_element(By.XPATH, "/html/body/div[1]/nav/button").click()
        self.driver.implicitly_wait(10)

    def change_language(self, language: str):  
        try:  
            xpath = f"//ul[contains(@class, 'ml-stack-nav__menu')]//a[contains(@href, '{language}')]"
            self.driver.find_element(By.XPATH, xpath).click()
        except NoSuchElementException:  
            assert False, f"Language option '{language}' not found."

    def test_list_menu(self, elements = None):  
        atualTitle = self.get_title()
        print(f"Current title: {atualTitle}")

        if elements is None:
            elements = self.driver.find_elements(By.XPATH, "//li[contains(@class, 'fragment-li')]/ul/li[./a[not(contains(@class, 'is-home'))]]")

        # elements.remove(elements[0]) 
        print(f"Elements found: {len(elements)}")

        for i in range(len(elements)):
            # print(len(elements[i].find_elements(By.XPATH, "./a/div/span/span")))
            if len(elements[i].find_elements(By.XPATH, "./a/div/span/span")) == 2:
                print("sim")
                elements[i].click()
                WebDriverWait(elements[i], 10).until(
                    EC.presence_of_element_located((By.XPATH, "./ul[contains(@class, 'ml-stack-nav-p')]/li[./a]"))
                )
                self.test_list_menu(elements[i].find_elements(By.XPATH, "./ul[contains(@class, 'ml-stack-nav-p')]/li[./a]"))
            else:
                print("não")
                elements[i].click()
                print("não2")
                self.driver.implicitly_wait(10)
                if self.get_title() == atualTitle:
                    assert False, f"Title did not change after clicking on {elements[i].text}"
                else:
                    self.open_menu()

        
if __name__ == "__main__":
    driver = webdriver.Firefox()
    test = BasicTests(driver)
    test.open_page("https://www.uni-muenchen.de/index.html")
    test.implicitly_wait(10)

    test.open_menu()
    test.change_language('en')
    
    test.implicitly_wait(10) 

    test.open_menu()
    test.test_list_menu()
    
    driver.quit()

    