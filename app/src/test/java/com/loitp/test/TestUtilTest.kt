package com.loitp.test

import com.google.common.truth.Truth.assertThat
import com.loitp.model.Task
import org.junit.Test

class TestUtilTest {
//    @Test
//    fun `signUp function returns false when username or password is empty`() {
//        val userName = ""
//        val password = ""
//        val repeatPassword = ""
//        assertThat(TestUtil.signUp(userName, password, repeatPassword)).isFalse()
//    }
//
//    @Test
//    fun `signUp function returns false when username is taken`() {
//        val userName = "Peter"
//        val password = "12345"
//        val repeatPassword = "12345"
//        assertThat(TestUtil.signUp(userName, password, repeatPassword)).isFalse()
//    }
//
//    @Test
//    fun `signUp function returns false when password and repeat password don't match`() {
//        val userName = "James"
//        val password = "12345"
//        val repeatPassword = "67890"
//        assertThat(TestUtil.signUp(userName, password, repeatPassword)).isFalse()
//    }
//
//    @Test
//    fun `signUp function returns false when password is less than two characters`() {
//        val userName = "Brian"
//        val password = "1"
//        val repeatPassword = "1"
//        assertThat(TestUtil.signUp(userName, password, repeatPassword)).isFalse()
//    }

    @Test
    fun `create task null return false`() {
        TestUtil.createTask(null) { result ->
            assertThat(result).isFalse()
        }
    }

    @Test
    fun `create task title empty return false`() {
        val task = Task().apply {
            title = ""
        }
        TestUtil.createTask(task) { result ->
            assertThat(result).isFalse()
        }
    }
}
