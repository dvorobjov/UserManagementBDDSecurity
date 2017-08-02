package com.dvorobjov;

import com.dvorobjov.steps.World;
import net.continuumsecurity.Config;
import net.continuumsecurity.Credentials;
import net.continuumsecurity.behaviour.ILogin;
import net.continuumsecurity.behaviour.INavigable;
import net.continuumsecurity.web.WebApplication;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro Vorobiov on 02.08.2017.
 */
public class UserManagementApplication extends WebApplication implements INavigable, ILogin {

    private boolean loggedIn = false;
    private String lastResponsePageSource;

    @Override
    public void navigate() {
        super.navigate();
    }


    @Override
    public void login(Credentials credentials) {
        lastResponsePageSource = null;
        Map<String,String> authTokens = new HashMap<String, String>();
        authTokens.put("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
        browser.setAuthTokens(authTokens);
        browser.getUrl(Config.getInstance().getBaseUrl() + "?id=1");
        loggedIn = true;

        try {
            WebDriverWait wait = new WebDriverWait(browser.getWebDriver(), 10);
            wait.until(ExpectedConditions.textToBe(By.name(""), "{"));
        } catch (TimeoutException e) {
            log.error("Didn't get the element {");
        }

        lastResponsePageSource = driver.getPageSource();

        log.debug("login. page source: {}" + lastResponsePageSource);

        lastResponsePageSource.length();
    }

    @Override
    public void openLoginPage() {
        loggedIn = false;
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getLastResponsePageSource() {
        return lastResponsePageSource;
    }
}
