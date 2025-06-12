import pytest
from SeleniumDriver.seleniumDrive import BasicTests
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import time


@pytest.fixture(scope="session")
def driver():
    # Inicializa o WebDriver uma vez por sessão
    driver = webdriver.Firefox()
    yield driver
    driver.quit() 

@pytest.fixture
def basicTests(driver):
    # Cria a interface BasicTests usando o driver compartilhado
    interface = BasicTests(driver)
    url = "https://www.uni-muenchen.de/index.html"

    interface.open_page(url)

    yield {"driver": interface}

@pytest.mark.home_page
def test_init(basicTests): 
    driver = basicTests["driver"]
    assert driver.get_title() == "Startseite - LMU München"

@pytest.mark.home_page
def test_menu_btn(basicTests): 
    driver = basicTests["driver"]
    driver.open_menu()
    assert driver.element_is_visible("/html/body/div[1]/nav/ul")
    driver.close_menu()
    assert not driver.element_is_visible("/html/body/div[1]/nav/ul")


@pytest.mark.home_page
def test_change_language(basicTests):  
    driver = basicTests["driver"]

    driver.open_menu()
    driver.change_language('en')
    
    driver.implicitly_wait(10) 
    assert driver.get_title() == "Home - LMU Munich"

@pytest.mark.home_page
def test_search_bnt(basicTests):  
    driver = basicTests["driver"]

    # Clique no botão de busca
    driver.click_search()

    # time.sleep(2)

    assert driver.element_in_screen("/html/body/div[3]/div/div/div/h3")






