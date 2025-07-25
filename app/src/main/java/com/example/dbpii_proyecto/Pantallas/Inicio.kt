import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import androidx.compose.ui.viewinterop.AndroidView
import com.example.dbpii_proyecto.FinanzasGlobal


@Composable
fun Inicio(finanzas: FinanzasGlobal, navController: NavController) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = {
                    PieChart(context).apply {
                        val entries = listOf(
                            PieEntry(FinanzasGlobal.resumen.ganancia, "Ganancias"),
                            PieEntry(FinanzasGlobal.resumen.gastos, "Gastos"),
                            PieEntry(FinanzasGlobal.resumen.disponible, "Disponible")
                        )
                        val dataSet = PieDataSet(entries, "").apply {
                            colors = listOf(
                                Color(0xFF1E90FF).toArgb(),
                                Color(0xFF4169E1).toArgb(),
                                Color(0xFFFF4C4C).toArgb()
                            )
                            valueTextSize = 12f
                            valueTextColor = android.graphics.Color.WHITE
                        }

                        data = PieData(dataSet)
                        description.isEnabled = false
                        isDrawHoleEnabled = true
                        setHoleColor(android.graphics.Color.WHITE)
                        setTransparentCircleAlpha(0)
                        legend.isEnabled = false
                        invalidate()
                    }
                },
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "$${FinanzasGlobal.resumen.balance}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Balance",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("ganancia", color = Color.Gray)
                    Text("${FinanzasGlobal.resumen.ganancia}", color = Color(0xFF1E90FF), fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("gastos", color = Color.Gray)
                    Text("${FinanzasGlobal.resumen.gastos}", color = Color(0xFF4169E1), fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("disponible", color = Color.Gray)
                    Text("${FinanzasGlobal.resumen.disponible}", color = Color(0xFFFF4C4C), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
