from selenium import webdriver
from selenium.webdriver.common.by import By  
from selenium.common.exceptions import NoSuchElementException  
  
class MainSelenium:
    def __init__(self, driver: webdriver.Remote):  
        self.driver = driver
    
    def implicitly_wait(self, time: int):
        self.driver.implicitly_wait(time)

    def get_title(self) -> str:  
        return self.driver.title
    
    def element_in_screen(self, xpath: str) -> bool:
        try:
            # Localiza o elemento pelo XPath
            element = self.driver.find_element(By.XPATH, xpath)
            
            # Obtém a posição e o tamanho do elemento
            element_position = element.location
            element_size = element.size

            # Obtém as dimensões da janela do navegador
            window_width = self.driver.execute_script("return window.innerWidth;")
            window_height = self.driver.execute_script("return window.innerHeight;")

            # Verifica se o elemento está dentro da janela visível
            is_within_window = (
                0 <= element_position["x"] < window_width and
                0 <= element_position["y"] < window_height
            )

            print(f"Element position: {element_position}, size: {element_size}")
            print(f"Window dimensions: width={window_width}, height={window_height}")
            return is_within_window
            
        except NoSuchElementException:
            print(f"Element with XPath '{xpath}' does not exist.")
            return False
    
    def element_is_visible(self, xpath: str) -> bool:  
        try:
            element = self.driver.find_element(By.XPATH, xpath)
            
            if element.is_displayed():
                return True
            else:
                return False
        except NoSuchElementException:
            print(f"Element with XPath '{xpath}' does not exist.")
            return False

    def open_page(self, url):  
        self.driver.get(url) 
        self.driver.implicitly_wait(10) 
    
    def close(self):  
        self.driver.close()