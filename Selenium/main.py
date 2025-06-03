from selenium import webdriver
import pytest

from selenium.webdriver.common.keys import Keys


driver = webdriver.Firefox()
print(driver)
driver.get('http://google.com')
print(driver.title)
driver.close()
driver.quit()
