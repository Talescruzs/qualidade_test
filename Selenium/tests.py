import pytest
from seleniumDrive import BasicTests
from selenium import webdriver

@pytest.fixture
def database_data():
    driver = webdriver.Firefox()
    url = "https://www.uni-muenchen.de/index.html"

    yield {"driver": driver, "main_url": url}
    driver.quit()  

@pytest.mark.home_page
def test_basic_functionalities(database_data):  
    driver = BasicTests(database_data["driver"])

    # Open Page
    driver.open_page(database_data["main_url"])

    assert driver.get_title() == "Startseite - LMU München"

    driver.open_menu()
    database_data["driver"].implicitly_wait(10) 
    driver.change_language('en')


