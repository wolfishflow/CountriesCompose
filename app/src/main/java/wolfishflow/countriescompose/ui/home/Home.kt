package wolfishflow.countriescompose.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.AmbientContentColor
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import dev.chrisbanes.accompanist.coil.CoilImage
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
        Box(modifier = Modifier.fillMaxSize(), alignment = Alignment.Center) {
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
                CountryCard(country)
            }
        }
    }
}


@Composable
fun CountryCard(country: Country) {
    Card(
        modifier = Modifier.fillMaxWidth().then(Modifier.padding(8.dp)),
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
            Box(modifier = Modifier.wrapContentSize()) {
                CoilImage(
                    data = ImageRequest.Builder(ContextAmbient.current)
                        .data(country.flagImageUrl)
                        .allowHardware(true)
                        .decoder(SvgDecoder(ContextAmbient.current))
                        .build(),
                    fadeIn = true,
                    loading = { Loading() },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(250.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    modifier = Modifier.fillMaxWidth().then(Modifier.align(Alignment.BottomStart))
                        .then(
                            Modifier.clip(
                                RoundedCornerShape(bottomLeft = 8.dp, bottomRight = 8.dp)
                            )
                        ).then(
                            Modifier.background(
                                brush = SolidColor(MaterialTheme.colors.surface),
                                alpha = 0.5f
                            )
                        ).then(Modifier.padding(8.dp))

                ) {
                    with(country) {
                        Text(text = name, style = MaterialTheme.typography.h6)
                        Text(
                            text = "Capital: ${if (capital.isNotEmpty()) capital else "None"}",
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            text = "Region: ${if (region.isNotEmpty()) region else "None"}",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}