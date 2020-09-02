package wolfishflow.countriescompose.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import wolfishflow.countriescompose.ui.theme.CountriesComposeTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
                //todo remove this once proper mvvm is setup
                viewModel.retrieveCanada()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountriesComposeTheme {
        Greeting("Android")
    }
}