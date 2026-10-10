package com.microtask.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val BgColor = Color(0xFF020713)
val CardBgColor = Color(0xFF061324)
val LimeColor = Color(0xFFD8FF38)
val TextWhite = Color(0xFFF8FAFC)
val PrimaryGradient = Brush.linearGradient(listOf(Color(0xFF00AAFF), Color(0xFF8E15EF)))
val GreenGradient = Brush.linearGradient(listOf(Color(0xFF00B355), Color(0xFF007736)))
val PurpleGradient = Brush.linearGradient(listOf(Color(0xFF7C1DF7), Color(0xFF4C07A3)))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.parseColor("#020713")
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = BgColor) {
                    MicroTaskApp()
                }
            }
        }
    }
}

@Composable
fun MicroTaskApp() {
    var isLoggedIn by remember { mutableStateOf(false) }
    if (!isLoggedIn) {
        AuthScreen(onLoginSuccess = { isLoggedIn = true })
    } else {
        MainDashboard()
    }
}

@Composable
fun AuthScreen(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF8F8F7)).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("invezo.", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
        Spacer(modifier = Modifier.height(40.dp))
        Text("Let’s find good\nthings together.", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black, lineHeight = 32.sp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        Text("Start your journey with a simple, secure MicroTask account.", color = Color.DarkGray, fontSize = 14.sp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(60.dp))
        
        Button(
            onClick = onLoginSuccess,
            colors = ButtonDefaults.buttonColors(containerColor = LimeColor),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Create Account / Sign In", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MainDashboard() {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        containerColor = BgColor
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("MicroTask", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = TextWhite)
                    Text("Small Tasks Big Earnings", fontSize = 12.sp, color = Color.LightGray)
                }
                Icon(Icons.Default.Person, contentDescription = "Profile", tint = TextWhite, modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.DarkGray).padding(8.dp).clickable { Toast.makeText(context, "Profile Clicked", Toast.LENGTH_SHORT).show() })
            }
            Spacer(modifier = Modifier.height(20.dp))
            BalanceCard()
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ActionButton("Deposit", "Add Money", GreenGradient, Modifier.weight(1f)) { Toast.makeText(context, "Deposit", Toast.LENGTH_SHORT).show() }
                ActionButton("Withdraw", "Cash Out", PurpleGradient, Modifier.weight(1f)) { Toast.makeText(context, "Withdraw", Toast.LENGTH_SHORT).show() }
            }
            Spacer(modifier = Modifier.height(20.dp))
            TaskCard("Watch Videos & Earn", "Earn $0.05 - $0.30", Icons.Default.PlayArrow)
            Spacer(modifier = Modifier.height(10.dp))
            TaskCard("Typing Task", "Earn $0.10 - $0.50", Icons.Default.Edit)
            Spacer(modifier = Modifier.height(10.dp))
            TaskCard("Telegram Task", "Join channel", Icons.Default.Send)
        }
    }
}

@Composable
fun BalanceCard() {
    var isHidden by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(PrimaryGradient).padding(20.dp)) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("My Balance", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(if (isHidden) Icons.Default.Lock else Icons.Default.Info, contentDescription = "Toggle", tint = Color.White, modifier = Modifier.size(18.dp).clickable { isHidden = !isHidden })
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(if (isHidden) "$ ••••" else "$ 12.45", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(if (isHidden) "≈ •••• BDT" else "≈ 1,240.00 BDT", fontSize = 12.sp, color = Color.LightGray)
        }
    }
}

@Composable
fun ActionButton(title: String, subtitle: String, gradient: Brush, modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.clip(RoundedCornerShape(16.dp)).background(gradient).clickable { onClick() }.padding(16.dp)) {
        Column {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(subtitle, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}

@Composable
fun TaskCard(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardBgColor).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(subtitle, color = Color.Gray, fontSize = 12.sp)
        }
        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Gray)
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color(0xFF030A16), contentColor = Color.White) {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, label = { Text("Home") }, selected = true, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.List, contentDescription = "Tasks") }, label = { Text("Tasks") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Call, contentDescription = "Support") }, label = { Text("Support") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Person, contentDescription = "Refer") }, label = { Text("Refer") }, selected = false, onClick = {})
    }
}
