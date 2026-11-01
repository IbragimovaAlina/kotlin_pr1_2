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

    // ИСПРАВЛЕНО: ComponentActivity вместо MainActivity — иначе setContent конфликтует
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // Оборачиваем WaterTracker в тему и в setContent, как в onCreate()
    private fun setWaterTracker() {
        composeTestRule.setContent {
            Kotlin_mirea_pr2Theme {
                WaterTracker()
            }
        }
    }

    // Набираем n порций по 250 мл, кликая по кнопке "+250 мл"
    private fun addWater(n: Int) {
        repeat(n) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }
    }

    // ---------- Пункт 1: кнопка добавления жидкости ----------
    @Test
    fun addWaterButton_existsAndIncrementsBy250() {
        setWaterTracker()

        // Кнопка присутствует
        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed()

        // Начальное значение счётчика = 100 мл
        composeTestRule.onNodeWithText("100 мл").assertIsDisplayed()

        // Одно нажатие -> 100 + 250 = 350 мл
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("350 мл").assertIsDisplayed()

        // Второе нажатие -> 350 + 250 = 600 мл
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("600 мл").assertIsDisplayed()
    }

    // ---------- Пункт 2: кнопка "Завершить день" ----------
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