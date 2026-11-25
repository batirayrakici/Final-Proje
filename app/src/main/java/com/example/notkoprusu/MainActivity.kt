package com.example.notkoprusu // kendi paket adınla değiştir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Figma tasarımındaki renkler
private val Zemin = Color(0xFFFFF0B3)
private val Vurgu = Color(0xFFF2C94C)
private val Lacivert = Color(0xFF1E3A4C)
private val Gri = Color(0xFF6B7882)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { AcilisEkrani() } }
    }
}

@Composable
fun AcilisEkrani() {
    var eposta by rememberSaveable { mutableStateOf("") }
    var bolum by rememberSaveable { mutableStateOf("") }
    var altDonem by rememberSaveable { mutableStateOf("") }

    // Başlangıç verisi; sonra Room/Firebase'den gelecek
    val bolumler = listOf("Bilgisayar Mühendisliği", "Elektrik-Elektronik Mühendisliği", "Makine Mühendisliği")
    val donemler = listOf("1. sınıf Güz", "1. sınıf Bahar", "2. sınıf Güz", "2. sınıf Bahar")

    // Üç alan dolmadan "Devam" kapalı (Hafta 1 kuralı)
    val hazir = eposta.contains("@") && bolum.isNotEmpty() && altDonem.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Zemin)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier.size(72.dp).background(Vurgu, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) { Text("N", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Lacivert) }

        Text("NotKöprüsü", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Lacivert)
        Text("Doğru hocanın notuna ulaş.", fontSize = 14.sp, color = Gri)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = eposta,
            onValueChange = { eposta = it.trim() },
            label = { Text("Okul e-postası") },
            placeholder = { Text("ad.soyad@okul.edu.tr") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Secim("Bölüm", "Bölümünü seç", bolum, bolumler) { bolum = it }
        Secim("Alt dönem", "Dönemini seç", altDonem, donemler) { altDonem = it }

        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { /* TODO: Dersler ekranına geç (Hafta 3) */ },
            enabled = hazir,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Vurgu, contentColor = Lacivert),
            modifier = Modifier.fillMaxWidth().height(52.dp)
        ) { Text("Devam", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) }

        Text("Notlar yalnızca aynı bölümün ilgili alt dönemine açılır.", fontSize = 12.sp, color = Gri)
    }
}

@Composable
private fun Secim(etiket: String, ipucu: String, secili: String, secenekler: List<String>, secildi: (String) -> Unit) {
    var acik by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = secili,
            onValueChange = {},
            readOnly = true,
            label = { Text(etiket) },
            placeholder = { Text(ipucu) },
            trailingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        // Alanın üstünde saydam tıklama katmanı
        Box(Modifier.matchParentSize().clickable { acik = true })
        DropdownMenu(expanded = acik, onDismissRequest = { acik = false }) {
            secenekler.forEach { s ->
                DropdownMenuItem(text = { Text(s) }, onClick = { secildi(s); acik = false })
            }
        }
    }
}
