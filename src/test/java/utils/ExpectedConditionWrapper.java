package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditionWrapper implements ExpectedCondition<Boolean> {
    private final ExpectedCondition<Boolean> innerCondition;
    private boolean boolForNoSuchElementException;

    private ExpectedConditionWrapper(ExpectedCondition<Boolean> condition) {
        innerCondition = condition;
    }

    private ExpectedConditionWrapper(ExpectedCondition<Boolean> condition, boolean bool) {
        innerCondition = condition;
        boolForNoSuchElementException = bool;
    }

    public Boolean apply(WebDriver webDriver) {
        try {
            return innerCondition.apply(webDriver);
        } catch (NoSuchElementException noSuchElement) {
            return boolForNoSuchElementException;
        }
    }

    public static ExpectedConditionWrapper condition(ExpectedCondition<Boolean> innerCondition) {
        return new ExpectedConditionWrapper(innerCondition);
    }

    public static ExpectedConditionWrapper condition(ExpectedCondition<Boolean> innerCondition,
                                                     boolean boolForNoSuchElementException) {
        return new ExpectedConditionWrapper(innerCondition, boolForNoSuchElementException);
    }
}