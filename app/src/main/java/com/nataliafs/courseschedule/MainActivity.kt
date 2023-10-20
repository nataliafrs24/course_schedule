package com.nataliafs.courseschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.nataliafs.courseschedule.ui.theme.CourseScheduleTheme

data class CourseData(
    val name: String,
    val imageResId: Int,
    val startTime: String
)

// Fungsi untuk mendapatkan data mata kuliah berdasarkan nama
fun getCourseData(courseName: String): CourseData {
    return when (courseName) {
        "Kuliah 1" -> CourseData(
            name = "ADS",
            imageResId = R.drawable.img_1,
            startTime = "08:00"
        )
        "Kuliah 2" -> CourseData(
            name = "PIBD",
            imageResId = R.drawable.img_2,
            startTime = "10:00"
        )
        "Kuliah 3" -> CourseData(
            name = "PAW",
            imageResId = R.drawable.img_3,
            startTime = "13:00"
        )
        // Tambahkan data mata kuliah lainnya sesuai kebutuhan
        "Kuliah 4" -> CourseData(
            name = "PPAW",
            imageResId = R.drawable.img_4,
            startTime = "00:00"
        )
        "Kuliah 5" -> CourseData(
            name = "PPAB",
            imageResId = R.drawable.img_5,
            startTime = "00:00"
        )
        "Kuliah 6" -> CourseData(
            name = "PPAW",
            imageResId = R.drawable.img_6,
            startTime = "00:00"
        )
        else -> CourseData(
            name = "Mobile ",
            imageResId = R.drawable.img,
            startTime = "00:00"
        )

    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseScheduleTheme {
                // State untuk daftar kuliah
                var courses by remember { mutableStateOf(listOf("ADS", "Kuliah 2", "Kuliah 3")) }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(courses) { course ->
                        CourseItem(courseName = course)
                    }
                }
            }
        }
    }
}

@Composable
fun CourseItem(courseName: String) {
    val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    // State untuk mengontrol apakah detail ditampilkan atau tidak
    var isDetailVisible by remember { mutableStateOf(false) }

    // Data mata kuliah, gambar, dan waktu
    val courseData = getCourseData(courseName)

    // Tata letak daftar kuliah dengan menggunakan Column
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
    ) {
        // Tampilkan nama mata kuliah
        Text(
            text = "Mata Kuliah: ${courseData.name}",
            style = MaterialTheme.typography.headlineMedium
        )

        // Tampilkan waktu saat ini
        Text(
            text = "Waktu saat ini: $currentTime",
            style = MaterialTheme.typography.headlineMedium
        )

        // Tombol untuk menampilkan/menyembunyikan detail
        Button(
            onClick = { isDetailVisible = !isDetailVisible },
            modifier = Modifier
                .align(Alignment.End)
                .padding(4.dp)
        ) {
            Text(text = if (isDetailVisible) "Sembunyikan Detail" else "Tampilkan Detail")
        }

        // Tampilkan detail mata kuliah jika tombol ditekan
        if (isDetailVisible) {
            // Gambar mata kuliah
            Image(
                painter = painterResource(id = courseData.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Sesuaikan tinggi gambar
            )

            // Nama mata kuliah
            Text(
                text = "Nama Mata Kuliah: ${courseData.name}",
                style = MaterialTheme.typography.headlineMedium
            )

            // Waktu mata kuliah
            Text(
                text = "Waktu Mata Kuliah: ${courseData.startTime}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseListPreview() {
    CourseScheduleTheme {
        // Contoh daftar kuliah awal
        val initialCourses = listOf("Kuliah 1", "Kuliah 2", "Kuliah 3", "Kuliah 4", "Kuliah 5", "Kuliah 6")
        LazyColumn {
            items(initialCourses) { courseName ->
                CourseItem(courseName = courseName)
            }
        }
    }
}

