package pt.iade.games.gamedevedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.Student
import pt.iade.games.gamedevedex.ui.components.DetailTopBar
import pt.iade.games.gamedevedex.ui.theme.GamedevedexTheme

class ProjectDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // info passed via Intent
        val project = intent.getParcelableExtra<Project>("project_data")

        setContent {
            GamedevedexTheme {
                Scaffold(
                    topBar = { DetailTopBar(onBackClick = { finish() }) }
                ) { innerPadding ->
                    project?.let {
                        ProjectDetailView(
                            project = it,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } ?: run {
                        Text(
                            text = "No project data available.",
                            modifier = Modifier.padding(innerPadding).padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectDetailView(project: Project, modifier: Modifier = Modifier) {
    // Create a scrollable view for project details
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = project.assets.firstOrNull(), // First image in assets
            contentDescription = "Top Image of the Project",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Votes and Description Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            // Vote Section
            Column(
                modifier = Modifier.padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "▲",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "${project.votes}",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Votes",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Group Members
        project.groupMembers.forEach { member ->
            GroupMemberView(member)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (project.id == 69) { // Check Fetch Freedom ID
            Text(
                text = "Screenshots",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Column {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.screen1),
                        contentDescription = "Screenshot 1",
                        modifier = Modifier
                            .weight(1f)
                            .height(150.dp)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.screen2),
                        contentDescription = "Screenshot 2",
                        modifier = Modifier
                            .weight(1f)
                            .height(150.dp)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.screen3),
                        contentDescription = "Screenshot 3",
                        modifier = Modifier
                            .weight(1f)
                            .height(150.dp)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.screen4),
                        contentDescription = "Screenshot 4",
                        modifier = Modifier
                            .weight(1f)
                            .height(150.dp)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        // Asset Image and Description
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            AsyncImage(
                model = project.assets.getOrNull(1),
                contentDescription = "Asset Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = project.detailedDescription, // Dynamically shows project-specific description
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Composable
fun GroupMemberView(member: Student) {
    Row(modifier = Modifier.fillMaxWidth()) {

        // Avatar
        Image(
            painter = rememberAsyncImagePainter(model = member.avatar.toString()),
            contentDescription = "${member.name}'s Avatar",
            modifier = Modifier
                .size(80.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )

        // human info
        Column {
            Text(
                text = member.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Mood: ${member.mood}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = member.biography,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
