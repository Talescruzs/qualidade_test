from selenium import webdriver
import pytest

from selenium.webdriver.common.keys import Keys



# Create a new instance of the Chrome driver

driver = webdriver.Firefox()
driver.get('http://google.com')
print(driver.title)
driver.quit()
