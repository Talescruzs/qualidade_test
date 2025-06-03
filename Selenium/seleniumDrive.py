import pytest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class TestUniversity():
    def __init__(self):
        self.driver = webdriver.Firefox()

    def setup_method(self):
        self.driver.get('https://www.uni-muenchen.de/index.html')
        
if __name__ == "__main__":
    teste = TestUniversity()
    teste.setup_method()