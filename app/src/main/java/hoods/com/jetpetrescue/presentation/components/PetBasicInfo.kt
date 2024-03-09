package hoods.com.jetpetrescue.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R

@Composable
fun PetBasicInfo(
    name: String,
    gender: String,
    location: String,
    species: String,
    status: String,

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(end = 12.dp),
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription =null,
                    tint = Color.Red
                )
                Text(
                    text = location,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 0.dp,
                        end = 12.dp,
                        bottom = 0.dp
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = status,
                modifier = Modifier.padding(0.dp,0.dp,12.dp,0.dp),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.overline
            )

        }
        Column(
            modifier = Modifier.height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenderTag(gender = gender, modifier = Modifier)
            Text(
                text = gender,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

