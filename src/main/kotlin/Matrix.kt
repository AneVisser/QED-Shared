package qed.testbaseclass

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = MatrixSerializer::class)
class Matrix<T : Number?>(
    val rows: Int,
    val cols: Int,
    private val flatBacking: MutableList<T?> = mutableListOf()
) {
    constructor(rows: Int, cols: Int, default: T?) :
            this(rows, cols, MutableList(rows * cols) { default })

    operator fun get(r: Int, c: Int): T? = flatBacking[r * cols + c]
    operator fun set(r: Int, c: Int, value: T?) { flatBacking[r * cols + c] = value }

    fun toNested(): List<List<T?>> =
        (0 until rows).map { r ->
            flatBacking.subList(r * cols, (r + 1) * cols)
        }

    fun toMatrixList(): List<List<Double>> = toNested().map { row -> row.map { it?.toDouble() ?: 0.0 } }
}


object MatrixSerializer : KSerializer<Matrix<Double?>> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Matrix") {
            element<Int>("rows")
            element<Int>("cols")
            element(
                "backing",
                listSerialDescriptor(listSerialDescriptor(Double.serializer().nullable.descriptor))
            )
        }

    override fun serialize(encoder: Encoder, value: Matrix<Double?>) {
        val nested = value.toNested()
        val composite = encoder.beginStructure(descriptor)
        composite.encodeIntElement(descriptor, 0, value.rows)
        composite.encodeIntElement(descriptor, 1, value.cols)
        composite.encodeSerializableElement(
            descriptor, 2,
            ListSerializer(ListSerializer(Double.serializer().nullable)),
            nested
        )
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): Matrix<Double?> {
        val input = decoder as JsonDecoder
        val obj = input.decodeJsonElement().jsonObject
        val rows = obj["rows"]!!.jsonPrimitive.int
        val cols = obj["cols"]!!.jsonPrimitive.int
        val backingArray = obj["backing"]!!.jsonArray

        val flat = mutableListOf<Double?>()
        for (row in backingArray) {
            for (cell in row.jsonArray) {
                flat.add(cell.jsonPrimitive.doubleOrNull)
            }
        }
        return Matrix(rows, cols, flat)
    }
}



inline fun <T : Number> matrix(rows: Int, cols: Int, init: (r: Int, c: Int) -> T?): Matrix<T> {
    val flat = MutableList(rows * cols) { idx ->
        val r = idx / cols
        val c = idx % cols
        init(r, c)
    }
    return Matrix(rows, cols, flat)
}

/**
 * Create a matrix filled with a given value.
 */
fun prepopulatedMatrix(rows: Int, cols: Int, value : Double?) = matrix(rows, cols) { _, _ -> value}

fun <T : Number> Matrix<T>.fill(value: T?) {
    for (i in 0 until rows) {
        for (j in 0 until rows) {
            this[i, j] = value
        }
    }
}

