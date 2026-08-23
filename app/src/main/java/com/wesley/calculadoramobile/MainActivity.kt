package com.wesley.calculadoramobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wesley.calculadoramobile.ui.theme.CalculadoraMobileTheme

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

    var valorAtual by remember { mutableStateOf("0") }
    var primeiroValor by remember { mutableStateOf<Double?>(null) }
    var operacao by remember { mutableStateOf("") }
    var calculo by remember { mutableStateOf("") }
    var novoNumero by remember { mutableStateOf(false) }

    fun adicionarNumero(numero: String) {

        valorAtual = if (valorAtual == "0" || novoNumero) {
            novoNumero = false
            numero
        } else {
            valorAtual + numero
        }
    }

    fun adicionarDecimal() {

        if (novoNumero) {
            valorAtual = "0."
            novoNumero = false
        } else if (!valorAtual.contains(".")) {
            valorAtual += "."
        }
    }

    fun escolherOperacao(simbolo: String) {

        primeiroValor = valorAtual.toDoubleOrNull()
        operacao = simbolo
        calculo = "$valorAtual $simbolo"
        novoNumero = true
    }

    fun calcularResultado() {

        val valor1 = primeiroValor
        val valor2 = valorAtual.toDoubleOrNull()

        if (valor1 == null || valor2 == null || operacao.isEmpty()) {
            return
        }

        val resultado = when (operacao) {

            "+" -> valor1 + valor2

            "-" -> valor1 - valor2

            "×" -> valor1 * valor2

            "÷" -> {

                if (valor2 == 0.0) {

                    valorAtual = "Erro"
                    calculo = "Divisão por zero"
                    primeiroValor = null
                    operacao = ""
                    novoNumero = true

                    return
                }

                valor1 / valor2
            }

            else -> return
        }

        calculo = "$valor1 $operacao $valor2 ="

        valorAtual = if (resultado % 1.0 == 0.0) {
            resultado.toLong().toString()
        } else {
            resultado.toString()
        }

        primeiroValor = null
        operacao = ""
        novoNumero = true
    }

    fun limpar() {

        valorAtual = "0"
        primeiroValor = null
        operacao = ""
        calculo = ""
        novoNumero = false
    }

    fun alterarSinal() {

        val numero = valorAtual.toDoubleOrNull() ?: return
        val resultado = numero * -1

        valorAtual = if (resultado % 1.0 == 0.0) {
            resultado.toLong().toString()
        } else {
            resultado.toString()
        }
    }

    fun aplicarPorcentagem() {

        val numero = valorAtual.toDoubleOrNull() ?: return
        val resultado = numero / 100

        valorAtual = if (resultado % 1.0 == 0.0) {
            resultado.toLong().toString()
        } else {
            resultado.toString()
        }

        novoNumero = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {

            Text(
                text = calculo,
                fontSize = 24.sp,
                color = Color.Gray
            )

            Text(
                text = valorAtual,
                fontSize = 52.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { limpar() },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("AC")
            }

            Button(
                onClick = { alterarSinal() },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("+/-")
            }

            Button(
                onClick = { aplicarPorcentagem() },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("%")
            }

            Button(
                onClick = { escolherOperacao("÷") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4)
                )
            ) {
                Text("÷")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { adicionarNumero("7") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("7")
            }

            Button(
                onClick = { adicionarNumero("8") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("8")
            }

            Button(
                onClick = { adicionarNumero("9") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("9")
            }

            Button(
                onClick = { escolherOperacao("×") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4)
                )
            ) {
                Text("×")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { adicionarNumero("4") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("4")
            }

            Button(
                onClick = { adicionarNumero("5") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("5")
            }

            Button(
                onClick = { adicionarNumero("6") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("6")
            }

            Button(
                onClick = { escolherOperacao("-") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4)
                )
            ) {
                Text("-")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { adicionarNumero("1") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("1")
            }

            Button(
                onClick = { adicionarNumero("2") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("2")
            }

            Button(
                onClick = { adicionarNumero("3") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("3")
            }

            Button(
                onClick = { escolherOperacao("+") },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4)
                )
            ) {
                Text("+")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { adicionarNumero("0") },
                modifier = Modifier
                    .weight(2f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("0")
            }

            Button(
                onClick = { adicionarDecimal() },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(".")
            }

            Button(
                onClick = { calcularResultado() },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4)
                )
            ) {
                Text("=")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviaCalculadora() {

    CalculadoraMobileTheme {
        TelaCalculadora()
    }
}