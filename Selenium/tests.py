import pytest
from seleniumDrive import TestUniversity

class TestSeleniumDrive:
    @pytest.fixture(autouse=True)
    def setup(self):
        self.selenium_instance = TestUniversity()
        self.mainLink = "https://www.uni-muenchen.de/index.html"

    def test_driver_initialization(self):
        assert self.selenium_instance.driver is not None, "Driver is not initialized"

    def test_google_page_load(self):
        self.selenium_instance.setup_method()
        assert self.selenium_instance.driver.title == "Startseite - LMU München" , "Erro ao chegar no site"