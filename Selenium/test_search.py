import pytest
import time
from SeleniumDriver.searchSelenium import SearchSelenium
from selenium import webdriver
from main_tests import driver 

# # inicial
@pytest.fixture(scope="session")
def driver():
     driver = webdriver.Chrome()
     yield driver
     driver.quit() 

@pytest.fixture
def searchTests(driver):
    interface = SearchSelenium(driver)
    url = "https://www.uni-muenchen.de/index.html"
    slide_delay = 1

    interface.open_page(url)
    interface.click_search()
    time.sleep(slide_delay)

    yield {"driver": interface}
    # driver.quit()


@pytest.mark.search_element
def test_selected_btn(searchTests):  
    driver = searchTests["driver"]
    assert driver.check_option_is_selected("1") == True, "Option 1 should be selected"
    driver.click_option("2")
    assert driver.check_option_is_selected("2") == True, "Option 2 should be selected"
    driver.click_option("1")
    assert driver.check_option_is_selected("1") == True, "Option 1 should be selected"
    driver.click_option("1")
    assert driver.check_option_is_selected("1") == True, "Option 1 should be selected"

@pytest.mark.search_element
def test_search_bar(searchTests):
    driver = searchTests["driver"]

    driver.write_on_search_bar("test")
    assert driver.get_search_bar_text() == "test", "Search bar should contain 'test'"
    driver.clean_search_bar()
    driver.write_on_search_bar("testandoBlaBlaBla")
    assert driver.get_search_bar_text() == "testandoBlaBlaBla", "Search bar should contain 'test'"
    driver.clean_search_bar()

@pytest.mark.search_element
def test_search_main_website(searchTests):
    driver = searchTests["driver"]

    driver.click_on("/html/body/main/div[1]/div/div/div[2]/form/div/div/ul/li[1]/label")
    driver.write_on_search_bar("test")
    driver.click_search_button()
    teste = driver.get_search_results()
    assert len(teste) > 0, "Search results should not be empty"
    for result in teste:
        assert ("test" in result["title"].lower() or "test" in result["text"].lower() or "PDF" in result["type"].lower()), result["title"].lower() + " " + result["text"].lower() + " " + result["type"].lower() + " should contain 'teste'"

@pytest.mark.search_element
def test_search_all_lmu_sites(searchTests):
    driver = searchTests["driver"]

    driver.click_on("/html/body/main/div[1]/div/div/div[2]/form/div/div/ul/li[1]/label")
    driver.click_option("2")
    driver.write_on_search_bar("test")
    driver.click_search_button()
    teste = driver.get_search_results()
    assert len(teste) > 0, "Search results should not be empty"
    for result in teste:
        assert ("test" in result["title"].lower() or "test" in result["text"].lower()), result["title"].lower() + " " + result["text"].lower() + " " + result["type"].lower() + " should contain 'teste'"
