from selenium import webdriver

def teste():
    driver = webdriver.Chrome()
    driver.get("https://www.example.com")
    print(driver.title)
    

if __name__ == "__main__":
    teste()