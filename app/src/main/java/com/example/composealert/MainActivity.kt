package com.example.composealert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composealert.ui.theme.AlertBackgroundColor
import com.example.composealert.ui.theme.AlertBorderColor
import com.example.composealert.ui.theme.AlertTextColor
import com.example.composealert.ui.theme.ComposeAlertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAlertTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (shouldShowDialog.value) {
        CustomDialog(shouldShowDialog = shouldShowDialog)
    }
    Button(
        onClick = { shouldShowDialog.value = true },
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = dimensionResource(id = R.dimen.inner_padding)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = AlertTextColor
        )
    ) {
        Text(
            text = stringResource(R.string.show_my_alert),
            fontFamily = FontFamily(Font(R.font.regular)),
        )
    }
}

@Composable
fun CustomDialog(
    shouldShowDialog: MutableState<Boolean>
) {
    if (shouldShowDialog.value) {
        Dialog(
            onDismissRequest = { shouldShowDialog.value = false },
        ) {
            Surface(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.alert_corner)),
                color = AlertBackgroundColor.copy(alpha = 0.8f),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.inner_padding))) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimensionResource(id = R.dimen.text_divider_small)),
                            text = stringResource(R.string.block_card),
                            fontSize = dimensionResource(id = R.dimen.primary_fontSize).value.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.semibold)),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(R.string.block_card_desc),
                            fontSize = dimensionResource(id = R.dimen.secondary_fontSize).value.sp,
                            fontFamily = FontFamily(Font(R.font.regular)),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                    HorizontalDivider(
                        thickness = dimensionResource(id = R.dimen.divider_thickness),
                        color = AlertBorderColor.copy(alpha = 0.36f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        Text(
                            modifier = Modifier
                                .clickable(true) {
                                    shouldShowDialog.value = false
                                }
                                .padding(vertical = dimensionResource(id = R.dimen.alert_row_padding))
                                .weight(0.5f),
                            text = stringResource(R.string.cancel),
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.regular)),
                            color = AlertTextColor,
                        )
                        VerticalDivider(
                            thickness = dimensionResource(id = R.dimen.divider_thickness),
                            color = AlertBorderColor.copy(alpha = 0.36f)
                        )
                        Text(
                            modifier = Modifier
                                .clickable(true) {
                                    shouldShowDialog.value = false
                                }
                                .padding(vertical = dimensionResource(id = R.dimen.alert_row_padding))
                                .weight(0.5f),
                            fontFamily = FontFamily(Font(R.font.semibold)),
                            text = stringResource(R.string.continue_txt),
                            textAlign = TextAlign.Center,
                            color = AlertTextColor,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeAlertTheme {
        MainScreen()
        CustomDialog(remember { mutableStateOf(true) })

    }
}