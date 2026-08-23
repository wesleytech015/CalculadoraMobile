package com.wesley.calculadoramobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wesley.calculadoramobile.ui.theme.CalculadoraMobileTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalculadoraMobileTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { espacamentoInterno ->

                    TelaCalculadora(
                        modifier = Modifier.padding(espacamentoInterno)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaCalculadora(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "12 + 8",
            fontSize = 24.sp
        )

        Text(
            text = "20",
            fontSize = 48.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviaCalculadora() {
    CalculadoraMobileTheme {
        TelaCalculadora()
    }
}