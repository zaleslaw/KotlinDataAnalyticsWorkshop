import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.api.print
import org.jetbrains.kotlinx.dataframe.io.readCSV

fun main() {
    var df = DataFrame.readCSV(fileOrUrl = "src/main/resources/world-data-2023.csv", delimiter = ',')
    df.head(10).print()
}