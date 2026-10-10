package com.example.microtask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microtask.ui.theme.*

@Composable
fun MicroTaskScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        containerColor = DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TopHeader()
            Spacer(modifier = Modifier.height(24.dp))
            BalanceCard()
            Spacer(modifier = Modifier.height(16.dp))
            ActionButtonsRow()
            Spacer(modifier = Modifier.height(16.dp))
            TaskItem(
                icon = Icons.Default.PlayArrow,
                title = "Watch Videos & Earn",
                subtitle = "Watch videos from popular platforms",
                price = "$0.05 - $0.30"
            )
            Spacer(modifier = Modifier.height(12.dp))
            TaskItem(
                icon = Icons.Default.Keyboard,
                title = "Typing Task",
                subtitle = "Type simple text & earn money",
                price = "$0.10 - $0.50"
            )
            Spacer(modifier = Modifier.height(12.dp))
            TaskItem(
                icon = Icons.Default.Person,
                title = "Social Media",
                subtitle = "Follow our social media",
                price = "$0.20 - $1.00"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Replace with your actual Logo Image later
            Icon(Icons.Default.Star, contentDescription = "Logo", tint = Color.White, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "NeoXTask", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Earn More • Grow Faster", color = TextGray, fontSize = 12.sp)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Notifications, contentDescription = "Notification", tint = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.White, modifier = Modifier.size(36.dp))
        }
    }
}

@Composable
fun BalanceCard() {
    val gradient = Brush.horizontalGradient(listOf(PurpleGradientStart, PurpleGradientEnd))
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(gradient)
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Wallet", tint = Color.White, modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = "My Balance", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        Text(text = "$ 18.75", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
                        Text(text = "≈ 1,523.00 BDT", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                    }
                }
                
                Button(
                    onClick = { /* Withdraw Action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "Withdraw >", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ActionButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Deposit Button
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.horizontalGradient(listOf(GreenGradientStart, GreenGradientEnd)))
                .padding(16.dp)
        ) {
            Column {
                Icon(Icons.Default.AddCircle, contentDescription = "Deposit", tint = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Deposit", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Add Money", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }

        // Withdraw Button
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.horizontalGradient(listOf(OrangeGradientStart, OrangeGradientEnd)))
                .padding(16.dp)
        ) {
            Column {
                Icon(Icons.Default.ExitToApp, contentDescription = "Withdraw", tint = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Withdraw", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Cash Out", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun TaskItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(BlueGradientStart, BlueGradientEnd))),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = title, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = subtitle, color = TextGray, fontSize = 12.sp)
            }
        }
        
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF0A6CFF).copy(alpha = 0.2f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(text = price, color = Color(0xFF00D1FF), fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color(0xFF070414),
        contentColor = TextGray
    ) {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, label = { Text("Home") }, selected = true, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.List, contentDescription = "Tasks") }, label = { Text("Tasks") }, selected = false, onClick = { })
        
        // Custom Center Support Button
        NavigationBarItem(
            icon = { 
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Brush.horizontalGradient(listOf(BlueGradientStart, BlueGradientEnd))),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Support", tint = Color.White)
                }
            }, 
            label = { Text("Support") }, 
            selected = false, 
            onClick = { }
        )
        
        NavigationBarItem(icon = { Icon(Icons.Default.Refresh, contentDescription = "History") }, label = { Text("History") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Share, contentDescription = "Refer") }, label = { Text("Refer") }, selected = false, onClick = { })
    }
}
