package com.android.mystoryappcompose.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.mystoryappcompose.R
import com.android.mystoryappcompose.ui.theme.MyStoryAppComposeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { onBackClick() }
                )
                Text(
                    text = "About Me",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        modifier = Modifier
                            .size(300.dp)
                            .clip(CircleShape),
                        painter = painterResource(R.drawable.profil),
                        contentDescription = "profile"
                    )
                    Text(
                        text = "Ilham Maulana",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ilhamjkt14@gmail.com",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilScreenPreview() {
    MyStoryAppComposeTheme {

    }

}