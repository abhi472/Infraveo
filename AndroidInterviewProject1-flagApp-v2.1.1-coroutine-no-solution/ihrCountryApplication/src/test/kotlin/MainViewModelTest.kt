import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iheart.country.data.Country
import com.iheart.country.ui.home.MainViewModel
import com.iheart.country.usecase.GetCountriesUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockUseCase: GetCountriesUseCase

    private lateinit var viewModel: MainViewModel

    private lateinit var country1: Country
    private lateinit var country2: Country
    private lateinit var country3: Country
    private lateinit var country4: Country
    private lateinit var country5: Country
    private lateinit var country6: Country




    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(mockUseCase)
        country1= Country("1",
             "",
             "",
             "",
             "",
             "",
             "",
             1000,
             emptyList(),
             "",
             listOf("1", "1", "1"))
        country2= Country("2",
            "",
            "",
            "",
            "",
            "",
            "",
            2000,
            emptyList(),
            "",
            listOf("1", "1", "1"))
        country3= Country("3",
            "",
            "",
            "",
            "",
            "",
            "",
            3000,
            emptyList(),
            "",
            listOf("1", "1", "1","1", "1", "1"))
        country4= Country("4",
            "",
            "",
            "",
            "",
            "",
            "",
            4000,
            emptyList(),
            "",
            listOf("1", "1", "1","1", "1", "1","1", "1", "1"))
        country5= Country("5",
            "",
            "",
            "",
            "",
            "",
            "",
            5000,
            emptyList(),
            "",
            listOf("1", "1", "1","1", "1", "1","1", "1", "1","1", "1", "1"))
        country6= Country("6",
            "",
            "",
            "",
            "",
            "",
            "",
            6000,
            emptyList(),
            "",
            listOf("1", "1", "1","1", "1", "1","1", "1", "1","1", "1", "1","1", "1", "1"))

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoadData() = runTest {

        val countries: List<Country> = listOf(country1, country2)

        val flow = flowOf(countries)

        `when`(mockUseCase.invoke()).thenReturn(flow)

        viewModel.loadData()


        advanceUntilIdle()
        val disptchedCountries = viewModel.countriesLiveData.getOrAwaitValue()

        assertEquals(countries, disptchedCountries )
    }

    @Test
    fun testBorderData() {

        val countries: List<Country> = listOf(country1, country2, country3, country4, country5)

        val largestCountry = Pair(country5.name, country5.borders?.size?:0)

       // `when`(viewModel.getTheCountryWithTheLargestNumberOfBorders(countries)).thenReturn(largestCountry)

       val checkedLargestCountry =  viewModel.getTheCountryWithTheLargestNumberOfBorders(countries)



        assertEquals(largestCountry, checkedLargestCountry)
    }

    @Test
    fun testFilteredData() {

        val countries: List<Country> = listOf(country1, country2, country3, country4, country5, country6)

        val updatedCountries =  viewModel.getTop5CountriesWithTheLargestDensity(countries)



        assert(updatedCountries.size == 5)
        assertEquals(updatedCountries[0].first, country6.name)
    }



    @After
    fun close() {
        Dispatchers.shutdown()
    }
}