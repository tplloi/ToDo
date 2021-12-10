package com.loitp.test

import com.google.common.truth.Truth.assertThat
import com.loitp.model.Task
import org.junit.Test

class TestUtilTest {

    @Test
    fun `create task return false when task null`() {
        TestUtil.createTask(null) { result ->
            assertThat(result).isFalse()
        }
    }

    @Test
    fun `create task return false when title empty`() {
        val task = Task().apply {
            title = ""
        }
        TestUtil.createTask(task) { result ->
            assertThat(result).isFalse()
        }
    }

    @Test
    fun `get list task all return false when list null or empty `() {
        TestUtil.getListTaskAll { result ->
            assertThat(result).isFalse()
        }
    }

    @Test
    fun `get list task complete return false when list null or empty `() {
        TestUtil.getListTaskComplete { result ->
            assertThat(result).isFalse()
        }
    }

    @Test
    fun `get list task incomplete return false when list null or empty `() {
        TestUtil.getListTaskIncomplete { result ->
            assertThat(result).isFalse()
        }
    }
}
