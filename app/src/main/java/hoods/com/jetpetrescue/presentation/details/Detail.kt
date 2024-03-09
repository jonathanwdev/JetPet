package hoods.com.jetpetrescue.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.presentation.components.PetBasicInfo
import hoods.com.jetpetrescue.domain.models.Pet

@Composable
fun DetailScreen(pet: Pet, onNavigateBack: () -> Unit, onPetBtnClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onSurface,
                title = {
                    Text(text = "Detail")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable { onNavigateBack.invoke() }
                    )
                }
            )
        }
    ) { padding ->
        var isLoading by remember {
            mutableStateOf(false)
        }

        LazyColumn(contentPadding = padding) {
            item {
                if (isLoading) {
                    CircularProgressIndicator()
                }
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    model = if (pet.photos.isNotEmpty()) pet.photos[0].full else null,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.placeholder_ic),
                    placeholder = painterResource(id = R.drawable.placeholder_ic),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart,
                    onLoading = {
                        isLoading = true
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                        isLoading = false
                    },
                    onSuccess = {
                        isLoading = false
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PetBasicInfo(
                    name = pet.name,
                    gender = pet.gender,
                    location = pet.contact.address,
                    species = pet.species,
                    status = pet.status
                )


            }
            item {
                MyStoryItem(pet = pet)
            }
            item {
                PetInfo(pet = pet)
            }
            item {
                PetButton {
                    onPetBtnClick.invoke()
                }
            }

        }
    }
}

@Composable
fun PetButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(36.dp))
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Text("Adopt me")
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun MyStoryItem(pet: Pet) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "My Story")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = pet.description,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}

@Composable
fun PetInfo(pet: Pet) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PetInfoCard(
                primaryText = "${pet.age}yrs", secondaryText = "Age", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            PetInfoCard(
                primaryText = pet.colors, secondaryText = "Color", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            PetInfoCard(
                primaryText = pet.breeds, secondaryText = "Breed", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun PetInfoCard(
    primaryText: String,
    secondaryText: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(
                    text = secondaryText,
                )
            }
            Text(
                text = primaryText,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

