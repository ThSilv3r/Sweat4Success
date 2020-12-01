package com.example.sweat4success

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.sweat4success.account.CreateAccount
import cucumber.api.CucumberOptions
import cucumber.api.java.en.*
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
public class CreateAccountSteps {

    @Rule



    @When("^The user enters a username <username> and the input is correct")
    public fun enterUsername() {
        onView(withId(R.id.userNameTextBoxC)).perform(typeText("Max Mustermann"))
    }

    @When("^The user enters a e-mail <e-mail> and the input is correct")
    public fun enterEmail(){
        onView(withId(R.id.emailTextBoxC)).perform(typeText("Max@Mustermann@test.de"))
    }

    @When("^The user enters a password <password> and the input is correct")
    public fun enterPassword(){
        onView(withId(R.id.passwordTextBoxC)).perform(typeText("passwort"))
    }

    @When("^The user enters a birthday <birthday> and the input is correct")
    public fun enterAge(){
        onView(withId(R.id.ageTextBoxC)).perform(typeText("20"))
    }

    @When("^The user clicks on create Acount <createButton>")
    public fun createAccountClick(){
        onView(withId(R.id.createAccountButton)).perform(click())
    }

    @Then("^The the acount should be created  Feature: Profil")
    public fun goToProfil(){
        onView(withId(R.id.userNameTextBoxC)).check(matches(not(isDisplayed())))
    }
}