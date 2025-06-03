import pytest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class TestUniversity():
    def __init__(self):
        self.driver = webdriver.Firefox()

    def setup_method(self):
        self.driver.get('https://www.uni-muenchen.de/index.html')
    
    def get_main_menu(self):
        main_menu = self.driver.find_element("class name", "main-navigation__list-link")
        return main_menu
    
    def click(self, element):
        try:
            element.click()
        except Exception as e:
            print(f"Error clicking element: {e}")
        
if __name__ == "__main__":
    teste = TestUniversity()
    teste.setup_method()