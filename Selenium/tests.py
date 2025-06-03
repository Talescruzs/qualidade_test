import pytest
from seleniumDrive import TestUniversity

class TestSeleniumDrive:
    @pytest.fixture(autouse=True)
    def setup(self):
        self.selenium_instance = TestUniversity()
        self.mainLink = "https://www.uni-muenchen.de/index.html"

    def test_driver_initialization(self):
        assert self.selenium_instance.driver is not None, "Driver is not initialized"

    # Testes com selenium 
    def test_page_title(self):
        self.selenium_instance.setup_method()
        assert self.selenium_instance.driver.title == "Startseite - LMU München" , "Erro ao chegar no site"

    def test_main_menu(self):
        self.selenium_instance.open_main_menu()
    
    def finish(self):
        self.selenium_instance.driver.close()
        self.selenium_instance.driver.quit()
        assert self.selenium_instance.driver is None, "Driver is not closed properly"

