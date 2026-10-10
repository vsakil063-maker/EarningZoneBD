package com.microtask.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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

val BgDark = Color(0xFF020713)
val CardDark = Color(0xFF061324)
val LimeColor = Color(0xFFD8FF38)
val BalanceGradient = Brush.linearGradient(listOf(Color(0xFF00AAFF), Color(0xFF0051FF), Color(0xFF8E15EF)))
val DepositGradient = Brush.linearGradient(listOf(Color(0xFF00B355), Color(0xFF007736)))
val WithdrawGradient = Brush.linearGradient(listOf(Color(0xFF7C1DF7), Color(0xFF4C07A3)))
val CenterBubbleGradient = Brush.linearGradient(listOf(Color(0xFF00D2FF), Color(0xFF6F17E3)))
val WhiteText = Color(0xFFF8FAFC)
val MutedText = Color(0xFF839EC4)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.parseColor("#020713")
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = BgDark) {
                    MicroTaskApp()
                }
            }
        }
    }
}

@Composable
fun MicroTaskApp() {
    val isLoggedIn = remember { mutableStateOf(false) }

    if (!isLoggedIn.value) {
        AuthScreen(onLoginSuccess = { isLoggedIn.value = true })
    } else {
        MainDashboard()
    }
}

@Composable
fun AuthScreen(onLoginSuccess: () -> Unit) {
    val showBottomSheet = remember { mutableStateOf(false) }
    val isLoginView = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF8F8F7)).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("invezo.", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            Spacer(modifier = Modifier.height(40.dp))
            Text("Let’s find good\nthings together.", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black, lineHeight = 34.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Text("Start your journey with a simple, secure MicroTask account.", color = Color.DarkGray, fontSize = 14.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = { isLoginView.value = false; showBottomSheet.value = true },
                colors = ButtonDefaults.buttonColors(containerColor = LimeColor),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text("Create Account", color = Color.Black, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = { isLoginView.value = true; showBottomSheet.value = true },
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text("Sign In", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        // Custom Safe Bottom Sheet Overlay
        if (showBottomSheet.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable { showBottomSheet.value = false },
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                        .background(Color(0xFFF8F8F7))
                        .clickable(enabled = false) {} 
                        .padding(24.dp)
                        .padding(bottom = 20.dp)
                ) {
                    Text(if (isLoginView.value) "Welcome back" else "Create your account", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(if (isLoginView.value) "Sign in to continue" else "Enter your details to get started", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = "", onValueChange = {},
                        label = { Text("Mobile number or Gmail") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = "", onValueChange = {},
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            showBottomSheet.value = false
                            onLoginSuccess()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = LimeColor),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(if (isLoginView.value) "Sign In" else "Create Account", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun MainDashboard() {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(Color.White).border(1.dp, Color(0xFF00D2FF), RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.AttachMoney, contentDescription = null, tint = Color(0xFF00D2FF))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("MicroTask", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = WhiteText)
                            Text("Small Tasks Big Earnings", fontSize = 10.sp, color = MutedText)
                        }
                    }
                    Row {
                        Icon(Icons.Default.Notifications, contentDescription = "Alerts", tint = WhiteText, modifier = Modifier.size(28.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF0077FF)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = WhiteText)
                        }
                    }
                }
            }

            item { BalanceCard() }

            item {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ActionButton("Deposit", "Add Money", DepositGradient, Icons.Default.ArrowDownward, Modifier.weight(1f))
                    ActionButton("Withdraw", "Cash Out", WithdrawGradient, Icons.Default.ArrowUpward, Modifier.weight(1f))
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    TaskCard("Watch Videos & Earn", "Watch videos from popular platforms", Icons.Default.PlayArrow, Color(0xFF00D2FF))
                    Spacer(modifier = Modifier.height(10.dp))
                    TaskCard("Typing Task", "Type simple text & earn money", Icons.Default.Edit, Color(0xFFC43BF0))
                    Spacer(modifier = Modifier.height(10.dp))
                    TaskCard("Telegram Task", "Join our official channel", Icons.Default.Send, Color(0xFF2AABEE))
                }
            }
        }

        CustomBottomNav(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BalanceCard() {
    val isHidden = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clip(RoundedCornerShape(20.dp)).background(BalanceGradient).padding(20.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.AccountBalanceWallet, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("My Balance", color = Color(0xFFD8ECFF), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(if (isHidden.value) Icons.Default.Lock else Icons.Default.Visibility, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp).clickable { isHidden.value = !isHidden.value })
                        }
                        Text(if (isHidden.value) "$ ••••" else "$ 12.45", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        Text(if (isHidden.value) "≈ •••• BDT" else "≈ 1,240.00 BDT", fontSize = 11.sp, color = Color(0xFFD6E2F5))
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Tanvir Ahmed", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Row(modifier = Modifier.clickable { Toast.makeText(context, "UID Copied", Toast.LENGTH_SHORT).show() }, verticalAlignment = Alignment.CenterVertically) {
                        Text("UID: 784219", fontSize = 11.sp, color = Color(0xFFA8F0FF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFF061124)).padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                SubStat("Today", "$ 2.35", Color(0xFF00D25B))
                SubStat("Total", "$ 42.80", Color(0xFF00E5FF))
                SubStat("Level", "Silver", Color.LightGray)
            }
        }
    }
}

@Composable
fun SubStat(label: String, value: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(30.dp).clip(RoundedCornerShape(8.dp)).background(color.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Star, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(label, fontSize = 9.sp, color = MutedText)
            Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = WhiteText)
        }
    }
}

@Composable
fun ActionButton(title: String, subtitle: String, gradient: Brush, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clip(RoundedCornerShape(16.dp)).background(gradient).clickable { Toast.makeText(context, "$title Clicked", Toast.LENGTH_SHORT).show() }.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(Color.White.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(subtitle, color = Color.White.copy(alpha = 0.8f), fontSize = 10.sp)
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun TaskCard(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, iconColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardDark).border(1.dp, Color(0xFF112642), RoundedCornerShape(16.dp)).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(iconColor), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = WhiteText, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(subtitle, color = MutedText, fontSize = 10.sp)
        }
        Box(modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color(0xFF0066FF)).padding(horizontal = 10.dp, vertical = 6.dp)) {
            Text("$0.10 - $0.50", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CustomBottomNav(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().height(70.dp).background(Color(0xFF020813)).border(1.dp, Color(0xFF0F2038))
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            NavItem("Home", Icons.Outlined.Home, Color(0xFF00D2FF))
            NavItem("Tasks", Icons.Default.List, MutedText)
            
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.offset(y = (-15).dp)) {
                Box(modifier = Modifier.size(54.dp).clip(CircleShape).background(CenterBubbleGradient).border(3.dp, Color(0xFF030A16), CircleShape), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.SupportAgent, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                }
                Text("Support", fontSize = 10.sp, color = WhiteText, fontWeight = FontWeight.SemiBold)
            }
            
            NavItem("History", Icons.Outlined.History, MutedText)
            NavItem("Refer", Icons.Default.Share, MutedText)
        }
    }
}

@Composable
fun NavItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 10.sp, color = color, fontWeight = FontWeight.SemiBold)
    }
}
