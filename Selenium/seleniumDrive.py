import pytest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class TestUniversity():
    def __init__(self):
        self.driver = webdriver.Firefox()

    def setup_method(self):
        self.driver.get('https://www.uni-muenchen.de/index.html')
    
    def open_main_menu(self):
        main_menu = self.driver.find_element("css selector", "button[data-js-item='js-skip-links-navvuebutton']")
        main_menu.click()
    
        
if __name__ == "__main__":
    teste = TestUniversity()
    teste.setup_method()
    teste.open_main_menu()