package wolfishflow.countriescompose.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import dagger.hilt.android.AndroidEntryPoint
import wolfishflow.countriescompose.data.models.Country
import wolfishflow.countriescompose.ui.theme.CountriesComposeTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataComposable(countriesLiveDataList = viewModel.countriesList)
        }
    }
}

@Composable
fun LiveDataComposable(countriesLiveDataList: LiveData<List<Country>>) {
    val countries by countriesLiveDataList.observeAsState(initial = emptyList())

    if (countries.isEmpty()) {
        //todo loading???
    } else {
        RecyclerViewComposable(countries = countries)
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun RecyclerViewComposable(
    @PreviewParameter(CountryPreviewProvider::class)
    countries: List<Country>
) {
    CountriesComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumnFor(items = countries) {
                Column {
//                    TODO figure out why coil is crashing
//                     No static method CoilImage(Lcoil/request/ImageRequest;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;Landroidx/compose/ui/graphics/ColorFilter;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V in class Ldev/chrisbanes/accompanist/coil/CoilKt; or its super classes (declaration of 'dev.chrisbanes.accompanist.coil.CoilKt' appears in /data/app/~~AdVXEBVPGd6k4lCy4WW1Wg==/wolfishflow.countriescompose-gBMgXNniToSb_Gti1szkLg==/base.apk)
//                    Box(modifier = Modifier.gravity(Alignment.CenterVertically).then(Modifier.size(200.dp))) {
//                        CoilImage(request = ImageRequest.Builder(ContextAmbient.current).data(it.flagImageUrl).decoder(SvgDecoder(ContextAmbient.current)).build())
//                    }
                    Text(text = it.name, style = MaterialTheme.typography.h6)
                    Text(text = "Region: ${it.region}", style = MaterialTheme.typography.body1)
                }
            }
        }
    }

}

