import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.api.print
import org.jetbrains.kotlinx.dataframe.io.readCSV

fun main() {
    var df = DataFrame.readCSV(fileOrUrl = "src/main/resources/world-data-2023.csv", delimiter = ',')
    df.head(10).print()

    // **Task 1: Find top-10 countries by Total CO2 emissions**

    // **Task 2: Build a Bar Plot**

    // **Task 3: Find top-10 countries by Total CO2 emissions per Person**

    // **Task 4: Build a Bar Gradient for absolute CO2 Emission and CO2 Emission per Person**

    // **Task 5: Calculate middle geographical point and average forested area for group of countries with the same official language**

    // **Task 6: Find the best option to visualize this dataframe among charts available with Kandy**
}