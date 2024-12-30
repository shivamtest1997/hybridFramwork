package testRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

@Component
public class BaseTest {

  @Autowired public LoginPage login;
  @Autowired public HomePage homePage;
  @Autowired public AccountRegistrationPage accountRegistrationPage;
}
