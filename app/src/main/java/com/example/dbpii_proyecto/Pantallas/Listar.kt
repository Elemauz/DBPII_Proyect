import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.dbpii_proyecto.TransaccionesGlobales
import com.example.dbpii_proyecto.Transaccion
import com.example.dbpii_proyecto.R


@Composable
fun Listar(
    navController: NavController,
    transacciones: List<Transaccion>, // ✅ este es el tipo correcto
    onItemClick: (Transaccion) -> Unit
)
 {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp) // Espacio exterior
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(1.dp)) // Borde
            .padding(25.dp) // Espacio interior (dentro del borde)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Buscador con ancho reducido
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar") },
                modifier = Modifier
                    .fillMaxWidth(0.90f) // Un poco más angosto
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
            )

            val filteredList = TransaccionesGlobales.lista.filter {
                it.titulo.contains(searchQuery.text, ignoreCase = true)
            }

            LazyColumn {
                items(filteredList) { transaccion ->
                    TransaccionItem(
                        transaccion = transaccion,
                        onClick = { onItemClick(transaccion) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun TransaccionItem(transaccion: Transaccion, onClick: () -> Unit) {
    val imagenId = if (transaccion.flujo == "Ingreso")
        R.drawable.cuenta_picture else R.drawable.cuenta_picture

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imagenId),
                contentDescription = "Imagen",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 12.dp)
            )
            Column {
                Text(
                    text = transaccion.titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "${transaccion.flujo}: S/.${transaccion.monto}",
                    color = Color.Gray
                )
            }
        }
    }
}


