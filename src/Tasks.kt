import java.io.*
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale


class Tasks {
}
fun main() {
    val ext = ".md"
    val dir = "./test/"

    // 当日
    val nowDate = Date()
    // yyyy-MM-dd
    val fileName = SimpleDateFormat("yyyyMMdd", Locale.US).format(nowDate)


    val cal = Calendar.getInstance()
    // 前日
    cal.time = nowDate
    cal.add(Calendar.DAY_OF_MONTH, -1)
    val preDate = SimpleDateFormat("yyyyMMdd", Locale.US).format(cal.time)

    val textFile = File(dir + preDate + ext).absoluteFile
    val lines = textFile.readLines().filter(String::isNotBlank)
        .toList()

    val filepath =  File(dir)
    if (!Files.exists(filepath.toPath())) {
        filepath.mkdir()
    }
    val file = FileWriter(dir + fileName + ext)
    val pw = PrintWriter(BufferedWriter(file))
    run {
        lines.forEach{
            if (it == "---") return@run
            if (!it.contains("[x]")) {
                pw.println(it)
            }
        }
    }
    pw.close();
}