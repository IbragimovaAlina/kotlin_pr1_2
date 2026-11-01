package com.example.kotlin_mirea_pr2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlin_mirea_pr2.ui.theme.Kotlin_mirea_pr2Theme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaterTrackerUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun setWaterTracker() {
        composeTestRule.setContent {
            Kotlin_mirea_pr2Theme {
                WaterTracker()
            }
        }
    }

    private fun addWater(n: Int) {
        repeat(n) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }
    }

    @Test
    fun addWaterButton_existsAndIncrementsBy250() {
        setWaterTracker()

        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText("100 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("350 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("600 мл").assertIsDisplayed()
    }

    @Test
    fun finishDayButton_resetsWater_andUpdatesStreak() {
        setWaterTracker()

        composeTestRule.onNodeWithText("Завершить день").assertIsDisplayed()

        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
        composeTestRule.onNodeWithText("Дней подряд (>= 1500 мл): 0").assertIsDisplayed()


        addWater(6)
        composeTestRule.onNodeWithText("1500 мл").assertIsDisplayed()
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
        composeTestRule.onNodeWithText("Дней подряд (>= 1500 мл): 1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд (>= 1500 мл): 0").assertIsDisplayed()
    }

    @Test
    fun streakText_isDisplayedWithCorrectValue() {
        setWaterTracker()
        composeTestRule.onNodeWithText("Дней подряд (>= 1500 мл): 0").assertIsDisplayed()
        addWater(6)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд (>= 1500 мл): 1").assertIsDisplayed()
    }
}