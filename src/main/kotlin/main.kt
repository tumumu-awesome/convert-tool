import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.lang.StringBuilder

fun main() {
    Window(title = "convert-tool", size = IntSize(480, 550)) {
        MaterialTheme {

            var text by remember { mutableStateOf("") }
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            val builder = StringBuilder()

            Column(Modifier.padding(10.dp), Arrangement.spacedBy(10.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    Text("please ⭐️: https://github.com/tumumu-awesome/convert-tool")
                }

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    Modifier.fillMaxWidth().height(400.dp),
                    label = {
                        Text("输入待转换的字符")
                    }
                )

                Row(modifier = Modifier.fillMaxWidth(), Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = {
                            builder.clear()
                            val tempArr = text.trim().split("\\r?\\n".toRegex())
                            for (str in tempArr) {
                                if (str == "")
                                    continue
                                builder.append('\'').append(str).append("',\r\n")
                            }
                            clipboard.setContents(StringSelection(builder.removeSuffix(",\r\n").toString()), null)
                        }
                    ) {
                        Text("convert")
                    }

                    Button(
                        onClick = { text = "" }) {
                        Text("reset")
                    }
                }
            }
        }
    }
}