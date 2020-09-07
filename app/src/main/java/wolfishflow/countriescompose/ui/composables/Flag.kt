package wolfishflow.countriescompose.ui.composables

import androidx.compose.foundation.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import wolfishflow.countriescompose.data.models.Country

@Composable
fun FlagBanner(country: Country) {
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
            modifier = Modifier.fillMaxSize().clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.FillHeight
        )
    }
}
