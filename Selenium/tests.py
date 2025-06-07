import pytest
from SeleniumDriver.seleniumDrive import BasicTests
from selenium import webdriver


@pytest.fixture
def basicTests():
    driver = webdriver.Firefox()
    interface = BasicTests(driver)
    url = "https://www.uni-muenchen.de/index.html"

    interface.open_page(url)
    interface.implicitly_wait(10)

    yield {"driver": interface}
    driver.quit()  

# @pytest.mark.home_page
# def test_basic_functionalities(basicTests): 
#     driver = basicTests["driver"]
#     assert driver.get_title() == "Startseite - LMU München"


@pytest.mark.menu
def test_menu(basicTests):  
    driver = basicTests["driver"]

    driver.open_menu()
    driver.implicitly_wait(10) 
    driver.change_language('en')
    
    driver.implicitly_wait(10) 
    assert driver.get_title() == "Home - LMU Munich"

    driver.open_menu()
    driver.implicitly_wait(10) 
    driver.test_list_menu()


