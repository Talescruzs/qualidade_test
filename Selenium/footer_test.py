import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture(scope="function")
def driver():
    from selenium import webdriver
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

@pytest.mark.footer
def test_open_instagram(driver):
    driver.get("https://www.uni-muenchen.de/index.html")
    insta_link = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//span[text()='Instagram']/parent::a"))
    )
    driver.execute_script("arguments[0].scrollIntoView({block: 'center'});", insta_link)
    WebDriverWait(driver, 5).until(EC.visibility_of(insta_link))
    import time; time.sleep(1)
    insta_link.click()
    driver.switch_to.window(driver.window_handles[-1])
    WebDriverWait(driver, 10).until(
        EC.url_contains("instagram.com")
    )
    assert "instagram.com" in driver.current_url
    driver.close()
    driver.switch_to.window(driver.window_handles[0])

@pytest.mark.footer
def test_logo_present(driver):
    driver.get("https://www.uni-muenchen.de/index.html")
    logo = driver.find_element(By.XPATH, "//img[contains(@alt, 'LMU')]")
    assert logo.is_displayed()

@pytest.mark.footer
def test_footer_university_name(driver):
    driver.get("https://www.uni-muenchen.de/index.html")
    footer = driver.find_element(By.XPATH, "//h2[contains(@class, 'footer__content-headline') and contains(text(), 'Ludwig-Maximilians-Universität München')]")
    assert footer.is_displayed()

@pytest.mark.footer
def test_footer_phone(driver):
    driver.get("https://www.uni-muenchen.de/index.html")
    phone = driver.find_element(By.XPATH, "//span[@itemprop='telephone' and contains(text(), '+49 89 2180-0')]")



@pytest.mark.home_page
def test_loja_external_link(driver):
    driver.get("https://www.uni-muenchen.de/index.html")
    loja_link = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//a[contains(@href, 'lmu-shop')]"))
    )
    loja_link.click()
    WebDriverWait(driver, 10).until(
        EC.url_contains("lmu-shop")
    )
    assert "lmu-shop" in driver.current_url