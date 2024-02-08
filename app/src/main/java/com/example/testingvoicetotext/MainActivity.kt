package com.example.testingvoicetotext

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testingvoicetotext.ui.theme.TestingVoicetoTextTheme
import java.util.Locale


class MainActivity : ComponentActivity() {
    private lateinit var textToSpeech:TextToSpeech
    private val mTts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // text to speech button
        val btnText = findViewById<Button>(R.id.btnText)

        // text
        val editText = findViewById<EditText>(R.id.Text)

        // create TextToSpeech object
        textToSpeech = TextToSpeech(this){status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech.setLanguage(Locale.getDefault())
                if (result == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(this, "language not supported", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failure to create the TTS", Toast.LENGTH_LONG).show()
                Toast.makeText(this, status.toString(), Toast.LENGTH_LONG).show()
            }
        }
        // Setting On Click Listener
        btnText.setOnClickListener {
            if (editText.text.toString().trim().isNotEmpty()) {
//                textToSpeech.speak(editText.text.toString().trim(), TextToSpeech.QUEUE_FLUSH, null, null)
                speak(editText.text.toString().trim());
                Toast.makeText(this, editText.text.toString().trim(), Toast.LENGTH_SHORT).show()
            } else (
                    Toast.makeText(this, "Required", Toast.LENGTH_SHORT).show()
            )
        }
    }

    private fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestingVoicetoTextTheme {
        Greeting("Android")
    }
}