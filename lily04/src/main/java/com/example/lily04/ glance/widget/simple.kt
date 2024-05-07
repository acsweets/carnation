import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

class SimpleAppWidget : GlanceAppWidget() {
//    override val sizeMode: GlanceSizeMode = SizeMode.Responsive
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        TODO("Not yet implemented")
    }

    @Composable
     fun Content() {
        val textStyle = TextStyle(
            fontSize = 20.sp
        )

        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
            ) {
                Text(
                    text = "Hello, Glance!",
                    style = textStyle,
                )
                Text(
                    text = "This is a simple app widget.",
                    style = textStyle,
                )
            }
        }
    }
}