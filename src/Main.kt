import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    println("Данная программа группирует слова по одинаковым буквам!")
    println("Введите слова, разделенные пробелами или запятыми: ")

    val input = scanner.nextLine()

    val words = input.split(",")
        .flatMap { it.trim().split("\\s+".toRegex()) }
        .filter { it.isNotEmpty() }

    if (words.isEmpty()) {
        println("Ошибка: Вы не ввели ни одного слова. Пожалуйста, попробуйте снова.")
        return
    }

    val invalidWords = words.filter { !it.all { char -> char.isLetter() } }
    if (invalidWords.isNotEmpty()) {
        println("Ошибка: Следующие слова содержат недопустимые символы (только буквы разрешены):")
        println(invalidWords.joinToString(", "))
        return
    }

    val grouped = words.groupBy { it.toLowerCase().toCharArray().sorted().joinToString("") }

    println("\nГруппы слов, состоящих из одинаковых букв:")
    for ((_, group) in grouped) {
        println(group.joinToString(", "))
    }
}