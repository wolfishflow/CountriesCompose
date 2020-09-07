package wolfishflow.countriescompose.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import wolfishflow.countriescompose.data.models.Country
import wolfishflow.countriescompose.ui.CountryPreviewProvider
import wolfishflow.countriescompose.ui.theme.CountriesComposeTheme

@ExperimentalAnimationApi
@Composable
fun HomeScreen(countriesLiveDataList: LiveData<List<Country>>) {
    val countries by countriesLiveDataList.observeAsState(initial = emptyList())

    //TODO figure out how to animate between 2 composables

    AnimatedVisibility(
        visible = countries.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
        initiallyVisible = true
    ) {
        Loading()
    }

    AnimatedVisibility(
        visible = countries.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
        initiallyVisible = true
    ) {
        CountryList(countries = countries)
    }
}

@Preview
@Composable
fun Loading() {
    CountriesComposeTheme {
        Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryList(
    @PreviewParameter(CountryPreviewProvider::class)
    countries: List<Country>
) {
    CountriesComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumnFor(items = countries) { country ->
                Card(
                    modifier = Modifier.fillParentMaxWidth().then(Modifier.padding(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.clickable(
                            onClick = {},
                            indication = RippleIndication()
                        ).then(
                            Modifier.padding(8.dp)
                        )
                    ) {
                        NameCapitalAndRegion(country)
                    }
                }
            }
        }
    }
}