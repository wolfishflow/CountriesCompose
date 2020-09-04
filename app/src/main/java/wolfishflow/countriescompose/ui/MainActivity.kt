package wolfishflow.countriescompose.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import wolfishflow.countriescompose.data.models.Country
import wolfishflow.countriescompose.ui.theme.CountriesComposeTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataComposable(countriesLiveDataList = viewModel.countriesList)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun LiveDataComposable(countriesLiveDataList: LiveData<List<Country>>) {
    val countries by countriesLiveDataList.observeAsState(initial = emptyList())

    //TODO figure out how to animate between 2 composables

    AnimatedVisibility(
        visible = countries.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
        initiallyVisible = true
    ) {
        LoadingComposable()
    }

    AnimatedVisibility(
        visible = countries.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
        initiallyVisible = true
    ) {
        RecyclerViewComposable(countries = countries)
    }
}

@Preview
@Composable
fun LoadingComposable() {
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
fun RecyclerViewComposable(
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
                        Box(
                            modifier = Modifier.fillMaxWidth().then(Modifier.height(150.dp)),
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            CoilImageWithCrossfade(
                                data = ImageRequest.Builder(ContextAmbient.current)
                                    .data(country.flagImageUrl).allowHardware(false)
                                    .decoder(
                                        SvgDecoder(ContextAmbient.current)
                                    ).build(),
                                modifier = Modifier.fillMaxWidth().then(Modifier.fillMaxHeight()),
                                contentScale = ContentScale.FillHeight
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = country.name, style = MaterialTheme.typography.h6)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Capital: ${country.capital}",
                                style = MaterialTheme.typography.body1
                            )
                            Text(
                                text = "Region: ${country.region}",
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    }
}






