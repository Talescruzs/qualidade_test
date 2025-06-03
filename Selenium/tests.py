import pytest
from seleniumDrive import TestUniversity

class TestSeleniumDrive:
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
        main_menu = self.selenium_instance.get_main_menu()
        assert main_menu is not None, "Main menu not found"
        assert self.selenium_instance.click(main_menu) is None, "Error clicking main menu"


