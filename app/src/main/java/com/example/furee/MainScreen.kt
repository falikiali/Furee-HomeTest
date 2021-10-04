package com.example.furee

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furee.ui.theme.*

@Composable
fun TopBar() {
    val context = LocalContext.current
    Row(
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Gray),
            modifier = Modifier.clickable {
                Toast.makeText(context, "Clicked menu button", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Menu Button",
                modifier = Modifier.padding(6.dp),
                tint = Navy
            )
        }

        Text(
            stringResource(id = R.string.app_name),
            color = Navy,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Gray),
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "Clicked notification button", Toast.LENGTH_SHORT).show()
                }
        ) {
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = "Notification",
                Modifier.padding(6.dp),
                tint = Navy
            )
        }
    }
}

@Composable
fun Identity(Dokter: Dokter) {
    Card(
        backgroundColor = Navy,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(Dokter.profile),
                contentDescription = "The Profile of Doctor",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
            )
            Spacer(Modifier.padding(16.dp))
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    Dokter.name,
                    color = Color.White
                )
                Text(
                    Dokter.specialist,
                    color = DarkGray,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(Modifier.padding(4.dp))
                Text(
                    Dokter.description,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

@Composable
fun CostSection(cost: List<Cost>) {
    val toPay = cost[0].value + cost[1].value
    val context = LocalContext.current
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            repeat(cost.size) {
                CostItems(cost = cost[it])
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "To Pay",
                    color = Navy
                )
                Text(
                    "$" + "%.2f".format(toPay.toDouble()),
                    color = Navy
                )
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(16.dp)
            )
            Card(
                backgroundColor = Cream,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Clicked promo button", Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.ic_baseline_confirmation_number_24),
                        contentDescription = "Icon Discount",
                        tint = Orange
                    )
                    Text(text = "Use Promo Code", color = Navy, fontWeight = FontWeight.Bold)
                    Icon(
                        painterResource(R.drawable.ic_baseline_arrow_forward_ios_24),
                        contentDescription = "Using Promo Code",
                        tint = Orange
                    )
                }
            }
        }
    }
}

@Composable
fun CostItems(cost: Cost) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            cost.desc,
            color = Navy
        )
        Text(
            "$" + "%.2f".format(cost.value.toDouble()),
            color = Navy
        )
    }
    if (cost.addDesc?.isNotEmpty() == true) {
        Text(
            cost.addDesc,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
    Spacer(modifier = Modifier.padding(bottom = 8.dp))
}

@Composable
fun PaymentMethod(payment: List<Payment>) {
    var selectedPayment by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Payment Options",
                fontWeight = FontWeight.ExtraBold,
                color = Navy,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                payment.forEach { payment ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.LightGray)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row() {
                            RadioButton(
                                selected = selectedPayment == payment.methodName,
                                onClick =
                                {
                                    selectedPayment = payment.methodName
                                    Toast.makeText(context, "Your payment method is $selectedPayment", Toast.LENGTH_SHORT).show()
                                },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.DarkGray)
                            )
                            Text(payment.methodName, color = Navy, modifier = Modifier.padding(start = 8.dp))
                        }
                        Row {
                            payment.logo.forEach { logoPayment ->
                                LogoPaymentItems(logoPayment = logoPayment)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LogoPaymentItems(logoPayment: LogoPayment) {
    Image(
        painter = painterResource(logoPayment.logo), 
        contentDescription = "Logo Payment Method",
        modifier = Modifier
            .size(25.dp)
            .padding(start = 5.dp)
    )
}

@Composable
fun ButtonConfirm() {
    val context = LocalContext.current
    Card(
        backgroundColor = Orange,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, "Clicked confirm button", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Text(
            "Pay & Confirm",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Composable
fun MainCompose() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DirtyWhite)
    ) {
        LazyColumn {
            item {
                TopBar()
                Identity(
                    Dokter(
                        "Dr. Corrie Anderson",
                        "Cardiovascular",
                        R.drawable.doctor,
                        "01 hour consultation"
                    )
                )
                CostSection(
                    cost = listOf(
                        Cost(
                            "Appointment Cost",
                            20,
                            "Consultation fee for 01 hour"
                        ),
                        Cost(
                            "Admin Fee",
                            5,
                            null
                        )
                    )
                )
                PaymentMethod(
                    listOf(
                        Payment(
                            1,
                            "Paypal",
                            listOf(
                                LogoPayment(
                                    R.drawable.paypal,
                                )
                            )
                        ),
                        Payment(
                            2,
                            "Credit Card",
                            listOf(
                                LogoPayment(
                                    R.drawable.americanexpress
                                ),
                                LogoPayment(
                                    R.drawable.mastercard
                                ),
                                LogoPayment(
                                    R.drawable.visa
                                )
                            )
                        )
                    )
                )
                ButtonConfirm()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FureeTheme {
        MainCompose()
    }
}