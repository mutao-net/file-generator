import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

fun readFolder(dir: String) {

    val files = File(dir)
    val list: Array<File> = files.listFiles()
    list.forEach {
        //println(it)
        run {
            if( !it.exists() )
                return@run
            else if( it.isDirectory() )
                readFolder( it.path );
            else if( it.isFile() )
                execute( it );
        }
    }
}
fun execute(file: File) {
    //println(file.path)
    val dirFile = File(file.path).absoluteFile
    val lines = dirFile.readLines().filter(String::isNotBlank)
        .toList()
    // println(lines);
    lines.forEach { matcher( it ) }
}
fun main() {
    // path
    val dir = ""
    readFolder(dir)
}
fun matcher(line:String){
    // jsoupでいいかも
    // <img src=\"(.*?)\".*?>(.*?)</img>
    println(line)
    println("----------------")
    val pattern: Pattern = Pattern.compile("<img src=\\\"(.*?)\\\".*?>(.*?)</img>")
    val matcher: Matcher = pattern.matcher(line)
    while (matcher.find()) {
        println(matcher.group(1))
    }
}