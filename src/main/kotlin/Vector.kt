package qed.testbaseclass

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

// Vector<T : Number>
@Serializable
class Vector<T : Number?>(
    @Transient val length: Int = 0,
    @Transient private val backing: MutableList<T> = mutableListOf()
) {
    constructor(size: Int, default: T) : this(size, MutableList(size) { default })
    operator fun get(i: Int): T = backing[i]
    operator fun set(i: Int, value: T) { backing[i] = value }
    val data: List<T> get() = backing
    fun toList(): List<Double> = data.map { (it as? Number)?.toDouble() ?: 0.0 }
    fun toNullableList(): List<Double?> = data.map { (it as? Number)?.toDouble()  }
}

inline fun <T : Number?> vector(length: Int, init: (i: Int) -> T): Vector<T> {
    val backing = MutableList(length) { idx -> init(idx) }
    return Vector(length, backing)
}


/**
 * Create a matrix filled with a given value.
 */
fun prepopulatedDoubleVector(length: Int, value: Double) = vector(length) { _ -> value }

fun prepopulatedNullableDoubleVector(length: Int, value: Double?) = vector(length) { _ -> value }

fun prepopulatedIntVector(length: Int, value : Int?) = vector(length) { _ -> value}

fun vectorOf(vararg values: Double): Vector<Double> =
    vector(values.size) { i -> values[i] }

fun <T : Number> Vector<T>.fill(value: T) {
    for (i in 0 until length) {
        this[i] = value
    }
}

fun <T : Number> Vector<T?>.fillNull(value: T?) {
    for (i in 0 until length) {
        this[i] = value
    }
}

fun <T : Number> Vector<T>.maxOrNull(): T? {
    if (length == 0) return null
    var max = this[0]
    for (i in 1 until length) {
        if (this[i].toDouble() > max.toDouble()) {
            max = this[i]
        }
    }
    return max
}

fun <T : Number> Vector<T>.indexOf(value: T): Int {
    for (i in 0 until length) {
        if (this[i].toDouble() == value.toDouble()) return i
    }
    return -1
}

fun <T : Number> Vector<T>.sum(): Double {
    var sum = 0.0
    for (i in 0 until length) {
        val value = this[i].toDouble()
        sum += value
    }
    return sum
}

fun <T : Number> Vector<T>.take(n: Int): Vector<T> {
    require(n <= length) { "Requested $n elements, but vector length is $length" }
    return vector(n) { i -> this[i] }
}

fun <T : Number> Vector<T>.any(predicate: (T?) -> Boolean): Boolean {
    for (i in 0 until length) {
        if (predicate(this[i])) return true
    }
    return false
}
fun <T : Number> Vector<T>.first(): T {
    require(length > 0) { "Vector is empty" }
    return this[0]
}

fun <T : Number> Vector<T>.last(): T {
    require(length > 0) { "Vector is empty" }
    return this[length - 1]
}

fun <T : Number> Vector<T>.all(predicate: (T?) -> Boolean): Boolean {
    for (i in 0 until length) {
        if (!predicate(this[i])) return false
    }
    return true
}

fun <T : Number> Vector<T>.average(): Double {
    if (length == 0) return Double.NaN
    var sum = 0.0
    for (i in 0 until length) {
        sum += this[i].toDouble()
    }
    return sum / length
}

fun <T : Number> Vector<T>.drop(n: Int): Vector<T> {
    require(n <= length) { "Cannot drop $n elements from vector of length $length" }
    return vector(length - n) { i -> this[i + n]  }
}

