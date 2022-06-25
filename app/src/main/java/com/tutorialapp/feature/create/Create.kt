package com.tutorialapp.feature.create


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.tutorialapp.data.database.Tutorial
import com.tutorialapp.domain.TutorialStep
import java.util.*


/*
//test to simply try to print steps
val step1 = TutorialStep(
    id = 1,
    content = "c1",
)
val step2 = TutorialStep(
    id = 2,
    content = "c2",
)
val step3 = TutorialStep(
    id = 3,
    content = "c3",
)

//
*/

//private var steps:MutableList<TutorialStep> = mutableStateListOf(step1, step2, step3)
private var steps:MutableList<TutorialStep> = mutableStateListOf()
private var counter: Int = 0
private var title: String = ""
private var titleTextField: Unit = Unit

//SCHÖN MACHEN
@Composable
fun Create(addTutorial: (Tutorial) -> Unit){
    Column() {
        TutorialNameTextField() //evtl enter taste deaktivieren
        //Divider(color = Color.Blue, thickness = 1.dp)
        ShowExistingSteps(addTutorial) //übersicht machen, id, content und evtl image
        //Divider(color = Color.Blue, thickness = 1.dp)
        //AddStep() //textfeld anpassen (größe, form?), inhalt löschen nach hinzufügen, möglichkeit für bilder(?)
    }
}

@Composable
fun TutorialNameTextField(){
    var value by remember { mutableStateOf(TextFieldValue("")) }
    titleTextField = OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
            title = newText.text
        },
        label = { Text(text = "Name of the tutorial")},
        singleLine = true,
        modifier = Modifier
            .padding(8.dp)
            .width(400.dp)
    )
    //title = titleTextField.toString()
}

/*
@Composable
fun showExistingSteps(){
    //für jeden bereits vorhandenen schritt
    steps.forEach{
        Text(it.id.toString())
        Text(it.content)
    }
}*/

@Composable
fun ShowExistingSteps(AddTutorial: (Tutorial) -> Unit){
    //für jeden bereits vorhandenen schritt
    LazyColumn (
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
            )
    {
        itemsIndexed(steps) {
                index, item ->
            Box(modifier = Modifier
                .padding(3.dp)
                .width(380.dp)
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .wrapContentHeight(align = Alignment.CenterVertically)) {
                Text(text = (item.id.toString()),
                    Modifier
                        .padding(vertical = 3.dp)
                        .padding(horizontal = 5.dp), textDecoration = TextDecoration.Underline)
                Text(item.content,
                    Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 5.dp))
            }
        }
        item { AddStep() }
        item{ FinishCreatingTutorial(AddTutorial)}
    }
}

@Composable
fun AddStep(){
    val textFieldValue = ""
    var content by remember { mutableStateOf(TextFieldValue(textFieldValue)) }
    TextField(
        value = content,
        onValueChange = {content = it},
        label = { Text("Describe this step")},
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .width(400.dp)
            .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
    )
    Button(
        onClick = {
            if (content != TextFieldValue("")) {
                val step = TutorialStep(
                    id = counter,
                    content = content.text.trim(),
                )
                content = TextFieldValue("")
                counter++
                steps.add(step)
            }

        }, Modifier.padding(start = 8.dp)
    ){Text("Add Step")}
}
@Composable
fun FinishCreatingTutorial(AddTutorial: (Tutorial) -> Unit){
    Button(
        onClick = {
            var tutorial: Tutorial = Tutorial(
                id = generateID(),
                steps = mutableListToList(steps),
                title = title.substringAfter("(text=\"")
            )
            AddTutorial(tutorial)
            //if(titleTextField is TextFieldKt)
            steps.clear()
            counter = 0
        }, Modifier.padding(start = 8.dp)
    ){Text("Finish Tutorial")}
}

private fun generateID() : Int{
    val uuid = UUID.randomUUID()
    return uuid.hashCode()
    //return Random.nextInt(10000,99999)
}

private fun mutableListToList(mutableList: MutableList<TutorialStep>): List<com.tutorialapp.data.database.TutorialStep>{
    var result: MutableList<com.tutorialapp.data.database.TutorialStep> = mutableListOf<com.tutorialapp.data.database.TutorialStep>()

    for (element in mutableList) {
       result.add(
           com.tutorialapp.data.database.TutorialStep(
               id = element.id,
               content = element.content
           )
       )
    }
    return result
}
/*
fun TakePicture() {
    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(File(...)).build()
    imageCapture.takePicture(outputFileOptions, cameraExecutor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(error: ImageCaptureException)
            {
                // insert your code here.
            }
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                // insert your code here.
            }
        })
}*/

