package jetbrains.kotlin.course.last.push

// You will use this function later
fun getPattern(): String {
    println(
        "Do you want to use a pre-defined pattern or a custom one? " +
                "Please input 'yes' for a pre-defined pattern or 'no' for a custom one"
    )
    do {
        when (safeReadLine()) {
            "yes" -> {
                return choosePattern()
            }
            "no" -> {
                println("Please, input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

// You will use this function later
fun choosePattern(): String {
    do {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val name = safeReadLine()
        val pattern = getPatternByName(name)
        pattern?.let {
            return@choosePattern pattern
        }
    } while (true)
}

// You will use this function later
fun chooseGenerator(): String {
    var toContinue = true
    var generator = ""
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    do {
        when (val input = safeReadLine()) {
            "canvas", "canvasGaps" -> {
                toContinue = false
                generator = input
            }
            else -> println("Please, input 'canvas' or 'canvasGaps'")
        }
    } while (toContinue)
    return generator
}

// You will use this function later
fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")
fun getPatternHeight(pattern: String): Int{
    return (pattern.lines()).count()
}
fun fillPatternRow(patternRow: String, patternWidth: Int): String{
    if (patternRow.length > patternWidth){
        throw IllegalStateException("length too big =(")
    }
    if (patternRow.length < patternWidth){
        val a= StringBuilder()
        a.append(patternRow)
        a.append("$separator".repeat(patternWidth-patternRow.length))
        return a.toString()
    }
    return patternRow
}
fun repeatHorizontally(pattern: String, repeats: Int, patternWidth: Int): String{
    val newpat= StringBuilder()
    for (line in pattern.lines()){
        val row= fillPatternRow(line, patternWidth)
        val a= StringBuilder()
        for (i in 0 until repeats){
            a.append(row)
        }
        a.append(newLineSymbol)
        newpat.append(a.toString())
    }
    return newpat.toString()
}
fun dropTopFromLine(pattern: String,  width: Int, patternHeight: Int, patternWidth: Int): String{
    val bd= StringBuilder()
    var i=0
    if (patternHeight>1){
        for(row in pattern.lines()){
            if (i>=width){
                bd.append(row)
                bd.append(newLineSymbol)
            }
            i++
        }
        return bd.toString()
    }
    return pattern

}
fun canvasGenerator(pattern: String, width: Int, height: Int): String{
    val bd= StringBuilder()
    val patternWidth= getPatternWidth(pattern)
    val patternHeight = getPatternHeight(pattern)
    val top= dropTopFromLine(pattern,1, patternHeight, patternWidth).removeSuffix(newLineSymbol)
    for(i in 0 until height){
        if (i==0){
            bd.append(repeatHorizontally(pattern, width, patternWidth))
        } else {
            bd.append(repeatHorizontally(top, width, patternWidth))
        }
    }
    return (bd.toString())
}
fun repeatHorizontallyWithGaps(pattern: String, number: Int, patternWidth: Int, patternHeight: Int, pos: Int): String{
    //1 even pos
    //0 uneven pos
    val newpat= StringBuilder()
    val gap= "$separator".repeat(patternWidth)
    for (line in pattern.lines()){
        val row= fillPatternRow(line, patternWidth)
        val a= StringBuilder()
        for (i in 0 until number){
            if (i%2==pos|| number==1) {
                a.append(row)
            } else {
                a.append(gap)
            }
        }
        a.append(newLineSymbol)
        newpat.append(a.toString())
    }
    return newpat.toString()
}
fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String{
    val bd=StringBuilder()
    val patternWidth= getPatternWidth(pattern)
    val patternHeight = getPatternHeight(pattern)
    for (i in 0 until height){
        bd.append(repeatHorizontallyWithGaps(pattern, width, patternWidth, patternHeight, i%2))
    }
    return bd.toString()
}
fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String{
    when(generatorName){
        "canvas"->return canvasGenerator(pattern, width, height)
        "canvasGaps"->return canvasWithGapsGenerator(pattern, width, height)
        else-> error("incorrect name")
    }
}
fun main() {
    // Uncomment this code on the last step of the game

    val pattern = getPattern()
    val generatorName = chooseGenerator()
    println("Please input the width of the resulting picture:")
    val width = safeReadLine().toInt()
    println("Please input the height of the resulting picture:")
    val height = safeReadLine().toInt()

    println("The pattern:$newLineSymbol${pattern.trimIndent()}")

    println("The generated image:")
    println(applyGenerator(pattern, generatorName, width, height))
}
