package pt.iade.games.gamedevedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.games.gamedevedex.controllers.StudentController
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.Student
import pt.iade.games.gamedevedex.ui.components.ProjectCard
import pt.iade.games.gamedevedex.ui.theme.GamedevedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamedevedexTheme {
                MainView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    var student by remember { mutableStateOf<Student?>(null) }
    val studentController = StudentController()
    studentController.GetById(
        id = 123,
        onSuccess = { studentReq ->
            student = studentReq
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Gamedevedex")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            if (student != null) {
                Text(student!!.name)
            } else {
                // TODO: Show progress circle thingy.
            }

            ProjectCard(
                modifier = Modifier.padding(vertical = 20.dp),
                project = ProjectExample()
            )
            ProjectCard(
                modifier = Modifier.padding(vertical = 20.dp),
                project = ProjectExample2()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainViewPreview() {
    GamedevedexTheme {
        MainView()
    }
}
fun ProjectExample(): Project {
    return Project(
        title = "Fetch Freedom",
        votes = 69,
        description = "Astro Bot didn't win the 2024 game of the year, the academy made a mistake by accident because it was actually fetch freedom that won. Fetch Freedom also won the 2023 game of the year award.",
        detailedDescription = "Play as a Husky in the cute and amazing game coming in 2026.",
        id = 69,
        semester = 3,
        assets = listOf(
            "https://e0.pxfuel.com/wallpapers/1013/130/desktop-wallpaper-husky-dog-background-themes-for-android-apk.jpg",
            "https://pet-health-content-media.chewy.com/wp-content/uploads/2024/09/11161718/202106Siberian-Husky_FeaturedImage.jpg",
            "android.resource://pt.iade.games.gamedevedex/drawable/screen1",
            "android.resource://pt.iade.games.gamedevedex/drawable/screen2",
            "android.resource://pt.iade.games.gamedevedex/drawable/screen3",
            "android.resource://pt.iade.games.gamedevedex/drawable/screen4"
        ),
        groupMembers = listOf(
            Student(
                id = 20230380,
                name = "Miguel Silver",
                biography = "Is ranked bronze in most competitive games, probably should change his name soon to Miguel Bronze instead.",
                mood = "Sad",
                avatar = "https://i.pinimg.com/736x/43/07/d6/4307d68b62ff90d889088689eb543cbe.jpg"
            ),
            Student(
                id = 20230300,
                name = "Tomás Monteiro",
                biography = "Is human and also made this game",
                mood = "Unknown",
                avatar = "https://i.pinimg.com/1200x/98/1d/6b/981d6b2e0ccb5e968a0618c8d47671da.jpg"
            )
        )
    )
}

fun ProjectExample2(): Project {
    return Project(
        title = "Football game",
        votes = 10,
        description = "Early prototype for football game. Vote and donate money if you want to see us making this game.",
        detailedDescription = "Preorder the biggest collab to ever happen in the world of football.",
        id = 404,
        semester = 80,
        assets = listOf(
            "https://img2-levelup.buscafs.com/871526/871526_1140x516.jpg",
            "https://momentimes.wordpress.com/wp-content/uploads/2018/07/reference-to-sayonara-football.jpg?w=739"
        ),
        groupMembers = listOf(
            Student(
                id = 7,
                name = "Cristiano Ronaldo",
                biography = "Portugal himself.",
                mood = "SUIIII",
                avatar = "https://media.gettyimages.com/photos/cristiano-ronaldo-of-portugal-poses-during-the-official-fifa-world-picture-id450555852?k=6&m=450555852&s=612x612&w=0&h=aUh0DVio_ubpFtCVvMv3WLR1MVPQji1sN5PDNKvHCT4="
            ),
            Student(
                id = 34,
                name = "Shaquille O'Neal",
                biography = "The 'giant' himself that shook the basketball world gave football a try and stuck around. Sometimes forgets he cant use his hands in this sport.",
                mood = "Thinking",
                avatar = "https://cdn.nba.com/headshots/nba/latest/1040x760/406.png"
            ),
            Student(
                id = 1,
                name = "Minion",
                biography = "Is the sole defender of the team, but he rather eat bananas and papayas than defend...",
                mood = "Banana",
                avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPQYeGq7Q27hdvjeuDYH2On_T6ZMVTYRFI7g&s"
            ),
            Student(
               id = 10,
               name = "Mark Evans",
               biography = "Won the junior international world cup tournament using the power of friendship.",
               mood = "Go team!",
               avatar = "https://static.wikia.nocookie.net/inazumaeleven/images/5/5e/Endoumammoruorionwersja.webp/revision/latest?cb=20240206124002&path-prefix=pl"
            ),
            Student(
                id = 12,
                name = "XQC",
                biography = "Overwatch pro player turned footballer. Has ADHD.",
                mood = "0.3 score",
                avatar = "https://scientific.place/assets/celebrities-iq/img/xqc-iq.webp"
            ),
            Student(
                id = 99,
                name = "Pam",
                biography = "Ex viral math teacher that once kicked a student on camera then got a Real Madrid contract.",
                mood = "Matrices rule!",
                avatar = "https://thumbs.dreamstime.com/b/happy-middle-aged-woman-school-math-teacher-standing-blackboard-portrait-smiling-mature-high-education-college-professor-229050036.jpg"
            ),
            Student(
                id = 124,
                name = "Dog",
                biography = "Is on the bench supporting the team.",
                mood = "Is Dog",
                avatar = "https://lh5.googleusercontent.com/proxy/_ZZA4fMLwp25NsYmTk2eWE1Vh-SwfgejIi7JZqC6kcnbIfIGwKaoxE5BeM7Ge8Yc2cMvciZJj6LSyQnLR2htgdZNQIMaoy-ED9f91Tb_mBYJl7Rxi8FOnEwFwTzot7NP3_cyLQsmMSmNPruRzqeWekvqjTqUIwcU8XnyVESOqS-4c0TR4cs"
            )
        )
    )
}
