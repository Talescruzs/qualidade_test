from selenium import webdriver
from selenium.webdriver.common.by import By  
from selenium.common.exceptions import NoSuchElementException  
  
class MainSelenium:
    def __init__(self, driver: webdriver.Remote):  
        self.driver = driver
    
    def implicitly_wait(self, time: int):
        self.driver.implicitly_wait(time)