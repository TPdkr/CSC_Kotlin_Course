package jetbrains.kotlin.course.almost.done
fun trimPicture(picture: String): String{
    return picture.trimIndent()
}
fun applyBordersFilter(picture: String): String{
    val width= getPictureWidth(picture)
    val newpicture=StringBuilder()
    newpicture.append("$borderSymbol".repeat(width+4))
    newpicture.append("$newLineSymbol")
    for (line in picture.lines()){
        val newline=StringBuilder()
        newline.append("$borderSymbol$separator")
        newline.append(line)
        newline.append("$separator".repeat(width-line.length+1))
        newline.append("$borderSymbol")
        newline.append("$newLineSymbol")
        val new =newline.toString()
        newpicture.append(newline)
    }
    newpicture.append("$borderSymbol".repeat(width+4))
    val newpic=newpicture.toString()
    return newpic
}
fun applySquaredFilter(picture: String): String{
    val width= getPictureWidth(picture)
    val newpicture=StringBuilder()
    newpicture.append("$borderSymbol".repeat(width*2+8))
    newpicture.append("$newLineSymbol")
    for (line in picture.lines()){
        val newline=StringBuilder()
        newline.append("$borderSymbol$separator")
        newline.append(line)
        newline.append("$separator".repeat(width-line.length+1))
        newline.append("$borderSymbol$borderSymbol$separator")
        newline.append(line)
        newline.append("$separator".repeat(width-line.length+1))
        newline.append("$borderSymbol")
        newline.append("$newLineSymbol")
        val new =newline.toString()
        newpicture.append(newline)
    }
    newpicture.append("$borderSymbol".repeat(width*2+8))
    newpicture.append("$newLineSymbol")
    for (line in picture.lines()){
        val newline=StringBuilder()
        newline.append("$borderSymbol$separator")
        newline.append(line)
        newline.append("$separator".repeat(width-line.length+1))
        newline.append("$borderSymbol$borderSymbol$separator")
        newline.append(line)
        newline.append("$separator".repeat(width-line.length+1))
        newline.append("$borderSymbol")
        newline.append("$newLineSymbol")
        val new =newline.toString()
        newpicture.append(newline)
    }
    newpicture.append("$borderSymbol".repeat(width*2+8))
    val newpic=newpicture.toString()
    return newpic
}
fun safeReadLine(): String{
    val a= readlnOrNull() ?: error("error input")
    return a
}
fun applyFilter(picture: String, filter: String): String{
    return when(filter){
        "borders"-> applyBordersFilter(trimPicture(picture))
        "squared"-> applySquaredFilter(trimPicture(picture))
        else->{
            println("error")
            picture
        }
    }
}
fun chooseFilter():String{
    var inp: String
    while (true) {
        println("Please choose the filter: 'borders' or 'squared'.")
        when (val input = safeReadLine()) {
            "borders", "squared" -> return input
            else -> continue
        }
    }
}
fun choosePicture():String{
    while (true) {
        println("Please choose a picture. The possible options are: ${allPictures().joinToString(", ")}")
        when (val input= safeReadLine()) {
             in allPictures() -> return getPictureByName(input)!!
            else -> continue
        }
    }
}
fun getPicture(): String{
    println("Do you want to use a predefined picture or a custom one? Please input 'yes' for a predefined image or 'no' for a custom one")
    do{
        val answ= safeReadLine()
        if (answ == "yes"){
            return choosePicture()
        }
        if (answ == "no"){
            break
        }
        println("Please input 'yes' or 'no'")
    } while(true)
    println("Please input a custom picture")
    return safeReadLine()
}
fun photoshop(){
    val picture= getPicture()
    val filter = chooseFilter()
    println("The old image:")
    println(picture)
    println("The transformed picture:")
    println(applyFilter(picture, filter))
}
fun main() {
    // Uncomment this code on the last step of the game

    photoshop()
}
