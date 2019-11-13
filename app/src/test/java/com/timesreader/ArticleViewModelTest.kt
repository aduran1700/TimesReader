package com.timesreader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import com.timesreader.model.Error as ErrorState
import com.timesreader.model.Loading
import com.timesreader.model.Success
import com.timesreader.model.ViewState
import com.timesreader.respository.Repository
import com.timesreader.viewmodel.ArticleListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var articleListViewModel: ArticleListViewModel
    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var viewStateObserver: Observer<ViewState>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        articleListViewModel = ArticleListViewModel(repository,
            TestContextProvider()
        ).apply {
            getViewModelLiveData().observeForever(viewStateObserver)
        }
    }

    @Test
    fun `should success when getTopArticles returns data` () =
        testCoroutineRule.runBlockingTest {
            val data = Any()
            whenever(repository.getTopArticles()).thenReturn(Success(data))

            articleListViewModel.getTopArticles()
            verify(viewStateObserver).onChanged(Loading)
            verify(viewStateObserver).onChanged(Success(data))
        }

    @Test
    fun `should fail when getTopArticles return error` () =
        testCoroutineRule.runBlockingTest {
            val error = ErrorState(Throwable())
            whenever(repository.getTopArticles()).thenReturn(error)

            articleListViewModel.getTopArticles()
            verify(viewStateObserver).onChanged(Loading)
            verify(viewStateObserver).onChanged(error)
        }
}